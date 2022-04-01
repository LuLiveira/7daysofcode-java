package dev.lucas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.net.http.HttpClient.Version.HTTP_1_1;

public class App {
    final static Logger log = Logger.getAnonymousLogger();

    public static void main(String[] args) throws IOException, InterruptedException {
        final var apiKey = System.getenv("API_KEY");
        log.info(format("API_KEY=%s", apiKey));

        final var imdbUrl = format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey);
        log.info(format("IMDB_URL=%s", imdbUrl));

        final var uri = URI.create(imdbUrl);

        final var request = HttpRequest
                .newBuilder(uri)
                .GET()
                //.timeout(Duration.ofSeconds(1)) //opcional
                .version(HTTP_1_1) //opcional
                .build();

        final var client = HttpClient.newBuilder().build();

        final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(format("RESPONSE_STATUS_CODE=%s", response.statusCode()));

        System.out.println(response.body());
    }
}
