/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.example;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.context.propagation.TextMapPropagator;

import java.net.InetSocketAddress;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;

public class App {

    private static TextMapGetter<HttpExchange> TEXT_MAP_GETTER = new HttpRequestGetter();

    private static Tracer  getTracer() {

        OpenTelemetry otel = OpenTelemetryConfig.initialize();

        Tracer tracer = otel.getTracer("exampleTracer");
        return tracer;
    }

    public static void main(String[] args) throws Exception {
        Tracer tracer = getTracer();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        ContextPropagators propagators = ContextPropagators.create(
            TextMapPropagator.composite(
                W3CTraceContextPropagator.getInstance(), W3CBaggagePropagator.getInstance()));

        server.createContext("/", new Handler(propagators));

        server.start();

    }

    private static class Handler implements HttpHandler {
        private ContextPropagators propagators;

        public Handler(ContextPropagators propagators) {
            this.propagators = propagators;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            OpenTelemetry otel = OpenTelemetryConfig.initialize();

            Tracer tracer = otel.getTracer("serverTracer");
            // debug - see headers
            System.out.println("=== headers");
            Iterable<String> headers = exchange.getRequestHeaders().keySet();

            for(String header : headers) {
                System.out.println(String.format("%s: %s", header, exchange.getRequestHeaders().get(header)));
            }

            Context extractedContext = propagators.getTextMapPropagator().extract(Context.current(), exchange, TEXT_MAP_GETTER);

            Span span = tracer.spanBuilder("/ GET").startSpan();;
            try(Scope scope = extractedContext.makeCurrent()) {
                System.out.println("Do work");
                
            } finally {
                String response = "Hello from server";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                span.end();
            }

        }

    }

    private static class HttpRequestGetter implements TextMapGetter<HttpExchange> {
        @Override
        public Iterable<String> keys(HttpExchange exchange) {
            return exchange.getRequestHeaders().keySet();
        }

        @Override
        public String get(HttpExchange exchange, String key) {
            if(exchange == null) {
                return null;
            }
            List<String> headers = exchange.getRequestHeaders().get(key);
            if(headers == null || headers.isEmpty()) {
                return null;
            }
            
            return headers.get(0);
        }
        
    }
}
