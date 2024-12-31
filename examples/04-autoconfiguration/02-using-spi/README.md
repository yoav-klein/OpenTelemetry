# Autoconfiguration of SDK
---

Here, we use the SPI `ConfigurableSpanExporterProvider`. We implement this interface, register it in the `META-SERVICE/service` directory
and specify the name of the SpanExporter in the OTEL_TRACER_EXPORTER environment variable

This is a pretty advanced feature, you'll probably not need it.
