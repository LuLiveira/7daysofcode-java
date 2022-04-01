package dev.lucas;

public class Movie {

    private String title;
    private String image;
    private String imDbRating;
    private Integer year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", imDbRating='" + imDbRating + '\'' +
                ", year=" + year +
                '}';
    }
}
