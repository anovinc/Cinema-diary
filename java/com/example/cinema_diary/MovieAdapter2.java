package com.example.cinema_diary;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class MovieAdapter2 extends RecyclerView.Adapter<MovieAdapter2.MyViewHolder2> {
    private List<Movie2> moviesList;
    private Realm realm;
    public static final String MY_KEY = null;

    public MovieAdapter2(List<Movie2> data){
        moviesList=data;
        realm=Realm.getDefaultInstance();
    }
    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item2_movie, parent, false);

        return new MyViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, final int position) {
        final Movie2 movie = moviesList.get(position);
        holder.title.setText(movie.getName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(v.getContext(),SecondAcitivity.class);
               intent.putExtra(MY_KEY,moviesList.get(position).getName());
               v.getContext().startActivity(intent);
            }
        });
        holder.btnRmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                moviesList.remove(position);
                RealmResults<Movie2> results = realm.where(Movie2.class).findAll();
                results.deleteFromRealm(position);
                realm.commitTransaction();
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,moviesList.size());
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView title;
        ImageButton btnRmv;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.movie_nameWishlist);
            btnRmv=itemView.findViewById(R.id.remove_btnWishlist);
        }
    }
}
