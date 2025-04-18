package com.example.lutemongo.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.R;
import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.lutemonhandling.LutemonManager;
import com.example.lutemongo.lutemonhandling.Storage;
import com.example.lutemongo.ui.LutemonAdapter;
import com.example.lutemongo.ui.RecyclerViewUtil;
import com.example.lutemongo.ui.UIHandler;

import java.util.ArrayList;
import java.util.List;


public class TrainingActivity extends AppCompatActivity {

    private LutemonManager lutemonManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // Initialize the UIHandler
        UIHandler uiHandler = new UIHandler(this);

        // Set up button listeners for TrainingActivity
        uiHandler.setupTrainingActivityButtons(this);

        // Setup the RecyclerView for Training
        RecyclerView recyclerView = RecyclerViewUtil.setupRecyclerView(this, R.id.RecyclerViewTraining);

        //Initialize Storage
        Storage storage = Storage.getInstance();
        //List for the training lutemon
        List<Lutemon> trainingLutemonsList = new ArrayList<>();
        Lutemon trainingLutemon = storage.getTrainingLutemon(); // Get the single Lutemon object
        trainingLutemonsList.add(trainingLutemon);
        //Initialize adapter
        LutemonAdapter adapter = new LutemonAdapter(trainingLutemonsList, lutemon -> {
        }, R.layout.item_layout_home);
        lutemonManager = new LutemonManager(this, adapter);

        //Put the training lutemons to recyclerview
        recyclerView.setAdapter(adapter);

        Button moveToHomeButton = findViewById(R.id.moveHomeButton);
        moveToHomeButton.setOnClickListener(v -> {
           trainingLutemonsList.clear();
            recyclerView.setAdapter(adapter);
        });
    }


}