package com.vm.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.inject.Qualifier;
import java.beans.PropertyDescriptor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.LinkedList;
import java.util.List;

/**
 * Bean实例化前置操作
 * @author huaihai.guan
 * @since 2021/11/19 9:47
 */
@Component
public class IInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(IInstantiationAwareBeanPostProcessor.class);


    /**
     * 默认系统会从Ioc容器中往每个Bean上进行属性填充(前提是符合条件，例如:@Autowired,@Resource,@Qualifier)进行注入
     * 如果我们想自定义注入就可以在该方法上进行
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 可以实现自定义属性填充

        InjectionMetadata metadata = this.findInjectionMetadata(bean.getClass());

        try {
            metadata.inject(bean, beanName, pvs);
        } catch (Throwable var) {

            logger.error("BeanName {} injection Exception {}", beanName, var.getMessage());
        }
        return pvs;
    }

    /**
     * 查找Bean中字段是否包含自定义注解
     * @param aClass
     * @return
     */
    private InjectionMetadata findInjectionMetadata(Class<?> aClass) {
        List<InjectionMetadata.InjectedElement> injectedElements = new LinkedList<>();

        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            TestAnnotation testAnnotation = field.getAnnotation(TestAnnotation.class);
            if (testAnnotation != null) {
                injectedElements.add(0, new IInjectedElement(field, null));
            }
        }

        return new InjectionMetadata(aClass, injectedElements);
    }

    @Override
    public int getOrder() {
        return 1923929;
    }

    /**
     * 自定义注入元素（意思是，如果匹配到自定义注解的类型字段，请返回你想要注入的类）
     */
    static class IInjectedElement extends InjectionMetadata.InjectedElement {

        protected IInjectedElement(Member member, PropertyDescriptor pd) {
            super(member, pd);
            // 过滤掉不是Test类型的字段
            this.checkResourceType(Test.class);
        }

        @Override
        protected Object getResourceToInject(Object target, String requestingBeanName) {
            // 我要返回的实体类
            return new TestImpl();
        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnnotation {
    }

    public interface Test {

        String getName();

    }

    public static class TestImpl implements Test {

        @Override
        public String getName() {
            return "hello";
        }
    }


}
