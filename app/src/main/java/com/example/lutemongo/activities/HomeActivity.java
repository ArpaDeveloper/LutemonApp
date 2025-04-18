package com.example.lutemongo.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.R;
import com.example.lutemongo.filehandling.LoadLutemonsFromFile;
import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.lutemonhandling.LutemonManager;
import com.example.lutemongo.ui.LutemonAdapter;
import com.example.lutemongo.ui.RecyclerViewUtil;
import com.example.lutemongo.ui.UIHandler;

import java.util.List;


public class HomeActivity extends AppCompatActivity {

    private LutemonManager lutemonManager;
    private LutemonAdapter adapter;
    private List<Lutemon> lutemons;

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

        //Initialize LutemonManager

        LutemonAdapter adapter = new LutemonAdapter(lutemons, null,R.layout.item_layout_home);
        lutemonManager = new LutemonManager(this, adapter);

        Button moveToTrainingButton = findViewById(R.id.moveTrainButton);
        moveToTrainingButton.setOnClickListener(v -> {
            if (lutemonManager != null) {
                lutemonManager.moveSelectedLutemonsToTraining(); // Safely call the method
            } else {
                // Handle the case where LutemonManager is still null
                Log.e("HomeActivity", "LutemonManager is not initialized.");
            }
        });
    }

}