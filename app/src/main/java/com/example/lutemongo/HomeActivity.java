package com.example.lutemongo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView LutemonRecyclerView;
    private List<Lutemon> lutemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize the UIHandler
        UIHandler uiHandler = new UIHandler(this);

        // Set up button listeners for HomeActivity
        uiHandler.setupHomeActivityButtons(this);

        //Setup the RecyclerView
        SetupRecyclerView();

        //Load the data from Json
        loadLutemonData();
    }

    //Method to setup the recyclerView
    public void SetupRecyclerView(){
        //Find the recyclerview and set it layout and size.
        LutemonRecyclerView = findViewById(R.id.RecyclerViewHome);
        LutemonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LutemonRecyclerView.setHasFixedSize(true);
    }

    //Method to load lutemon data
    public void loadLutemonData(){
        //Try loading from the JSON and catch errors
        try{
            lutemons = JsonUtils.loadLutemonsFromJson(this, R.raw.lutemons);
        }
        catch (Exception e){
            JsonUtils.handleError(e, this);
        }

        //Initialize the adapter and check for listener
        LutemonAdapter adapter;

        if (lutemons != null && !lutemons.isEmpty()) {
            adapter = new LutemonAdapter(lutemons, position -> {
                Lutemon clickedLutemon = lutemons.get(position);
                Log.d("Lutemon Clicked", clickedLutemon.getName());
            });
            LutemonRecyclerView.setAdapter(adapter);
        }
    }

    public void showError(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}