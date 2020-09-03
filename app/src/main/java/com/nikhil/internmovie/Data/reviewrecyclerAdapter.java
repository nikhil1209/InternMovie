package com.nikhil.internmovie.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nikhil.internmovie.R;

import java.util.ArrayList;

public class reviewrecyclerAdapter extends RecyclerView.Adapter<com.nikhil.internmovie.Data.videoRecyclerAdapter.ViewHolder> {
    private ArrayList<Integer> arrayList;

    public reviewrecyclerAdapter() {
    }

    @NonNull
    @Override
    public com.nikhil.internmovie.Data.videoRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.review,parent,false);
        return new com.nikhil.internmovie.Data.videoRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.nikhil.internmovie.Data.videoRecyclerAdapter.ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }
}