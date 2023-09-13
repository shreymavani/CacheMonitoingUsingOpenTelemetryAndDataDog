package org.example;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

public class GuavaCacheExample {
    public static void main(String[] args) {
        // Initialize OpenTelemetry
        OpenTelemetry openTelemetry = OpenTelemetryInitializer.initOpenTelemetry();
        // Create a Guava Cache
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();

        // Retrieve a value from the cache
        String key = "example-key";
        String cachedValue = cache.getIfPresent(key);

        // Update or add an item to the cache
        if (cachedValue == null) {
            cache.put(key, "example-value");
        } else {
            cache.put(key, "updated-value");
        }

        // Create a span to record cache-related telemetry
        Tracer tracer = GlobalOpenTelemetry.getTracer("cache-monitor-tracer");
        Span span = tracer.spanBuilder("cache-operation").startSpan();

        try {
            // Record cache operation details in the span
            span.setAttribute("cache.key", key);
            span.setAttribute("cache.action", cachedValue == null ? "add" : "update");
        } finally {
            // End the span
            span.end();
        }
    }
}
