package com.example.lutemongo.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.R;
import com.example.lutemongo.filehandling.LoadLutemonsFromFile;
import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.lutemonhandling.LutemonManager;
import com.example.lutemongo.lutemonhandling.Storage;
import com.example.lutemongo.ui.RecyclerViewUtil;
import com.example.lutemongo.ui.UIHandler;

import java.util.List;

/**
 * StatisticActivity handles stats ui actions
 */
public class StatisticsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //Initialize the UIHandler
        UIHandler uiHandler = new UIHandler(this);

        //Set up button listeners for StatisticsActivity
        uiHandler.setupStatisticsActivityButtons(this);

        //Setup the RecyclerView for Stats
        RecyclerView recyclerView = RecyclerViewUtil.setupRecyclerView(this, R.id.RecyclerViewStats);

        //Initialize Storage
        Storage storage = Storage.getInstance();

        //Load the data from file
        LoadLutemonsFromFile loader = new LoadLutemonsFromFile(recyclerView, R.layout.item_layout_stats);
        loader.loadLutemonData();

        //Define UI
        TextView totalBattlesText = findViewById(R.id.StatsFillText1);
        TextView totalWinsText = findViewById(R.id.StatsFillText2);

        List<Lutemon> allLutemons = storage.getLutemons();
        int totalBattles = 0;
        int totalExp = 0;

        for (Lutemon lutemon : allLutemons) {
            totalBattles += lutemon.getBattles();
            totalExp += lutemon.getExperience();
        }

        totalBattlesText.setText("Total battles "+String.valueOf(totalBattles));
        totalWinsText.setText("Total training "+String.valueOf(totalExp));
    }
}
