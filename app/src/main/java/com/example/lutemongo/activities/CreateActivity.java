package com.example.lutemongo.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lutemongo.R;
import com.example.lutemongo.actions.CreateLutemon;
import com.example.lutemongo.ui.UIHandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * CreateActivity handles create ui actions
 */
public class CreateActivity extends AppCompatActivity {
    //Variables
    private TextInputEditText nameInput;
    private RadioGroup colorGroup;
    private CreateLutemon createLutemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Initialize UI elements - fix the TextInputEditText reference
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        nameInput = (TextInputEditText) textInputLayout.getEditText();
        colorGroup = findViewById(R.id.colorRadioGroup);
        Button createButton = findViewById(R.id.create_button);

        //Initialize the UIHandler
        UIHandler uiHandler = new UIHandler(this);

        //Initialize CreateLutemon
        createLutemon = new CreateLutemon(this);

        //Set up create button listener
        createButton.setOnClickListener(v -> {
            if (createLutemon.createNewLutemon(nameInput, colorGroup)) {
                Toast.makeText(this, "Lutemon created successfully!", Toast.LENGTH_SHORT).show();
                clearInputs();
            }
        });

        //Set up button listeners for CreateActivity
        uiHandler.setupCreateActivityButtons(this);
    }

    /**
     * Clear input fields after successful creation
     */
    private void clearInputs() {
        if (nameInput != null) {
            nameInput.setText("");
        }
        if (colorGroup != null) {
            colorGroup.clearCheck();
        }
    }
}