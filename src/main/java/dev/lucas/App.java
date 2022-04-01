package dev.lucas;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.lucas.client.IMDBClient;

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

    public static void main(String[] args) {
        final var apiKey = System.getenv("API_KEY");
        log.info(format("API_KEY=%s", apiKey));

        final var response = new IMDBClient(apiKey).getTop250Movies().body();

        final var items = parseJsonMovies(response);

        items.getFieldList(Movie::getTitle);
        items.getFieldList(Movie::getImage);
        items.getFieldList(Movie::getImDbRating);
        items.getFieldList(Movie::getYear);
    }

    private static Items parseJsonMovies(String body) {
        return new Gson().fromJson(body, new TypeToken<Items>() {
        }.getType());
    }
}
