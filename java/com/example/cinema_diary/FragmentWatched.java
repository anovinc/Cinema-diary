package com.example.cinema_diary;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class FragmentWatched extends Fragment {
    private List<Movie> movies;
    private RecyclerView recycler;
    private MovieAdapter adapter;
    private Button btnAdd;
    private EditText ETmovie,rating;
    private Realm realm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.watched_fragment, container, false);
        recycler = view.findViewById(R.id.recycler);
        btnAdd = view.findViewById(R.id.add_button);
        ETmovie = view.findViewById(R.id.add_et);
        rating=view.findViewById(R.id.add_rating);

        Realm.init(getContext());
        realm=Realm.getDefaultInstance();






        movies = new ArrayList<>();

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(movies);
        recycler.setAdapter(adapter);
        showData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((ETmovie.getText().toString().matches(""))||(rating.getText().toString().matches(""))){
                    ETmovie.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    Toast.makeText(getContext(),"Movie title is invalid or you didin't put your rating, please put correct information",Toast.LENGTH_SHORT).show();
                }
               else{addData(ETmovie.getText().toString(),rating.getText().toString());
                movies.add(new Movie(ETmovie.getText().toString(),rating.getText().toString()));
                adapter.notifyDataSetChanged();
                ETmovie.onEditorAction(EditorInfo.IME_ACTION_DONE);
                ETmovie.setText("");
                rating.onEditorAction(EditorInfo.IME_ACTION_DONE);
                rating.setText("");
                }}
        } );

        return view;
    }

    public void addData(String title,String rating){
        realm.beginTransaction();

        Movie movie=realm.createObject(Movie.class);
        movie.setName(title);
        movie.setRating(rating);
        realm.commitTransaction();
    }

    public List<Movie> showData(){
        RealmResults<Movie>results= realm.where(Movie.class).findAll();

        for(Movie m:results){
            movies.add(m);
        }
    return movies;
    }


    public void onDestroy(){
        super.onDestroy();
        realm.close();
    }

}








