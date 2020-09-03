package com.nikhil.internmovie.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikhil.internmovie.Data.MovieRecyclerViewAdapter;
import com.nikhil.internmovie.Data.reviewrecyclerAdapter;
import com.nikhil.internmovie.Data.videoRecyclerAdapter;
import com.nikhil.internmovie.Model.Movie;
import com.nikhil.internmovie.R;
import com.squareup.picasso.Picasso;

public class movieDetailAct extends AppCompatActivity {

    private Movie movie;
    private TextView title;
    private TextView release;
    private TextView over;
    private TextView vote;
    private ImageView img;
    private String imgl="https://image.tmdb.org/t/p/w500/";
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private videoRecyclerAdapter vidAdapter;
    private reviewrecyclerAdapter reviewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        setUpUI();

        movie=(Movie) getIntent().getSerializableExtra("movie");
        Log.d("movieIntent",movie.getTitle());

        title.setText(movie.getTitle());
        release.setText("Released on: "+movie.getReleased());
        over.setText("Summary: "+movie.getOverview());
        vote.setText("Average vote: "+movie.getVote());

        String posterLink=imgl+movie.getPoster().substring(1);

        Picasso.get().load(posterLink).placeholder(android.R.drawable.ic_btn_speak_now).into(img);

        recyclerView1=(RecyclerView) findViewById(R.id.trailRec);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        vidAdapter= new videoRecyclerAdapter();
        recyclerView1.setAdapter(vidAdapter);
        vidAdapter.notifyDataSetChanged();

        recyclerView2=(RecyclerView) findViewById(R.id.reviewRec);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        reviewAdapter= new reviewrecyclerAdapter();
        recyclerView2.setAdapter(reviewAdapter);
        reviewAdapter.notifyDataSetChanged();

    }

    private void setUpUI() {
        title=(TextView) findViewById(R.id.movieTitleIDDet);
        release=(TextView) findViewById(R.id.releasedIDDet);
        over=(TextView) findViewById(R.id.overviewDet);
        vote=(TextView) findViewById(R.id.voteIDDet);
        img=(ImageView) findViewById(R.id.movieImageIDDet);

    }
}