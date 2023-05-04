package com.example.latihan_lks_laundry_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button PickUp, Notif;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PickUp = findViewById(R.id.pickup);
        Notif = findViewById(R.id.notif);

        PickUp.setOnClickListener(view -> {
            Intent pickup = new Intent(MainActivity.this, RequestPickUpActivity.class);
            startActivity(pickup);
            finish();
        });

        Notif.setOnClickListener(view -> {
            Intent notif = new Intent(MainActivity.this, NotifActivity.class);
            startActivity(notif);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}