package com.vm.test.spring;

import com.vm.test.annotation.TestComponent;
import com.vm.test.config.IBinderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author huaihai.guan
 * @date 2020/12/15 18:31
 */
@Component
public class IBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware, ResourceLoaderAware {

    private static Logger logger = LoggerFactory.getLogger(IBeanDefinitionRegistryPostProcessor.class);

    private Environment environment;

    private ResourceLoader resourceLoader;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Set<String> resolvedScanPackages = resolvedScanPackages(new HashSet<>(Collections.singletonList("com.vm.test.component")));
        VmBeanDefinitionScanner scanner = new VmBeanDefinitionScanner(beanDefinitionRegistry, false, environment, resourceLoader);

        scanner.addIncludeFilter(new AnnotationTypeFilter(TestComponent.class));
        resolvedScanPackages.forEach(scanPackage -> {
            Set<BeanDefinitionHolder> holders = scanner.doScan(scanPackage);

            if (CollectionUtils.isEmpty(holders)) {
                for (BeanDefinitionHolder beanDefinitionHolder : holders) {
                    beanDefinitionRegistry.registerBeanDefinition(beanDefinitionHolder.getBeanName(), beanDefinitionHolder.getBeanDefinition());
                }
            }
        });

        try {
            this.loadPropertySource();
        } catch (IOException var1) {

            logger.info("loadPropertySource error " + var1.getMessage());
        }
    }

    private void loadPropertySource() throws IOException {
        AbstractEnvironment abstractEnvironment = (AbstractEnvironment) this.environment;
        MutablePropertySources mutablePropertySources = abstractEnvironment.getPropertySources();
        Resource resource = this.resourceLoader.getResource("classpath:/i.p");
        if (resource.isFile()) {
            Map properties = PropertiesLoaderUtils.loadProperties(resource);
            PropertySource propertySource = new OriginTrackedMapPropertySource("iConfig", Collections.unmodifiableMap(properties), true);
            mutablePropertySources.addLast(propertySource);
        }

        BindResult<IBinderProperties> bindResult = Binder.get(environment).bind("i.bind", Bindable.of(IBinderProperties.class));
        if (bindResult.isBound()) {
            IBinderProperties iBinderProperties = bindResult.get();
            logger.info(iBinderProperties.getBindValue());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    private Set<String> resolvedScanPackages(HashSet<String> scanPackages) {
        Set<String> resolvedScanPackages = new LinkedHashSet<>(scanPackages.size());

        for (String scanPackage : scanPackages) {
            if (!StringUtils.isEmpty(scanPackage)) {
                resolvedScanPackages.add(this.environment.resolvePlaceholders(scanPackage));
            }
        }
        return resolvedScanPackages;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
