package com.example.zygo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button homebtn= findViewById(R.id.home);


        dbHelper = new DatabaseHelper(this);

        // Find views
        EditText emailEditText = findViewById(R.id.et_mail);
        EditText passwordEditText = findViewById(R.id.et_password);
        Button loginButton = findViewById(R.id.btn_login);

        // Set login button click listener
        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
                return;
            }


            // Check user role in the database
            String role = dbHelper.getUserRoleByEmail(email);
            if (role == null) {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            } else if (role.equals("client")) {
                Intent clientIntent = new Intent(LoginActivity.this, ClientActivity.class);
                startActivity(clientIntent);
                finish(); // Close the login activity
            } else if (role.equals("driver")) {
                Intent driverIntent = new Intent(LoginActivity.this, DriverActivity.class);
                startActivity(driverIntent);
                finish(); // Close the login activity
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
