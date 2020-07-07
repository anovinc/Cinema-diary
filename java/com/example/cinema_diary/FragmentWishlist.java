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


public class FragmentWishlist extends Fragment {
   private EditText movieName;
   private Button addBtn;
   private RecyclerView recyclerView;
   private List<Movie2> movies;
   private MovieAdapter2 adapter;
   private Realm realm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.wishlist_fragment,container,false);
        movieName=view.findViewById(R.id.add_etWishlist);
        addBtn=view.findViewById(R.id.add_wishlist);
        recyclerView=view.findViewById(R.id.recycler_wishlistFragment);
        Realm.init(getContext());
        realm=Realm.getDefaultInstance();

        movies=new ArrayList<>();

       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter=new MovieAdapter2(movies);
        recyclerView.setAdapter(adapter);

        showData();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movieName.getText().toString().matches("")){
                    movieName.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    Toast.makeText(getContext(),"Please put movie name",Toast.LENGTH_SHORT).show();
                }
           else {   addData(movieName.getText().toString());
                movies.add(new Movie2(movieName.getText().toString()));
                adapter.notifyDataSetChanged();
                movieName.onEditorAction(EditorInfo.IME_ACTION_DONE);
                movieName.setText("");
            }

        }} );

        return view;
    }

    public void addData(String title){
        realm.beginTransaction();

        Movie2 movie=realm.createObject(Movie2.class);
        movie.setName(title);
        realm.commitTransaction();
    }

    public List<Movie2> showData(){
        RealmResults<Movie2> results= realm.where(Movie2.class).findAll();

        for(Movie2 m:results){
            movies.add(m);
        }
        return movies;
    }


    public void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}
