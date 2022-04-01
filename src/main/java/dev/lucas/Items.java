package dev.lucas;

import java.util.List;
import java.util.function.Function;

public class Items {
    private List<Movie> items;

    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;
    }

    public void getFieldList(Function<Movie, ?> function) {
        this.items.stream().map(function).forEach(System.out::println);
    }
}
