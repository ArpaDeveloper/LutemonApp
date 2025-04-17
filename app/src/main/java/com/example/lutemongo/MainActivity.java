package com.example.lutemongo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Load saved Lutemons
        Storage storage = Storage.getInstance();
        if (storage.loadFromSharedPreferences(this)) {
            int count = storage.getLutemonCount();
            if (count > 0) {
                Toast.makeText(this, "Loaded " + count + " Lutemons", Toast.LENGTH_SHORT).show();
            }
        }

        UIHandler uiHandler = new UIHandler(this);
        uiHandler.setupMainActivityButtons(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}