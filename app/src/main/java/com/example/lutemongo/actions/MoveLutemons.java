package com.example.lutemongo.actions;

import com.example.lutemongo.Lutemon;
import com.example.lutemongo.ui.LutemonAdapter;

import java.util.List;

public class MoveLutemons {


    private List<Lutemon> homeLutemons;
    private List<Lutemon> trainingLutemons;
    private LutemonAdapter lutemonAdapter;

    // Constructor to pass everything in
    public MoveLutemons(List<Lutemon> homeLutemons, List<Lutemon> trainingLutemons, LutemonAdapter adapter) {
        this.homeLutemons = homeLutemons;
        this.trainingLutemons = trainingLutemons;
        this.lutemonAdapter = adapter;
    }

    public void moveLutemonsTraining(){
        List<Lutemon> selected = lutemonAdapter.getSelectedLutemons();

        if (selected.isEmpty()) {
            return;
        }

        for (Lutemon l : selected) {
            l.setSelected(false); // Optional, reset checkbox
        }

        // Move to training list
        trainingLutemons.addAll(selected);
        homeLutemons.removeAll(selected);

        //Refresh adapter
        lutemonAdapter.updateLutemons(homeLutemons);
       // LutemonAdapter.updateLutemons(trainingLutemons);
    }
}
