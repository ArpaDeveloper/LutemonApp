package com.example.lutemongo.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.Lutemon;
import com.example.lutemongo.R;
import com.example.lutemongo.Storage;
import com.example.lutemongo.ui.LutemonAdapter;
import com.example.lutemongo.ui.RecyclerViewUtil;
import com.example.lutemongo.ui.UIHandler;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private LutemonAdapter adapter;
    private List<Lutemon> lutemons;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize the UIHandler
        UIHandler uiHandler = new UIHandler(this);

        // Set up button listeners for HomeActivity
        uiHandler.setupHomeActivityButtons(this);

        // Setup the RecyclerView for Home
        RecyclerView recyclerView = RecyclerViewUtil.setupRecyclerView(this, R.id.RecyclerViewHome);

        // Get Lutemons from Storage singleton
        storage = Storage.getInstance();
        lutemons = storage.getLutemons();

        // Create and set the adapter
        if (lutemons != null && !lutemons.isEmpty()) {
            adapter = new LutemonAdapter(lutemons, position -> {
                // Handle click on lutemon item
                Lutemon clickedLutemon = lutemons.get(position);
                // Set as team lutemon when clicked
                if (storage.setTeamLutemon(clickedLutemon)) {
                    // Only update the items that might have changed visually
                    // based on team selection status
                    for (int i = 0; i < lutemons.size(); i++) {
                        adapter.notifyItemChanged(i);
                    }
                }
            }, R.layout.item_layout_home);

            recyclerView.setAdapter(adapter);
        }
    }
}