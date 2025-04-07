package com.example.zygo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);

        Button getDriverButton = findViewById(R.id.button2);
        getDriverButton.setOnClickListener(view -> {
            Intent intent = new Intent(ClientActivity.this, GetdriverActivity.class);
            startActivity(intent);
        });
    }
}
