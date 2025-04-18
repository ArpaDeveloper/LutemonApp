package com.example.lutemongo.actions;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.lutemonhandling.Storage;

public class BattleSystem {
    private Context context;

    public BattleSystem(Context context) {
        this.context = context;
    }

    /**
     * Simulates a battle between two Lutemons.
     * @param teamLutemon The player's Lutemon.
     * @param enemyLutemon The enemy Lutemon.
     * @return The result of the battle as a string.
     */
    public String startBattleWithLutemon(Lutemon teamLutemon, Lutemon enemyLutemon) {
        if (teamLutemon == null || enemyLutemon == null) {
            return "Both Lutemons must be selected for the battle.";
        }

        // Reset health to max at start of battle
        teamLutemon.setHealth(teamLutemon.getMaxHealth());
        enemyLutemon.setHealth(enemyLutemon.getMaxHealth());

        Log.d("BattleSystem", "Before battle - Team: " + teamLutemon.getName() +
                " HP:" + teamLutemon.getHealth() + "/" + teamLutemon.getMaxHealth() +
                " ATK:" + teamLutemon.getAttack() + " DEF:" + teamLutemon.getDefence());
        Log.d("BattleSystem", "Before battle - Enemy: " + enemyLutemon.getName() +
                " HP:" + enemyLutemon.getHealth() + "/" + enemyLutemon.getMaxHealth() +
                " ATK:" + enemyLutemon.getAttack() + " DEF:" + enemyLutemon.getDefence());

        StringBuilder battleLog = new StringBuilder();
        battleLog.append("Battle Start!\n");

        int baseDamage;
        int variance;
        int damageToEnemy;
        int damageToTeam;

        // Counter to prevent infinite loops
        int maxTurns = 100;
        int turnCount = 0;
        java.util.Random random = new java.util.Random();

        // Simulate turns
        while (teamLutemon.getHealth() > 0 && enemyLutemon.getHealth() > 0 && turnCount < maxTurns) {
            turnCount++;

            baseDamage = Math.max(1, teamLutemon.getAttack() - enemyLutemon.getDefence());
            variance = random.nextInt(10) - 5; // -5 to +5
            damageToEnemy = Math.max(1, baseDamage + variance);

            enemyLutemon.setHealth(enemyLutemon.getHealth() - damageToEnemy);
            battleLog.append(teamLutemon.getName())
                    .append(" attacks ")
                    .append(enemyLutemon.getName())
                    .append(" for ")
                    .append(damageToEnemy)
                    .append(" damage. ")
                    .append(enemyLutemon.getName())
                    .append(" HP: ")
                    .append(enemyLutemon.getHealth())
                    .append("\n");

            if (enemyLutemon.getHealth() <= 0) {
                battleLog.append(enemyLutemon.getName()).append(" has been defeated!\n");
                break;
            }

            baseDamage = Math.max(1, enemyLutemon.getAttack() - teamLutemon.getDefence());
            variance = random.nextInt(10) - 5; // -5 to +5
            damageToTeam = Math.max(1, baseDamage + variance);

            teamLutemon.setHealth(teamLutemon.getHealth() - damageToTeam);
            battleLog.append(enemyLutemon.getName())
                    .append(" attacks ")
                    .append(teamLutemon.getName())
                    .append(" for ")
                    .append(damageToTeam)
                    .append(" damage. ")
                    .append(teamLutemon.getName())
                    .append(" HP: ")
                    .append(teamLutemon.getHealth())
                    .append("\n");

            if (teamLutemon.getHealth() <= 0) {
                battleLog.append(teamLutemon.getName()).append(" has been defeated!\n");
                break;
            }
        }

        if (turnCount >= maxTurns) {
            battleLog.append("Battle ended in a draw after ").append(maxTurns).append(" turns!\n");
        } else {
            // Determine winner
            if (teamLutemon.getHealth() > 0) {
                battleLog.append(teamLutemon.getName()).append(" wins the battle!\n");
                teamLutemon.setWins(enemyLutemon.getWins() + 1);
                // teamLutemon.setExperience(teamLutemon.getExperience() + 10);
                // battleLog.append(teamLutemon.getName()).append(" gained 10 experience points!\n");
            } else {
                battleLog.append(enemyLutemon.getName()).append(" wins the battle!\n");
                enemyLutemon.setWins(enemyLutemon.getWins() + 1);
            }
        }

        // Reset health for next time
        teamLutemon.setHealth(teamLutemon.getMaxHealth());
        enemyLutemon.setHealth(enemyLutemon.getMaxHealth());

        teamLutemon.setBattls(teamLutemon.getBattles() + 1);
        enemyLutemon.setBattls(teamLutemon.getBattles() + 1);

        // Save changes to storage
        if (context != null) {
            Storage.getInstance().saveToSharedPreferences(context);
        } else {
            Log.e("BattleSystem", "Context is null, cannot save battle results");
        }

        return battleLog.toString();
    }

    /**
     * Simulates a battle using Lutemons selected in Storage
     * @return The result of the battle as a string
     */
    public String startBattle() {
        Storage storage = Storage.getInstance();

        // Check if both Lutemons are selected
        if (!storage.areBothBattlelutemonsSelected()) {
            return "Both Lutemons must be selected for the battle.";
        }

        // Get the selected Lutemons from storage
        Pair<Lutemon, Lutemon> battlePair = storage.getBattleLutemons();
        return startBattleWithLutemon(battlePair.first, battlePair.second);
    }
}
