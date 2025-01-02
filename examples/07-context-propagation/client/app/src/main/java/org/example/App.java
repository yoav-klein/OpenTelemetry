/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {

    public static void main(String[] args) throws Exception {
        String serverEndpoint = System.getenv("SERVER_ENDPOINT");
        if(null == serverEndpoint) {
            throw new IllegalArgumentException("No server endpoint");
        }

        HttpClient client = HttpClient.newBuilder()
            .followRedirects(Redirect.NORMAL)
            .build();

        HttpRequest request = HttpRequest.newBuilder(URI.create(serverEndpoint)).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

    }
}