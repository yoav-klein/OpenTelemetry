/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.StatusCode;

import io.opentelemetry.context.Context;
import io.opentelemetry.context.ContextKey;
import io.opentelemetry.context.Scope;

import io.opentelemetry.api.OpenTelemetry;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    
    public static void main(String[] args) {

        spanParenting();   
    }
    
    private static Tracer  getTracer() {

        OpenTelemetry otel = OpenTelemetryConfig.initialize();

        Tracer tracer = otel.getTracer("exampleTracer");
        return tracer;
    }

    public static void spanParenting() {
        Tracer tracer = getTracer();
        Span span = tracer.spanBuilder("rootSpan").startSpan();
        try {
            // Start a child span, explicitly setting the parent.
            Span childSpan = tracer.spanBuilder("span child")
                // Explicitly set parent.
                .setParent(span.storeInContext(Context.current()))
                .startSpan();
            // Call makeCurrent(), adding childSpan to Context.current(). Spans created inside the scope
            // will have their parent set to childSpan.
            try (Scope childSpanScope = childSpan.makeCurrent()) {
                // Call another method which creates a span. The span's parent will be childSpan since it is
                // started in the childSpan scope.
                doWork();
            } finally {
                childSpan.end();
            }
        } finally {
          span.end();
        }
    }
    
    private static int doWork() {
        Span doWorkSpan = tracer.spanBuilder("doWork").startSpan();
        try (Scope scope = doWorkSpan.makeCurrent()) {
            int result = 0;
            for (int i = 0; i < 10; i++) {
                result += i;
            }
            return result;
        } finally {
            doWorkSpan.end();
        }
    }

}




