package com.example.cinema_diary;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET(".")
    Call<MovieDetails> getMovieDetails(@Query("apikey") String apikey,@Query("t") String title);
}
