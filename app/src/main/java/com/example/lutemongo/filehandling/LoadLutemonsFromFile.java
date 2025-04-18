package com.example.lutemongo.filehandling;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.Lutemon;
import com.example.lutemongo.R;
import com.example.lutemongo.Storage;
import com.example.lutemongo.ui.LutemonAdapter;

import java.util.List;

public class LoadLutemonsFromFile {

    private List<Lutemon> lutemons;
    private final RecyclerView recyclerView;
    private final int layoutResId;

    private LutemonAdapter adapter;
    private Storage storage;

    public LoadLutemonsFromFile(RecyclerView recyclerView, int layoutResId) {
        this.recyclerView = recyclerView;
        this.layoutResId = layoutResId;
    }

    //Method to load lutemon data
    public void loadLutemonData(){

        // Initialize the adapter and check for listener
        final LutemonAdapter[] adapterWrapper = new LutemonAdapter[1]; // Wrapper for adapter
        // Get Lutemons from Storage singleton
        storage = Storage.getInstance();
        lutemons = storage.getLutemons();

        // Create and set the adapter
        if (lutemons != null && !lutemons.isEmpty()) {
            adapterWrapper[0] = new LutemonAdapter(lutemons, position -> {
                // Handle click on lutemon item
                Lutemon clickedLutemon = lutemons.get(position);
                // Set as team lutemon when clicked
                if (storage.setTeamLutemon(clickedLutemon)) {
                    // Only update the items that might have changed visually
                    // based on team selection status
                    for (int i = 0; i < lutemons.size(); i++) {
                        adapterWrapper[0].notifyItemChanged(i); // Use the wrapper's reference
                    }
                }
            }, layoutResId);

            recyclerView.setAdapter(adapterWrapper[0]); // Set the adapter to the RecyclerView
        }
    }
}
