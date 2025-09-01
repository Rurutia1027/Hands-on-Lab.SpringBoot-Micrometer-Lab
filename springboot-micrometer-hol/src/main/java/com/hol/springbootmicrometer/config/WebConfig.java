package com.hol.springbootmicrometer.config;

import com.hol.springbootmicrometer.metrics.intereceptor.ErrorCountingInterceptor;
import com.hol.springbootmicrometer.metrics.intereceptor.RequestTimingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestTimingInterceptor timingInterceptor;
    private final ErrorCountingInterceptor errorInterceptor;

    public WebConfig(RequestTimingInterceptor timingInterceptor, ErrorCountingInterceptor errorInterceptor) {
        this.timingInterceptor = timingInterceptor;
        this.errorInterceptor = errorInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timingInterceptor);
        registry.addInterceptor(errorInterceptor);
    }
}