package com.example.lutemongo.actions;

import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.lutemonhandling.Storage;

public class TrainingArea {

    public static void train() {
        //Initialize Storage
        Storage storage = Storage.getInstance();
        Lutemon trainingLutemon = storage.getTrainingLutemon();

        //Every 5 exp gain 1atk,1def,1hp
        if(trainingLutemon.getExperience() % 5 == 0 && trainingLutemon.getExperience() != 0){
            trainingLutemon.setAttack(trainingLutemon.getAttack()+1);
            trainingLutemon.setDefence(trainingLutemon.getDefence()+1);
            trainingLutemon.setMaxHealth(trainingLutemon.getMaxHealth()+1);
            trainingLutemon.setExperience(trainingLutemon.getExperience()+1);
        }
        else{
            trainingLutemon.setExperience(trainingLutemon.getExperience()+1);
        }
    }

}
