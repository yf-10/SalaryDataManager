package jp.fujino.SalaryDataManager.configuration;

import jp.fujino.SalaryDataManager.interceptor.LoggingHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    LoggingHandlerInterceptor loggingHandlerInterceptor() {
        return new LoggingHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingHandlerInterceptor())
                .addPathPatterns("/salary/**");
    }

}
