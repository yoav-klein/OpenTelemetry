# Docker Compose
---

An example of how to run Jaeger on another host (Docker container).

We set the endpoint for the traces endpoint (`http://<jaeger-host>:4318/v1/traces`) 
in the configuration of the SpanExporter
