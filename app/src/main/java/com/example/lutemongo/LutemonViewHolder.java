package com.example.lutemongo;

//Imports
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//This class manages UI for single movie object

public class LutemonViewHolder extends RecyclerView.ViewHolder {

    //Variables
    private final ImageView lutemonImageView;
    private final TextView nameTextView;
    private final TextView colorTextView;
    private final TextView expTextView;
    private final TextView atkTextView;
    private final TextView hpTextView;
    private final TextView maxhpTextView;
    private final TextView defenceTextView;

    //Constructor
    public LutemonViewHolder(@NonNull View itemView, final LutemonAdapter.OnItemClickListener listener) {
        super(itemView);

        lutemonImageView = itemView.findViewById(R.id.lutemonImage);
        nameTextView = itemView.findViewById(R.id.lutemonNameText);
        colorTextView = itemView.findViewById(R.id.lutemonColorText);
        expTextView = itemView.findViewById(R.id.lutemonExpText);
        atkTextView = itemView.findViewById(R.id.lutemonAtkText);
        maxhpTextView = itemView.findViewById(R.id.lutemonMaxHpText);
        hpTextView = itemView.findViewById(R.id.lutemonHpText);
        defenceTextView = itemView.findViewById(R.id.lutemonDefText);
        //Define a listener
        itemView.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    //Binds the movies data to the ui
    public void bind(Lutemon lutemon) {

        nameTextView.setText(lutemon.getName());
        colorTextView.setText(lutemon.getColor());

        //Convert int to String for experience, attack, health, max health, and defence
        expTextView.setText(String.valueOf(lutemon.getExperience()));
        atkTextView.setText(String.valueOf(lutemon.getAttack()));
        hpTextView.setText(String.valueOf(lutemon.getHealth()));
        maxhpTextView.setText(String.valueOf(lutemon.getMaxHealth()));
        defenceTextView.setText(String.valueOf(lutemon.getDefence()));

        String imageName = lutemon.getColor();

        int imageResId = itemView.getContext().getResources()
                .getIdentifier(imageName, "drawable", itemView.getContext().getPackageName());

        lutemonImageView.setImageResource(imageResId);

    }

}

