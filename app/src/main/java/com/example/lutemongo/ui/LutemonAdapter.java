package com.example.lutemongo.ui;

//Imports
import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.lutemonhandling.Lutemon;

//This class handles the lutemons in the recyclerview
public class LutemonAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    //Define a list of lutemons and a listener
    private List<Lutemon> lutemons;
    private final OnItemClickListener listener;
    private final int layoutResId;

    //Defines click listener
    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    //Constructor
    public LutemonAdapter(List<Lutemon> lutemons, OnItemClickListener listener, int layoutResId){
        this.lutemons = lutemons;
        this.listener = listener;
        this.layoutResId = layoutResId;
    }

    //This method creates the view holder to manage the UI
    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new LutemonViewHolder(view, listener, layoutResId);
    }

    //This method gets the Lutemons position and binds it to right place
    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position){
        Lutemon lutemon = lutemons.get(position);
        holder.bind(lutemon, layoutResId);
    }

    //This method returns the amount of movies in the list
    @Override
    public int getItemCount(){
        return lutemons != null ? lutemons.size() : 0;
    }

    //This method calls to refresh the recycler view
    public void updateLutemons(List<Lutemon> newLutemons){
        this.lutemons = newLutemons;
        notifyDataSetChanged();
    }

    //Method to get the selected lutemons
    public List<Lutemon> getSelectedLutemons() {
        List<Lutemon> selected = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.isSelected()) {
                selected.add(lutemon);
            }
        }
        return selected;
    }

}
