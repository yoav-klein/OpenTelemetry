package org.example;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.SimpleLogRecordProcessor;
import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogRecordExporter;

public class OpenTelemetryConfig {

    public static OpenTelemetry initialize() {

        String otelExporterEndpoint = System.getenv("OTEL_EXPORTER_OTLP_ENDPOINT");
        
        if(otelExporterEndpoint == null) {
            otelExporterEndpoint = "http://localhost:4318";
        }

        String otelTracesEndpoint = otelExporterEndpoint + "/v1/traces";
        String otelLoggingEndpoint = otelExporterEndpoint + "/v1/logs";
       
        SimpleSpanProcessor spanProcessor = SimpleSpanProcessor.builder(OtlpHttpSpanExporter.builder()
                .setEndpoint(otelTracesEndpoint)
                .build())
            .build();
        
        // Set up Tracer Provider
        Resource myApp = Resource.getDefault().toBuilder().put("service.name", "basic-example").build();

        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
            .setResource(myApp)
            .addSpanProcessor(spanProcessor)
            .build();

        OtlpHttpLogRecordExporter exporter = OtlpHttpLogRecordExporter.builder().setEndpoint(otelLoggingEndpoint).build();
        
        SdkLoggerProvider loggerProvider = SdkLoggerProvider.builder()
            .setResource(myApp)
            .addLogRecordProcessor(SimpleLogRecordProcessor.create(exporter))
            .build();

        // Set up OpenTelemetry SDK
        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
            .setTracerProvider(tracerProvider)
            .setLoggerProvider(loggerProvider)
            .build();

        return openTelemetrySdk;
    }
}
