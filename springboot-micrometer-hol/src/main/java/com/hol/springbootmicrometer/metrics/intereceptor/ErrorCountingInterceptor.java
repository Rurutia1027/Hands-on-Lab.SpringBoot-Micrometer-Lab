package com.hol.springbootmicrometer.metrics.intereceptor;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class ErrorCountingInterceptor extends AbstractMetricsInterceptor {

    public ErrorCountingInterceptor(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (response.getStatus() >= 400) {
            Counter.builder("http_errors")
                    .tag("uri", request.getRequestURI())
                    .tag("status", String.valueOf(response.getStatus()))
                    .register(registry)
                    .increment();
        }
    }
}