package com.example.cinema_diary;

import io.realm.RealmObject;

public class Movie extends RealmObject  {
    private String name;
    private String rating;
    public Movie(){}
    public Movie(String name){
        this.name=name;



    }
    public Movie(String name,String rating){
        this.name=name;
        this.rating=rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
