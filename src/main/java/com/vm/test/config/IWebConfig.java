package com.vm.test.config;

import com.vm.test.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * @author huaihai.guan
 * @since 2021/8/12 19:20
 */
@Component
public class IWebConfig implements WebMvcConfigurer {

    private static Logger logger = LoggerFactory.getLogger(IWebConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IInterceptor());
    }

    static class IInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            RequestContext.set(request.getRequestURI());
            return true;
        }
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(0, new DefaultExceptionResolvers());
    }

    static class DefaultExceptionResolvers implements HandlerExceptionResolver {

        @Override
        public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            request.getRequestURI();
            response.setHeader("Error", "Yes");
            ModelAndView modelAndView = new ModelAndView();
            if (ex instanceof MethodArgumentNotValidException) {
                BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
                FieldError fieldError = bindingResult.getFieldError();
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setCode(-1);
                jsonResponse.setError(true);
                modelAndView.setView(new ErrorView(jsonResponse));
                jsonResponse.setErrMessage(null != fieldError ? fieldError.getDefaultMessage() : "UnknownErrorMessage");
                return modelAndView;
            }
            throw new RuntimeException(ex);
        }
    }
}
