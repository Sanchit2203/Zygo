package com.example.zygo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GetclientActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getclient);

        databaseHelper = new DatabaseHelper(this);

        EditText phoneInput = findViewById(R.id.et_driver_phone);
        Button submitButton = findViewById(R.id.btn_submit_driver);

        // Save phone number in database
        submitButton.setOnClickListener(view -> {
            String phoneNumber = phoneInput.getText().toString().trim();

            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            } else {
                boolean isUpdated = databaseHelper.updateDriverPhone(phoneNumber);
                if (isUpdated) {
                    Toast.makeText(this, "Phone number saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to save phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
