package com.hol.springbootmicrometer.metrics.config;

import com.hol.springbootmicrometer.metrics.aop.MetricsAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public MetricsAspect metricsAspect(MeterRegistry registry) {
        return new MetricsAspect(registry);
    }
}