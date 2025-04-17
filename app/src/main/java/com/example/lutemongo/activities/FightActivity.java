package com.example.lutemongo.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lutemongo.R;
import com.example.lutemongo.ui.UIHandler;

public class FightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        // Initialize the UIHandler
        UIHandler uiHandler = new UIHandler(this);

        // Setup buttons for this activity
        uiHandler.setupFightActivityButtons(this);
    }
}