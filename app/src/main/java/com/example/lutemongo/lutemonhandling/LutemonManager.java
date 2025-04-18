package com.example.lutemongo.lutemonhandling;

import android.content.Context;


import com.example.lutemongo.ui.ErrorHandler;
import com.example.lutemongo.ui.LutemonAdapter;

import java.util.ArrayList;
import java.util.List;

public class LutemonManager {


    Storage storage;
    private final Context context;

    // Add a constructor to initialize the fields
    public LutemonManager(Context context, LutemonAdapter adapter) {
        this.context = context;
        this.storage = Storage.getInstance();
    }

    public void moveSelectedLutemonsToTraining() {
        List<Lutemon> lutemons = storage.getLutemons();
        List<Lutemon> selectedLutemons = new ArrayList<>();
        List<Lutemon> trainingLutemonList = new ArrayList<>();

        // Find selected lutemons
        for (Lutemon lutemon : lutemons) {
            if (lutemon.isSelected()) {
                selectedLutemons.add(lutemon);
            }
        }

        if(selectedLutemons.size() > 1){
            ErrorHandler.showError(context, "Select only 1");
        }
        else{
            // Move to training
            for (Lutemon lutemon : selectedLutemons) {
                storage.setTrainingLutemon(lutemon);
            }
            Lutemon trainingLutemon = storage.getTrainingLutemon(); // Get the single Lutemon object
            trainingLutemonList.add(trainingLutemon);
        }
    }

    public void moveSelectedLutemonsToFight() {
        List<Lutemon> lutemons = storage.getLutemons();
        List<Lutemon> selectedLutemons = new ArrayList<>();

        // Find selected lutemons
        for (Lutemon lutemon : lutemons) {
            if (lutemon.isSelected()) {
                selectedLutemons.add(lutemon);
            }
        }

        if (selectedLutemons.size() != 2) {
            ErrorHandler.showError(context, "Choose 2");
        } else {
            // Move to fight
            Lutemon teamLutemon = selectedLutemons.get(0);
            Lutemon enemyLutemon = selectedLutemons.get(1);
            if (storage.setBattleLutemons(teamLutemon, enemyLutemon)) {
                // Save the selections to disk
                storage.saveToSharedPreferences(context);
            }
        }
    }
}
