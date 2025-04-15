package com.example.lutemongo;

//Imports
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//This class handles the Movies in the recyclerview
public class LutemonAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    //Define a list of lutemons and a listener
    private List<Lutemon> lutemons;
    private final OnItemClickListener listener;

    //Defines click listener
    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    //Constructor
    public LutemonAdapter(List<Lutemon> lutemons, OnItemClickListener listener){
        this.lutemons = lutemons;
        this.listener = listener;
    }

    //This method creates the view holder to manage the UI
    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_home, parent, false);
        return new LutemonViewHolder(view, listener);
    }

    //This method gets the Movies position and binds it to right place
    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position){
        Lutemon lutemon = lutemons.get(position);
        holder.bind(lutemon);
    }

    //This method returns the amount of movies in the list
    @Override
    public int getItemCount(){
        return lutemons != null ? lutemons.size() : 0;
    }

    //This method calls to refresh the recycler view
    public void updateMovies(List<Lutemon> newLutemons){
        this.lutemons = newLutemons;
        notifyDataSetChanged();
    }

}
