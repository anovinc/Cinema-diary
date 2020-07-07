package com.example.cinema_diary;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;

import android.widget.TextView;



import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;




public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    private Realm realm;
    public static final String MY_KEY = null;







    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,rating;
        public ImageButton delete;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.movie_name);
            delete=(ImageButton) view.findViewById(R.id.remove_btn);
            rating=(TextView) view.findViewById(R.id.rating_TV);




        }

    }


    public MovieAdapter(List<Movie> data){
        moviesList=data;
        realm=Realm.getDefaultInstance();


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Movie movie = moviesList.get(position);
        holder.title.setText(movie.getName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),SecondAcitivity.class);
                intent.putExtra(MY_KEY,moviesList.get(position).getName());
                v.getContext().startActivity(intent);
            }
        });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realm.beginTransaction();
                    moviesList.remove(position);
                    RealmResults<Movie> results = realm.where(Movie.class).findAll();
                    results.deleteFromRealm(position);
                    realm.commitTransaction();
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,moviesList.size());
                    notifyDataSetChanged();

                }
            });
       holder.rating.setText("My rating: "+moviesList.get(position).getRating());




    }



    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}
