package com.example.lutemongo.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.R;
import com.example.lutemongo.actions.TrainingArea;
import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.lutemonhandling.Storage;
import com.example.lutemongo.ui.LutemonAdapter;
import com.example.lutemongo.ui.RecyclerViewUtil;
import com.example.lutemongo.ui.UIHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * TrainingActivity handles training ui actions
 */
public class TrainingActivity extends AppCompatActivity {

    private boolean isLoading = false;

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

        // Initialize Storage
        Storage storage = Storage.getInstance();

        // List for the training lutemon
        List<Lutemon> trainingLutemonsList = new ArrayList<>();
        Lutemon trainingLutemon = storage.getTrainingLutemon();
        trainingLutemonsList.add(trainingLutemon);

        // Initialize adapter
        LutemonAdapter adapter = new LutemonAdapter(trainingLutemonsList, lutemon -> {
        }, R.layout.item_layout_home);


        // Put the training lutemon to recyclerview
        recyclerView.setAdapter(adapter);

        // Empty training (move lutemon back home)
        Button moveToHomeButton = findViewById(R.id.moveHomeButton);
        moveToHomeButton.setOnClickListener(v -> {
           trainingLutemonsList.clear();
           recyclerView.setAdapter(adapter);
        });

        // Define UI elements
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button trainButton = findViewById(R.id.trainButton);

        // Train button logic
        trainButton.setOnClickListener(v -> {
            if (isLoading) return; // If already loading return

            TrainingArea.train(); // Call train method
            isLoading = true;
            trainButton.setEnabled(false);
            progressBar.setProgress(0);
            // Define countdown
            CountDownTimer countDownTimer = new CountDownTimer(6000, 60) {
                @Override // Method for one tick
                public void onTick(long millisUntilFinished) {
                    int progress = (int) ((6000 - millisUntilFinished) / 60);
                    progressBar.setProgress(progress);
                }

                @SuppressLint("UnsafeIntentLaunch")
                @Override // Method for when the progress bar finishes
                public void onFinish() {
                    progressBar.setProgress(100);
                    trainButton.setEnabled(true);
                    isLoading = false;
                    finish(); // Closes current instance
                    startActivity(getIntent());
                }
            };
            countDownTimer.start(); // Start the countdown timer
        });
    }
}