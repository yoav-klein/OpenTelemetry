# Context Propagation
---

In this example we demonstrate context propagation. We have a client and server applications, which communicate via HTTP.

We use the `W3CTraceContextPropagator` propagator to pass context between client and server.

The client and server both create a new span. The call from the client to the server is done within the scope
of the client's span, so the server's span is created as the child span of the client's span.

## Run

Run the docker compose
```
$ docker compose up -d [--build]
```

Then browse to the jaeger `http://localhost:8090` and you'll see the traces.