# Basic example
---

This is a very minimal example. We are creating a single span, and export it using the `OtlpHttpSpanExporter` 
to export using OTLP.

The default of OtlpHttpSpanExporter is to send to `http://localhost:4318`

Jaeger speaks OTLP, so when you run Jaeger locally, it will send spans to Jaeger.
