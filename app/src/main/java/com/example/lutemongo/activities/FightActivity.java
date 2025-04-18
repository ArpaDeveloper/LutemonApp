package com.example.lutemongo.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lutemongo.R;
import com.example.lutemongo.actions.BattleSystem;
import com.example.lutemongo.lutemonhandling.Storage;
import com.example.lutemongo.ui.UIHandler;

public class FightActivity extends AppCompatActivity {

    BattleSystem battleSystem = new BattleSystem(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        // Initialize the UIHandler
        UIHandler uiHandler = new UIHandler(this);

        // Setup buttons for this activity
        uiHandler.setupFightActivityButtons(this);

        // Initialize Storage
        Storage storage = Storage.getInstance();

        Button FightButton = findViewById(R.id.nextAttackButton);
        TextView battleText = findViewById(R.id.battlefieldText);
        FightButton.setOnClickListener(v -> {
            String battleinfo = battleSystem.startBattle();
            battleText.setText(battleinfo);
        });


    }
}
