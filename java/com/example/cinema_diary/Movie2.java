package com.example.cinema_diary;

import io.realm.RealmObject;

public class Movie2 extends RealmObject {
    private String name;

    public Movie2(String name) {
        this.name = name;
    }
    public Movie2(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
