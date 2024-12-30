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
import io.opentelemetry.sdk.trace.export.SpanExporter;

import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;

public class OpenTelemetryConfig {
  public static OpenTelemetry initialize() {
    return AutoConfiguredOpenTelemetrySdk.builder()
    .addSpanExporterCustomizer((spanExporter, configProperties) -> {
      SpanExporter jaeger =  JaegerGrpcSpanExporter.builder()
      .setEndpoint("http://localhost:14250") // Replace with your Jaeger endpoint
      .build();

      //return SpanExporter.composite(jaeger, spanExporter);
      return jaeger;
    })
    .build().getOpenTelemetrySdk();
  }
}


