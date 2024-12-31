package org.example;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.exporter.logging.otlp.OtlpJsonLoggingSpanExporter;

public class OpenTelemetryConfig {

    public static OpenTelemetry initialize() {
       
        SimpleSpanProcessor spanProcessor = SimpleSpanProcessor.builder(OtlpJsonLoggingSpanExporter.create()).build();
        
        // Set up Tracer Provider
        Resource myApp = Resource.getDefault().toBuilder().put("service.name", "otlp-json-exporter").build();
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

