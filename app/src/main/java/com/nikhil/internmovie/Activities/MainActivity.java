package com.nikhil.internmovie.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nikhil.internmovie.Data.MovieRecyclerViewAdapter;
import com.nikhil.internmovie.Model.Movie;
import com.nikhil.internmovie.R;
import com.nikhil.internmovie.Util.Favorite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    private List<Movie> movieList;
    private RequestQueue queue;
    public ArrayList<String> favList;
    private String url="https://api.themoviedb.org/3/movie/popular?api_key=4e44d9029b1270a757cddc766a1bcb63&language=en-US&page=2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue= Volley.newRequestQueue(this);

        movieList=new ArrayList<>();
        movieList=getMovies();


        recyclerView=(RecyclerView) findViewById(R.id.movieRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieRecyclerViewAdapter=new MovieRecyclerViewAdapter(this,movieList);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        movieRecyclerViewAdapter.notifyDataSetChanged();


    }

    private List<Movie> getMovies() {
        movieList.clear();

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("status: ","In onResponse");
                        try {
                            JSONArray moviesArray=response.getJSONArray("results");
                            Log.d("status: ","In try");
                            for(int i=0;i<moviesArray.length();i++){
                                JSONObject movieObj=moviesArray.getJSONObject(i);
                                Log.d("Movies check",movieObj.getString("title"));

                                Movie movie= new Movie();
                                movie.setTitle(movieObj.getString("title"));
                                movie.setVote(movieObj.getString("vote_average"));
                                movie.setReleased(movieObj.getString("release_date"));
                                movie.setPoster(movieObj.getString("poster_path"));
                                movie.setOverview(movieObj.getString("overview"));

                                movieList.add(movie);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ",error.toString());
            }
        });
        queue.add(jsonObjectRequest);

        return movieList;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.favID){

           Log.d("fav"," ");
        }

        return super.onOptionsItemSelected(item);
    }
}