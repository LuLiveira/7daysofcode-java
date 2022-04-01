package dev.lucas;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

        final var body = response.body();

        final var items = parseJsonMovies(body);

        items.getItems().stream().map(Movie::getTitle).forEach(System.out::println);
        items.getItems().stream().map(Movie::getImage).forEach(System.out::println);
        items.getItems().stream().map(Movie::getImDbRating).forEach(System.out::println);
        items.getItems().stream().map(Movie::getYear).forEach(System.out::println);
    }

    private static Items parseJsonMovies(String body) {
        return new Gson().fromJson(body, new TypeToken<Items>() {
        }.getType());
    }
}
