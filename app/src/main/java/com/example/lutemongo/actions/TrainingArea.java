package com.example.lutemongo.actions;

import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.lutemonhandling.Storage;

/**
 * TrainingArea class handles lutemon training
 */
public class TrainingArea {
    /**
     * Handles Lutemon training and how stats are gained
     */
    public static void train() {
        // Initialize Storage & fetch trainingLutemon
        Storage storage = Storage.getInstance();
        Lutemon trainingLutemon = storage.getTrainingLutemon();

        // After every 5 exp gain 1atk,1def,1hp and 1exp
        if(trainingLutemon.getExperience() % 5 == 0 && trainingLutemon.getExperience() != 0){
            trainingLutemon.setAttack(trainingLutemon.getAttack()+1);
            trainingLutemon.setDefence(trainingLutemon.getDefence()+1);
            trainingLutemon.setMaxHealth(trainingLutemon.getMaxHealth()+1);
            trainingLutemon.setExperience(trainingLutemon.getExperience()+1);
        }
        // Else add 1 exp
        else{
            trainingLutemon.setExperience(trainingLutemon.getExperience()+1);
        }
    }
}
