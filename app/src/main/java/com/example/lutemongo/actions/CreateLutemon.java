package com.example.lutemongo.actions;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.lutemonhandling.Storage;
import com.google.android.material.textfield.TextInputEditText;

public class CreateLutemon {
    private final Context context;
    private Storage storage;

    public CreateLutemon(Context context) {
        this.context = context;
        // Get the singleton instance
        this.storage = Storage.getInstance();
    }

    /**
     * Creates a Lutemon based on input parameters
     * @param nameInput The TextInputEditText containing the name
     * @param colorGroup The RadioGroup containing color selection
     * @return boolean indicating if creation was successful
     */
    public boolean createNewLutemon(TextInputEditText nameInput, RadioGroup colorGroup) {
        if (nameInput == null || colorGroup == null) {
            return false;
        }

        // Get the name from input
        String name = nameInput.getText().toString().trim();

        // Validate name
        if (name.isEmpty()) {
            Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Get selected radio button ID
        int selectedId = colorGroup.getCheckedRadioButtonId();

        // Check if a color was selected
        if (selectedId == -1) {
            Toast.makeText(context, "Please select a color", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Find the radio button by selected id
        RadioButton selectedRadioButton = colorGroup.findViewById(selectedId);
        String color = selectedRadioButton.getText().toString().toLowerCase();

        // Create Lutemon with appropriate stats based on color
        Lutemon newLutemon = createLutemonByColor(name, color);

        // Add to storage
        boolean added = storage.addLutemon(newLutemon);

        // If this is the first Lutemon, automatically add to team
        if (added && !storage.hasTeamLutemon()) {
            storage.setTeamLutemon(newLutemon);
            Toast.makeText(context, "First Lutemon added to your team!", Toast.LENGTH_SHORT).show();
        }

        // Save to disk after creating a new Lutemon
        if (added) {
            if (storage.saveToSharedPreferences(context)) {
                Toast.makeText(context, "Lutemon created and saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Lutemon created but couldn't be saved", Toast.LENGTH_SHORT).show();
            }
        }

        return added;
    }

    /**
     * Creates a Lutemon with stats based on its color
     * @param name The name of the Lutemon
     * @param color The color of the Lutemon
     * @return Created Lutemon object
     */
    private Lutemon createLutemonByColor(String name, String color) {
        int attack;
        int maxHealth;
        int defense;

        // Set attributes based on color
        switch (color) {
            case "red":
                attack = 20;
                maxHealth = 5;
                defense = 5;
                break;
            case "blue":
                attack = 5;
                maxHealth = 20;
                defense = 5;
                break;
            case "green":
                attack = 5;
                maxHealth = 5;
                defense = 20;
                break;
            case "yellow":
                attack = 10;
                maxHealth = 10;
                defense = 10;
                break;
            case "lila":
                attack = 10;
                maxHealth = 15;
                defense = 5;
                break;
            default:
                attack = 1;
                maxHealth = 1;
                defense = 1;
                break;
        }

        // Initialize Lutemon with full health equal to max health and 0 experience
        return new Lutemon(name, color, attack, maxHealth, maxHealth, defense, 0);
    }

    /**
     * Get reference to storage instance
     * @return Storage instance
     */
    public Storage getStorage() {
        return storage;
    }
}
