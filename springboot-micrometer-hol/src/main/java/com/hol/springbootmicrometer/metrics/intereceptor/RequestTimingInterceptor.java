package com.hol.springbootmicrometer.metrics.intereceptor;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RequestTimingInterceptor extends AbstractMetricsInterceptor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    public RequestTimingInterceptor(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        startTime.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long duration = System.currentTimeMillis() - startTime.get();
        Map<String, String> tags = Map.of(
                "method", request.getMethod(),
                "uri", request.getRequestURI(),
                "status", String.valueOf(response.getStatus())
        );
        recordMetric("http_request_duration", tags, duration);
    }
}