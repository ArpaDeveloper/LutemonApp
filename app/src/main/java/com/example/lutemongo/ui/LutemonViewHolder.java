package com.example.lutemongo.ui;

//Imports
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.Lutemon;
import com.example.lutemongo.R;


//This class manages UI for single lutemon object

public class LutemonViewHolder extends RecyclerView.ViewHolder {

    //Variables
    private final ImageView lutemonImageView;
    private final TextView nameTextView;
    private final TextView colorTextView;
    private TextView expTextView;
    private TextView atkTextView;
    private TextView hpTextView;
    private TextView maxhpTextView;
    private TextView defenceTextView;
    private TextView battlesTextView;
    private TextView winsTextView;
    private TextView trainingTextView;

    private final CheckBox lutemonCheckbox;

    //Constructor
    public LutemonViewHolder(@NonNull View itemView, final LutemonAdapter.OnItemClickListener listener, int layoutResId) {
        super(itemView);
        //UI used by both layouts
        lutemonImageView = itemView.findViewById(R.id.lutemonImage);
        nameTextView = itemView.findViewById(R.id.lutemonNameText);
        colorTextView = itemView.findViewById(R.id.lutemonColorText);

        //Home layout texts
        if (layoutResId == R.layout.item_layout_home) {
            expTextView = itemView.findViewById(R.id.lutemonExpText);
            atkTextView = itemView.findViewById(R.id.lutemonAtkText);
            maxhpTextView = itemView.findViewById(R.id.lutemonMaxHpText);
            hpTextView = itemView.findViewById(R.id.lutemonHpText);
            defenceTextView = itemView.findViewById(R.id.lutemonDefText);
        }//Stats layout texts
        else if(layoutResId == R.layout.item_layout_stats){
            battlesTextView = itemView.findViewById(R.id.battlesNumberText);
            winsTextView = itemView.findViewById(R.id.winsNumberText);
            trainingTextView = itemView.findViewById(R.id.trainingNumberText);
        }


        //Define a listener
        itemView.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onItemClick(position);
            }
        });

        lutemonCheckbox = itemView.findViewById(R.id.lutemonCheckBox);
    }

    //Binds the movies data to the ui
    public void bind(Lutemon lutemon, int layoutResId) {

        nameTextView.setText(lutemon.getName());
        colorTextView.setText(lutemon.getColor());
        if (layoutResId == R.layout.item_layout_home) {
            //Convert int to String for experience, attack, health, max health, and defence
            expTextView.setText(String.valueOf(lutemon.getExperience()));
            atkTextView.setText(String.valueOf(lutemon.getAttack()));
            hpTextView.setText(String.valueOf(lutemon.getHealth()));
            maxhpTextView.setText(String.valueOf(lutemon.getMaxHealth()));
            defenceTextView.setText(String.valueOf(lutemon.getDefence()));
        }
        else if(layoutResId == R.layout.item_layout_stats) {
            //Convert int to String for battles,wins,training
            battlesTextView.setText(String.valueOf(lutemon.getExperience())); //Change after having method for these values
            winsTextView.setText(String.valueOf(lutemon.getAttack()));
            trainingTextView.setText(String.valueOf(lutemon.getHealth()));
        }


        String imageName = lutemon.getColor();

        int imageResId = itemView.getContext().getResources()
                .getIdentifier(imageName, "drawable", itemView.getContext().getPackageName());

        lutemonImageView.setImageResource(imageResId);

        // Handle checkbox state
        if (lutemonCheckbox != null) {
            lutemonCheckbox.setOnCheckedChangeListener(null); // prevent unwanted triggers
            lutemonCheckbox.setChecked(lutemon.isSelected());

            lutemonCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> lutemon.setSelected(isChecked));
        } else {
           // ErrorHandler.showError(this, "CheckBox not found in layout!");
            Log.e("LutemonViewHolder", "CheckBox not found in layout!");
        }
    }


}

