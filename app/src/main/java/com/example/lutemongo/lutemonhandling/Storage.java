package com.example.lutemongo.lutemonhandling;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Lutemon> lutemons;
    private Lutemon activeTeamLutemon;
    private Lutemon activeTrainingLutemon;
    private Lutemon activeEnemyLutemon;
    private static Storage instance;
    private static final String PREF_NAME = "lutemon_storage";
    private static final String KEY_LUTEMONS = "lutemons";
    private static final String KEY_TEAM_LUTEMON = "team_lutemon";
    private static final String KEY_ENEMY_LUTEMON = "enemy_lutemon";



    // Private constructor for singleton
    private Storage() {
        lutemons = new ArrayList<>();
        activeTeamLutemon = null;
        activeTrainingLutemon = null;
        activeEnemyLutemon = null;

    }

    // Singleton implementation
    public static synchronized Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    /**
     * Add a Lutemon to storage
     * @param lutemon The Lutemon to add
     * @return boolean indicating success
     */
    public boolean addLutemon(Lutemon lutemon) {
        if (lutemon == null) {
            return false;
        }
        // Add to storage collection
        return lutemons.add(lutemon);
    }

    /**
     * Get all stored Lutemons
     * @return List of stored Lutemons
     */
    public List<Lutemon> getLutemons() {
        return lutemons;
    }

    /**
     * Set the active Lutemon for the team (only 1 allowed)
     * @param lutemon Lutemon to add to team
     * @return boolean indicating success
     */
    public boolean setTeamLutemon(Lutemon lutemon) {
        if (lutemon == null) {
            return false;
        }

        // Verify the Lutemon exists in storage
        boolean exists = false;
        for (Lutemon storedLutemon : lutemons) {
            if (storedLutemon == lutemon) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            return false;
        }

        activeTeamLutemon = lutemon;
        return true;
    }

    /**
     * Set the active Lutemon for the enemy team
     * @param lutemon Lutemon to set as enemy
     * @return boolean indicating success
     */
    public boolean setEnemyLutemon(Lutemon lutemon) {
        if (lutemon == null) {
            return false;
        }

        // Verify the Lutemon exists in storage
        boolean exists = false;
        for (Lutemon storedLutemon : lutemons) {
            if (storedLutemon == lutemon) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            return false;
        }

        activeEnemyLutemon = lutemon;
        return true;
    }

    /**
     * Get active enemy Lutemon
     * @return Current active enemy Lutemon
     */
    public Lutemon getEnemyLutemon() {
        return activeEnemyLutemon;
    }

    /**
     * Check if there is an active enemy Lutemon
     * @return true if enemy has a Lutemon
     */
    public boolean hasEnemyLutemon() {
        return activeEnemyLutemon != null;
    }

    /**
     * Clear enemy Lutemon
     */
    public void removeEnemyLutemon() {
        activeEnemyLutemon = null;
    }

    /**
     * Set the active Lutemon for the training (only 1 allowed)
     * @param lutemon Lutemon to add to training
     * @return boolean indicating success
     */
    public boolean setTrainingLutemon(Lutemon lutemon) {
        if (lutemon == null) {
            return false;
        }

        // Verify the Lutemon exists in storage
        boolean exists = false;
        for (Lutemon storedLutemon : lutemons) {
            if (storedLutemon == lutemon) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            return false;
        }

        activeTrainingLutemon = lutemon;
        return true;
    }


    /**
     * Get active team Lutemon
     * @return Current active Lutemon
     */
    public Lutemon getTeamLutemon() {
        return activeTeamLutemon;
    }

    /**
     * Get active training Lutemon
     * @return Current active Lutemon
     */
    public Lutemon getTrainingLutemon() {
        return activeTrainingLutemon;
    }

    public void emptyTraining(){
        activeTrainingLutemon = null;
    }

    /**
     * Remove a Lutemon from storage
     * @param lutemon The Lutemon to remove
     * @return boolean indicating success
     */
    public boolean removeLutemon(Lutemon lutemon) {
        if (lutemon == null) {
            return false;
        }

        // If this is the active Lutemon, remove it from team
        if (lutemon == activeTeamLutemon) {
            activeTeamLutemon = null;
        }

        return lutemons.remove(lutemon);
    }

    /**
     * Find Lutemon by name
     * @param name Name to search for
     * @return Lutemon if found, null otherwise
     */
    public Lutemon findLutemonByName(String name) {
        if (name == null) {
            return null;
        }

        for (Lutemon lutemon : lutemons) {
            if (lutemon.getName().equals(name)) {
                return lutemon;
            }
        }
        return null;
    }

    /**
     * Clear all stored data
     */
    public void clearAll() {
        lutemons.clear();
        activeTeamLutemon = null;
        activeTrainingLutemon = null;
        activeEnemyLutemon = null;
    }

    /**
     * Get count of Lutemons in storage
     * @return Number of stored Lutemons
     */
    public int getLutemonCount() {
        return lutemons.size();
    }

    /**
     * Check if player has a Lutemon on their team
     * @return true if team has a Lutemon
     */
    public boolean hasTeamLutemon() {
        return activeTeamLutemon != null;
    }

    /**
     * Save storage data to SharedPreferences using JSON serialization
     * @param context Application context
     * @return true if saved successfully
     */
    public boolean saveToSharedPreferences(Context context) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            // Convert lutemons list to JSON using Gson
            Gson gson = new Gson();
            String lutemonsJson = gson.toJson(lutemons);
            editor.putString(KEY_LUTEMONS, lutemonsJson);

            // Save team Lutemon index
            int teamLutemonIndex = -1;
            if (activeTeamLutemon != null) {
                teamLutemonIndex = lutemons.indexOf(activeTeamLutemon);
            }
            editor.putInt(KEY_TEAM_LUTEMON, teamLutemonIndex);

            // Save enemy Lutemon index
            int enemyLutemonIndex = -1;
            if (activeEnemyLutemon != null) {
                enemyLutemonIndex = lutemons.indexOf(activeEnemyLutemon);
            }
            editor.putInt(KEY_ENEMY_LUTEMON, enemyLutemonIndex);

            return editor.commit();
        } catch (Exception e) {
            Log.e("Storage", "Error saving data: " + e.getMessage());
            return false;
        }
    }

    /**
     * Load storage data from SharedPreferences using JSON deserialization
     * @param context Application context
     * @return true if loaded successfully
     */
    public boolean loadFromSharedPreferences(Context context) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

            // Load lutemons list from JSON
            String lutemonsJson = prefs.getString(KEY_LUTEMONS, null);
            if (lutemonsJson != null) {
                Gson gson = new Gson();
                Type lutemonListType = new TypeToken<ArrayList<Lutemon>>(){}.getType();
                List<Lutemon> loadedLutemons = gson.fromJson(lutemonsJson, lutemonListType);
                if (loadedLutemons != null) {
                    lutemons = loadedLutemons;
                }
            }

            // Load team Lutemon
            int teamLutemonIndex = prefs.getInt(KEY_TEAM_LUTEMON, -1);
            if (teamLutemonIndex >= 0 && teamLutemonIndex < lutemons.size()) {
                activeTeamLutemon = lutemons.get(teamLutemonIndex);
            } else {
                activeTeamLutemon = null;
            }

            // Load enemy Lutemon
            int enemyLutemonIndex = prefs.getInt(KEY_ENEMY_LUTEMON, -1);
            if (enemyLutemonIndex >= 0 && enemyLutemonIndex < lutemons.size()) {
                activeEnemyLutemon = lutemons.get(enemyLutemonIndex);
            } else {
                activeEnemyLutemon = null;
            }

            return true;
        } catch (Exception e) {
            Log.e("Storage", "Error loading data: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if both battling Lutemons are selected
     * @return true if both player and enemy Lutemons are selected
     */
    public boolean areBothBattlelutemonsSelected() {
        return activeTeamLutemon != null && activeEnemyLutemon != null;
    }

    /**
     * Get both battle Lutemons as a pair
     * @return Pair of Lutemons where first is team Lutemon and second is enemy Lutemon
     */
    public Pair<Lutemon, Lutemon> getBattleLutemons() {
        return new Pair<>(activeTeamLutemon, activeEnemyLutemon);
    }

    /**
     * Clear both battle Lutemons
     */
    public void clearBattleLutemons() {
        activeTeamLutemon = null;
        activeEnemyLutemon = null;
    }

    /**
     * Set both battle Lutemons at once
     * @param teamLutemon Player's Lutemon
     * @param enemyLutemon Enemy's Lutemon
     * @return true if both were set successfully
     */
    public boolean setBattleLutemons(Lutemon teamLutemon, Lutemon enemyLutemon) {
        boolean teamSet = setTeamLutemon(teamLutemon);
        boolean enemySet = setEnemyLutemon(enemyLutemon);
        return teamSet && enemySet;
    }
}
