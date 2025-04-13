package com.example.lutemongo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

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