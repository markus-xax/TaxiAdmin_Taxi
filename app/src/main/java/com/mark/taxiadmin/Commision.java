package com.mark.taxiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mark.taxiadmin.API.City;
import com.mark.taxiadmin.API.HttpApi;

public class Commision extends AppCompatActivity {
    private final String Admin_Api = "http://45.86.47.12/api/admins/";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commision);
        Button com = findViewById(R.id.commision);
        com.setEnabled(false);

        Button home = findViewById(R.id.home);
        home.setOnClickListener(view -> {
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        });

        Button price = findViewById(R.id.price);

        price.setOnClickListener(view -> {
            Intent i = new Intent(this, Price.class);
            startActivity(i);
        });

        Button save = findViewById(R.id.save);
        EditText e = findViewById(R.id.com_n);
        save.setOnClickListener(view -> {
            if(!e.getText().toString().equals("")) {
                new Thread(()-> HttpApi.put(Admin_Api, "login=" + City.login + "&commission=" + e.getText())).start();
            }
        });

    }
}
