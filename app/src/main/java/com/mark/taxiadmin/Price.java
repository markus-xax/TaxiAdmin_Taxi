package com.mark.taxiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mark.taxiadmin.API.City;
import com.mark.taxiadmin.API.HttpApi;

public class Price extends AppCompatActivity {
    private final String Admin_Api = "http://45.86.47.12/api/admins/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price);
        Button price = findViewById(R.id.price);
        price.setEnabled(false);

        EditText e = findViewById(R.id.price_numbers);

        Button commision = findViewById(R.id.commision);
        commision.setOnClickListener(view -> {
            Intent i = new Intent(this, Commision.class);
            startActivity(i);
        });

        Button home = findViewById(R.id.home);

        home.setOnClickListener(view -> {
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        });

        Button save = findViewById(R.id.save);

        save.setOnClickListener(view -> {
            if(!e.getText().toString().equals("")) {
                new Thread(()-> HttpApi.put(Admin_Api, "login=" + City.login + "&price=" + e.getText())).start();
            }
        });
    }
}
