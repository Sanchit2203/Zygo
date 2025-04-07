package com.example.zygo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RoleActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role);

        dbHelper = new DatabaseHelper(this);

        RadioButton clientRadioButton = findViewById(R.id.radioButton);
        RadioButton driverRadioButton = findViewById(R.id.radioButton2);
        Button submitButton = findViewById(R.id.button);

        submitButton.setOnClickListener(view -> {
            Intent intent = getIntent();
            String name = intent.getStringExtra("name");
            String email = intent.getStringExtra("email");
            String phone = intent.getStringExtra("phone");

            if (clientRadioButton.isChecked()) {
                dbHelper.insertClient(name, email, phone, "Preferred Client");
                Toast.makeText(this, "Client data saved!", Toast.LENGTH_SHORT).show();
            } else if (driverRadioButton.isChecked()) {
                dbHelper.insertDriver(name, email, phone, "Driver License #12345");
                Toast.makeText(this, "Driver data saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show();
            }
            Intent successIntent = new Intent(RoleActivity.this, RegSuccessActivity.class);
            startActivity(successIntent);
            finish();

        });
    }
}
