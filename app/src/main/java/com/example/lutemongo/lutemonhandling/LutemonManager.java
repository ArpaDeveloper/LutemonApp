package com.example.lutemongo.lutemonhandling;

import android.content.Context;

import com.example.lutemongo.ui.ErrorHandler;
import com.example.lutemongo.ui.LutemonAdapter;

import java.util.ArrayList;
import java.util.List;

public class LutemonManager {


    private LutemonAdapter adapter;
    private Storage storage;
    private Context context;

    // Add a constructor to initialize the fields
    public LutemonManager(Context context, LutemonAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
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

}
