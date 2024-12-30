package org.example;

import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.autoconfigure.spi.traces.ConfigurableSpanExporterProvider;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;

public class CustomSpanExporterProvider implements ConfigurableSpanExporterProvider {

  @Override
  public SpanExporter createExporter(ConfigProperties config) {
    // Callback invoked when OTEL_TRACES_EXPORTER includes the value from getName().
    
    return JaegerGrpcSpanExporter.builder()
            .setEndpoint("http://localhost:14250") // Replace with your Jaeger endpoint
            .build();
  }

  @Override
  public String getName() {
    return "jarger-exporter";
  }
}