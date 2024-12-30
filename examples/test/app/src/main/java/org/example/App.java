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

        simpleExample();   
    }
    
    private static Tracer  getTracer() {

        OpenTelemetry otel = OpenTelemetryConfig.initialize();

        Tracer tracer = otel.getTracer("exampleTracer");
        return tracer;
    }
        
    public static void simpleExample() {
        
        // Get a Tracer instance
        Tracer tracer = getTracer();

        // Start a new span
        Span span = tracer.spanBuilder("exampleOperation").startSpan();

        try {
            // Simulate some work
            System.out.println("Performing a traced operation...");
            Thread.sleep(1650); // Simulated delay

            // Add attributes and events
            span.setAttribute("key", "value");
            span.addEvent("Operation started");
            span.setStatus(StatusCode.OK);
        } catch (InterruptedException e) {
            span.setStatus(StatusCode.ERROR);
            span.recordException(e);
        } finally {
            System.out.println("Ending traced operation..");
            // End the span
            span.end();           
        }

        try {
            Thread.sleep(1000);
        } catch(Exception e) { System.out.println("Interrupted"); }
    }

}




