package dev.lucas.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.format;

public class IMDBClient {

    private final HttpClient client;
    private final String TOP_250_URL;

    public IMDBClient(final String apiKey) {
        client = HttpClient.newBuilder().build();
        TOP_250_URL = format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey);
    }

    public HttpResponse<String> getTop250Movies() {
        try {
            return this.client.send(HttpRequest
                    .newBuilder(URI.create(TOP_250_URL))
                    .GET()
                    .build(),
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
