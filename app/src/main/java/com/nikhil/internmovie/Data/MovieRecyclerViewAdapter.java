package com.nikhil.internmovie.Data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nikhil.internmovie.Activities.movieDetailAct;
import com.nikhil.internmovie.Model.Movie;
import com.nikhil.internmovie.R;
import com.nikhil.internmovie.Util.Favorite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Movie> movieList;
    private String imgl="https://image.tmdb.org/t/p/w500/";


    public MovieRecyclerViewAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }


    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movierow,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHolder holder, int position) {
        Movie movie=movieList.get(position);

        String posterLink=imgl+movie.getPoster().substring(1);
        Log.d("img",posterLink);

        holder.title.setText(movie.getTitle());
        holder.vote.setText("Average vote: "+movie.getVote());
        holder.released.setText("Released on: "+movie.getReleased());

        Picasso.get().load(posterLink).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView vote;
        TextView released;
        ImageView poster;
        ImageView fav;

        public ViewHolder(@NonNull final View itemView, final Context ctx) {
            super(itemView);

            context=ctx;

            title=(TextView) itemView.findViewById(R.id.movieTitleID);
            vote=(TextView) itemView.findViewById(R.id.voteID);
            released=(TextView) itemView.findViewById(R.id.releasedID);
            fav=(ImageView) itemView.findViewById(R.id.fav);

            poster=(ImageView) itemView.findViewById(R.id.movieImageID);

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String favTitle=movieList.get(getAdapterPosition()).getTitle();

                    Favorite favorite=new Favorite();
                    favorite.addElement(favTitle);

//                    Favorite.addElement(favTitle);
                    Toast.makeText(context,"Added to favorites!",Toast.LENGTH_LONG).show();


                    /** add to favs
                     */

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Movie movieIntent=movieList.get(getAdapterPosition());

                    Intent intent=new Intent(context, movieDetailAct.class);
                    intent.putExtra("movie", movieIntent);
                    ctx.startActivity(intent);

                }
            });

        }

        @Override
        public void onClick(View view) {

        }
    }
}
