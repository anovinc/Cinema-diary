package com.example.cinema_diary;

import com.google.gson.annotations.SerializedName;

public class MovieDetails  {
    @SerializedName("Title")
    private String title;
    @SerializedName("Poster")
    private String poster;
    @SerializedName("imdbRating")
    private String rating;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Actors")
    private String actors;
    @SerializedName("Director")
    private String director;
    @SerializedName("Year")
    private String year;
    @SerializedName("Genre")
    private String genre;

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getRating() {
        return rating;
    }

    public String getPlot() {
        return plot;
    }

    public String getActors() {
        return actors;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }
}
