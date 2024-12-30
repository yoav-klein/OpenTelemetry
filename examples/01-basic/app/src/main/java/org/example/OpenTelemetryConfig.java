package org.example;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.resources.Resource;

public class OpenTelemetryConfig {

    public static OpenTelemetry initialize() {
        // Create Jaeger Exporter
        JaegerGrpcSpanExporter jaegerExporter = JaegerGrpcSpanExporter.builder()
            .setEndpoint("http://localhost:14250") // Replace with your Jaeger endpoint
            .build();
        
        SimpleSpanProcessor spanProcessor = SimpleSpanProcessor.builder(jaegerExporter).build();
        
        // Set up Tracer Provider
        Resource myApp = Resource.getDefault().toBuilder().put("service.name", "basic-example").build();
        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
            .setResource(myApp)
            .addSpanProcessor(spanProcessor)
            .build();

        // Set up OpenTelemetry SDK
        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
            .setTracerProvider(tracerProvider)
            .build();

        return openTelemetrySdk;
    }
}

