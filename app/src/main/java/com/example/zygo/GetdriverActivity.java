package com.example.zygo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GetdriverActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdriver);

        databaseHelper = new DatabaseHelper(this);

        TextView driverPhoneView = findViewById(R.id.tv_driver_phone);
        Button callButton = findViewById(R.id.btn_call_driver);

        // Fetch driver's phone number
        String driverPhone = databaseHelper.getDriverPhone();
        if (driverPhone != null) {
            driverPhoneView.setText(driverPhone);
            callButton.setVisibility(View.VISIBLE);
        } else {
            driverPhoneView.setText("No driver available at the moment.");
            callButton.setVisibility(View.GONE);
        }

        // Call the driver
        callButton.setOnClickListener(view -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + driverPhone));
            startActivity(callIntent);
        });
    }
}
