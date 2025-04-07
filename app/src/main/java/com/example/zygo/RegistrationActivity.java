package com.example.zygo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        dbHelper = new DatabaseHelper(this);

        EditText usernameEditText = findViewById(R.id.et_mail);
        EditText emailEditText = findViewById(R.id.et_email);
        EditText phoneEditText = findViewById(R.id.editTextPhone);
        EditText passwordEditText = findViewById(R.id.et_password);
        EditText confirmPasswordEditText = findViewById(R.id.et_confirm_password);

        Button roleButton = findViewById(R.id.btnRole);

        roleButton.setOnClickListener(view -> {
            // Get user input
            String username = usernameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            // Validate input
            if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check for duplicate data
            if (dbHelper.isUserExists(email, phone)) {
                Toast.makeText(this, "User with this email or phone already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            // Proceed to role selection
            Intent intent = new Intent(RegistrationActivity.this, RoleActivity.class);
            intent.putExtra("name", username);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            startActivity(intent);
        });
    }
}
