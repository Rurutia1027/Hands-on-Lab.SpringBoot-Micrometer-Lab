package com.hol.springbootmicrometer.metrics.aop;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MetricsAspect {

    private final MeterRegistry registry;

    public MetricsAspect(MeterRegistry registry) {
        this.registry = registry;
    }

    @Around("execution(* com.hol.springbootmicrometer.service.FooService.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().getName();

        Timer.Sample sample = Timer.start(registry);
        try {
            return pjp.proceed();
        } finally {
            sample.stop(Timer.builder("foo_service_methods")
                    .tag("method", method)
                    .description("Execution time of FooService methods")
                    .register(registry));

            Counter.builder("foo_service_calls")
                    .tag("method", method)
                    .description("Number of calls to FooService methods")
                    .register(registry)
                    .increment();
        }
    }
}