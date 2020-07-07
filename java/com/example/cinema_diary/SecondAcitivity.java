package com.example.cinema_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondAcitivity extends AppCompatActivity {
    private TextView details;
    private ImageView poster;
    private API api;
    private String movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_acitivity);
        details = (TextView) findViewById(R.id.details);
        poster=(ImageView) findViewById(R.id.poster);

       Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.omdbapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
       Intent intent=getIntent();
       movie=intent.getStringExtra(MovieAdapter.MY_KEY);
       movie=intent.getStringExtra(MovieAdapter2.MY_KEY);

        api = retrofit.create(API.class);
        getSupportActionBar().hide();

        getMovieDetail();

    }

    private void getMovieDetail() {
        Call<MovieDetails> call = api.getMovieDetails("64e32e15",movie);

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if(!response.isSuccessful()){
                    details.setText("Message: "+response.message());



                }
            else {
                    MovieDetails detail = response.body();


                    String content = "";
                    content += "Title: " + detail.getTitle() + "\n\n";
                    content += "Director: " + detail.getDirector() + "\n\n";
                    content += "Year: " + detail.getYear() + "\n\n";
                    content += "Actors: " + detail.getActors() + "\n\n";
                    content += "Genre: " + detail.getGenre() + "\n\n";
                    content += "Plot: " + detail.getPlot() + "\n\n";
                    content += "IMDB rating: " + detail.getRating() + "\n\n";

                    details.append(content);
                    Picasso.get().load(detail.getPoster()).into(poster);


                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                details.setText(t.getMessage());

            }
        });



    }
}
