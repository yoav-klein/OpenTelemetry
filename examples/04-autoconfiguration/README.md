# Autoconfiguration of SDK
---

Here we demonstrate using zero-code configuration of the SDK rather than programmatically configuring it.

In `OpenTelemetryConfig` class, we use `AutoConfiguredOpenTelemetrySdk` to get an instance of and auto configured `OpenTelemetrySdk`.
The `OpenTelemetrySdk` is configured using environment variables/system properties. In our case, we use environment variables.

We do modify the SpanExporter programatically to use Jaeger SpanExporter. We return a composite of both the Jaeger SpanExporter
and the Console SpanExporter to write spans to the screen.

Run the `run.sh` script to run the application. In this script we define environment variables
which are read by the autoconfigure module to automatically configure the SDK.


See (!Here)[https://opentelemetry.io/docs/languages/java/configuration/#configurablespanexporterprovider]
