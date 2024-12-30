/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import java.util.ServiceLoader;

import io.opentelemetry.sdk.autoconfigure.spi.traces.ConfigurableSpanExporterProvider;

public class App 
{
    @WithSpan
    public static void bar() {
        System.out.println("Bar");
    }

    @WithSpan
    public static void foo() {
        System.out.println("Foo");
        bar();
    }

    public static void loadService() {
        ServiceLoader<ConfigurableSpanExporterProvider> serviceLoader = ServiceLoader.load(ConfigurableSpanExporterProvider.class);

        for(ConfigurableSpanExporterProvider p : serviceLoader) {
            System.out.println(p.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException
    {   
        loadService();
        //foo();
    }
}
