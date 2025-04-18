package com.example.lutemongo.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.R;
import com.example.lutemongo.filehandling.LoadLutemonsFromFile;
import com.example.lutemongo.ui.RecyclerViewUtil;
import com.example.lutemongo.ui.UIHandler;



public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize the UIHandler
        UIHandler uiHandler = new UIHandler(this);

        // Set up button listeners for HomeActivity
        uiHandler.setupHomeActivityButtons(this);

        // Setup the RecyclerView for Home
        RecyclerView recyclerView = RecyclerViewUtil.setupRecyclerView(this, R.id.RecyclerViewHome);

        //Load lutemons from file
        LoadLutemonsFromFile loader = new LoadLutemonsFromFile(recyclerView, R.layout.item_layout_home);
        loader.loadLutemonData();
    }
}