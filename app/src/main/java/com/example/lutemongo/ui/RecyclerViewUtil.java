package com.example.lutemongo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewUtil {

    // Method to setup the recyclerView
    public static RecyclerView setupRecyclerView(AppCompatActivity activity, int recyclerViewId){
        // Find the recyclerview and set it layout and size.
        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        return recyclerView;
    }
}
