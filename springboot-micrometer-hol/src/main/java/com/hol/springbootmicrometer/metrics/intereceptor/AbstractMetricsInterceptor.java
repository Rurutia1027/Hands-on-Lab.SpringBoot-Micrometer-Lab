package com.hol.springbootmicrometer.metrics.intereceptor;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public abstract class AbstractMetricsInterceptor implements HandlerInterceptor {

    protected final MeterRegistry registry;

    public AbstractMetricsInterceptor(MeterRegistry registry) {
        this.registry = registry;
    }

    protected void recordMetric(String metricName, Map<String, String> tags, double value) {
        Timer.builder(metricName)
                .tags(tags.entrySet().stream()
                        .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                        .toArray(String[]::new))
                .register(registry)
                .record((long) value, TimeUnit.MILLISECONDS);
    }
}