package com.example.zygo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver);

        Button getClientButton = findViewById(R.id.button6);

        getClientButton.setOnClickListener(view -> {
            Intent intent = new Intent(DriverActivity.this, GetclientActivity.class);
            startActivity(intent);
        });
    }
}
