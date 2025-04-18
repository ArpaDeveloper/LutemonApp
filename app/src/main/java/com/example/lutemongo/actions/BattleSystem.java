package com.example.lutemongo.actions;

import com.example.lutemongo.lutemonhandling.Lutemon;

public class BattleSystem {

    /**
     * Simulates a battle between two Lutemons.
     * @param teamLutemon The player's Lutemon.
     * @param enemyLutemon The enemy Lutemon.
     * @return The result of the battle as a string.
     */
    public String startBattle(Lutemon teamLutemon, Lutemon enemyLutemon) {
        if (teamLutemon == null || enemyLutemon == null) {
            return "Both Lutemons must be selected for the battle.";
        }

        StringBuilder battleLog = new StringBuilder();
        battleLog.append("Battle Start!\n");

        // Simulate turns
        while (teamLutemon.getHealth() > 0 && enemyLutemon.getHealth() > 0) {
            // Team Lutemon attacks
            int damageToEnemy = Math.max(0, teamLutemon.getAttack() - enemyLutemon.getDefence());
            enemyLutemon.setHealth(enemyLutemon.getHealth() - damageToEnemy);
            battleLog.append(teamLutemon.getName())
                    .append(" attacks ")
                    .append(enemyLutemon.getName())
                    .append(" for ")
                    .append(damageToEnemy)
                    .append(" damage.\n");

            if (enemyLutemon.getHealth() <= 0) {
                battleLog.append(enemyLutemon.getName()).append(" has been defeated!\n");
                break;
            }

            // Enemy Lutemon attacks
            int damageToTeam = Math.max(0, enemyLutemon.getAttack() - teamLutemon.getDefence());
            teamLutemon.setHealth(teamLutemon.getHealth() - damageToTeam);
            battleLog.append(enemyLutemon.getName())
                    .append(" attacks ")
                    .append(teamLutemon.getName())
                    .append(" for ")
                    .append(damageToTeam)
                    .append(" damage.\n");

            if (teamLutemon.getHealth() <= 0) {
                battleLog.append(teamLutemon.getName()).append(" has been defeated!\n");
                break;
            }
        }

        // Determine winner
        if (teamLutemon.getHealth() > 0) {
            battleLog.append(teamLutemon.getName()).append(" wins the battle!\n");
            // teamLutemon.setExperience(teamLutemon.getExperience() + 10);
        } else {
            battleLog.append(enemyLutemon.getName()).append(" wins the battle!\n");
        }

        return battleLog.toString();
    }
}