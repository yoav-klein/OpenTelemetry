# Autoconfiguration of SDK
---

Here we demonstrate using zero-code configuration of the SDK rather than programmatically configuring it.

In `OpenTelemetryConfig` class, we use `AutoConfiguredOpenTelemetrySdk` to get an instance of and auto configured `OpenTelemetrySdk`.
The `OpenTelemetrySdk` is configured using environment variables/system properties. In our case, we use environment variables.

We use the `otlp` for the trace exporter. This will export spans using the OTLP protocol to `http://localhost:4318`.
Make sure you have Jaeger or a collector listening.


Run the `run.sh` script to run the application. In this script we define environment variables
which are read by the autoconfigure module to automatically configure the SDK.


See [Here](https://opentelemetry.io/docs/languages/java/configuration)
