package com.example.lutemongo.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lutemongo.lutemonhandling.Lutemon;
import com.example.lutemongo.activities.MainActivity;
import com.example.lutemongo.R;
import com.example.lutemongo.activities.CreateActivity;
import com.example.lutemongo.activities.FightActivity;
import com.example.lutemongo.activities.HomeActivity;
import com.example.lutemongo.activities.StatisticsActivity;
import com.example.lutemongo.activities.TrainingActivity;

/**
 * Handles UI interactions and navigation between screens
 */
public class UIHandler {

    private final Context context;

    public UIHandler(@NonNull Context context) {
        this.context = context;
    }

    /**
     * Navigate to the main screen of the application.
     */
    public void goToMainScreen() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * Navigate to the create screen
     */
    public void goToCreateScreen() {
        Intent intent = new Intent(context, CreateActivity.class);
        context.startActivity(intent);
    }

    /**
     * Navigate to the home screen
     */
    public void goToHomeScreen() {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    /**
     * Navigate to the training screen
     */
    public void goToTrainingScreen() {
        Intent intent = new Intent(context, TrainingActivity.class);
        context.startActivity(intent);
    }

    /**
     * Navigate to the fight screen
     */
    public void goToFightScreen() {
        Intent intent = new Intent(context, FightActivity.class);
        context.startActivity(intent);
    }

    /**
     * Navigate to the statistics screen
     */
    public void goToStatsScreen() {
        Intent intent = new Intent(context, StatisticsActivity.class);
        context.startActivity(intent);
    }

    /**
     * Set up button listeners for the main activity.
     *
     * @param activity The activity whose buttons are being configured.
     */
    public void setupMainActivityButtons(@NonNull Activity activity) {
        Button createButton = activity.findViewById(R.id.go_create_button);
        Button homeButton = activity.findViewById(R.id.go_home_button);
        Button trainButton = activity.findViewById(R.id.go_train_button);
        Button fightButton = activity.findViewById(R.id.go_fight_button);
        Button statsButton = activity.findViewById(R.id.go_stats_button);

        createButton.setOnClickListener(v -> goToCreateScreen());
        homeButton.setOnClickListener(v -> goToHomeScreen());
        trainButton.setOnClickListener(v -> goToTrainingScreen());
        fightButton.setOnClickListener(v -> goToFightScreen());
        statsButton.setOnClickListener(v -> goToStatsScreen());
    }

    /**
     * Set up button listeners for the fight activity.
     *
     * @param activity The activity whose buttons are being configured.
     */
    public void setupFightActivityButtons(@NonNull Activity activity) {
        Button nextAttackButton = activity.findViewById(R.id.nextAttackButton);
        Button endBattleButton = activity.findViewById(R.id.endBattleButton);
        Button mainButton = activity.findViewById(R.id.main_button);

        if (nextAttackButton != null) {
            nextAttackButton.setOnClickListener(v -> {
            });
        }

        if (endBattleButton != null) {
            endBattleButton.setOnClickListener(v -> goToHomeScreen());
        }

        if (mainButton != null) {
            mainButton.setOnClickListener(v -> goToMainScreen());
        }
    }

    /**
     * Set up button listeners for the training activity.
     *
     * @param activity The activity whose buttons are being configured.
     */
    public void setupTrainingActivityButtons(@NonNull Activity activity) {
        Button homeButton = activity.findViewById(R.id.moveHomeButton);
        Button trainButton = activity.findViewById(R.id.moveTrainButton);
        Button mainButton = activity.findViewById(R.id.main_button);

        homeButton.setOnClickListener(v -> goToHomeScreen());
        mainButton.setOnClickListener(v -> goToMainScreen());
    }

    /**
     * Set up button listeners for the create activity.
     *
     * @param activity The activity whose buttons are being configured.
     */
    public void setupCreateActivityButtons(@NonNull Activity activity) {
        Button createButton = activity.findViewById(R.id.create_button);
        Button cancelButton = activity.findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(v -> goToMainScreen());
    }

    /**
     * Set up button listeners for the statistics activity.
     *
     * @param activity The activity whose buttons are being configured.
     */
    public void setupStatisticsActivityButtons(@NonNull Activity activity) {
        Button viewChartsButton = activity.findViewById(R.id.view_charts_button);
        Button mainButton = activity.findViewById(R.id.main_button);

        mainButton.setOnClickListener(v -> goToMainScreen());
    }

    /**
     * Display the battle result message on the specified TextView.
     *
     * @param result The battle result message to display.
     * @param resultView The TextView where the result will be displayed. Can be null.
     */
    public void printResults(@NonNull String result, @Nullable TextView resultView) {
        if (resultView != null) {
            resultView.setText(result);
            resultView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Update the UI with the Lutemon's information.
     *
     * @param lutemon The Lutemon object containing the data.
     * @param nameView TextView to display the Lutemon's name.
     * @param attackView TextView to display the Lutemon's attack value.
     * @param defenceView TextView to display the Lutemon's defence value.
     * @param healthView TextView to display the Lutemon's health value.
     */
    public void updateLutemonInfo(Lutemon lutemon, TextView nameView, TextView attackView,
                                  TextView defenceView, TextView healthView) {
        if (lutemon != null) {
            if (nameView != null) {
                nameView.setText(lutemon.getName());
            }
            if (attackView != null) {
                attackView.setText(context.getString(R.string.attack_format, lutemon.getAttack()));
            }
            if (defenceView != null) {
                defenceView.setText(context.getString(R.string.defence_format, lutemon.getDefence()));
            }
            if (healthView != null) {
                healthView.setText(context.getString(R.string.health_format, lutemon.getMaxHealth()));
            }
        }
    }

    /**
     * Finish and close the specified activity.
     *
     * @param activity The activity to be finished.
     */
    public void closeActivity(@NonNull Activity activity) {
        activity.finish();
    }

    /**
     * Set up button listeners for the home activity.
     *
     * @param activity The activity whose buttons are being configured.
     */
    public void setupHomeActivityButtons(@NonNull Activity activity) {
        Button fightButton = activity.findViewById(R.id.moveFightButton);
        Button trainButton = activity.findViewById(R.id.moveTrainButton);
        Button mainButton = activity.findViewById(R.id.main_button);


        if (mainButton != null) {
            mainButton.setOnClickListener(v -> goToMainScreen());
        }
    }
}
