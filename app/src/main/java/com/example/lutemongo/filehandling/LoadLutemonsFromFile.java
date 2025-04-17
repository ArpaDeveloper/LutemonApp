package com.example.lutemongo.filehandling;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongo.Lutemon;
import com.example.lutemongo.R;
import com.example.lutemongo.ui.LutemonAdapter;

import java.util.List;

public class LoadLutemonsFromFile {

    private List<Lutemon> lutemons;
    private final Context context;
    private final RecyclerView recyclerView;
    private final int layoutResId;

    public LoadLutemonsFromFile(Context context, RecyclerView recyclerView, int layoutResId) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.layoutResId = layoutResId;
    }

    //Method to load lutemon data
    public void loadLutemonData(){
        //Try loading from the JSON and catch errors
        try{
            lutemons = JsonUtils.loadLutemonsFromJson(context, R.raw.lutemons);
        }
        catch (Exception e){
            JsonUtils.handleError(e, context);
        }

        //Initialize the adapter and check for listener
        LutemonAdapter adapter;

        if (lutemons != null && !lutemons.isEmpty()) {
            adapter = new LutemonAdapter(lutemons, position -> {
                Lutemon clickedLutemon = lutemons.get(position);
                Log.d("Lutemon Clicked", clickedLutemon.getName());
            }, layoutResId);
            recyclerView.setAdapter(adapter);
        }
    }
}
