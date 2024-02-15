package com.mark.taxiadmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mark.taxiadmin.API.City;
import com.mark.taxiadmin.API.HttpApi;
import com.mark.taxiadmin.API.model.AdminDataPojo;

import java.io.IOException;

public class Settings extends AppCompatActivity {
    private final String Admin_Api = "http://45.86.47.12/api/admins/";
    private EditText price, commission, rain, delivery, wait, day;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        bottom_nav();

        assign();

        loadData();

        updating();
    }

    private void assign(){
        price = findViewById(R.id.price_s);
        commission = findViewById(R.id.commission_s);
        rain = findViewById(R.id.weather_commission_s);
        delivery = findViewById(R.id.delivery_s);
        day = findViewById(R.id.day_s);
        wait = findViewById(R.id.wait_s);
    }

    private void bottom_nav(){
        Button home = findViewById(R.id.home);
        home.setOnClickListener(view -> {
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        });

        Button settings = findViewById(R.id.settings);
        settings.setEnabled(false);
    }

    private void updating(){
        Button save = findViewById(R.id.save);
        save.setOnClickListener(v -> {
            new Thread(()-> HttpApi.put(Admin_Api, "login=" + City.login +
                    "&price=" + price.getText() + "&commission=" + commission.getText() +
                    "&delivery=" + delivery.getText() + "&day=" + day.getText() +
                    "&weather=" + rain.getText() + "&wait=" + wait.getText())).start();
            runOnUiThread(() -> {
                Toast.makeText(this, "Успех!", Toast.LENGTH_LONG).show();
            });
        });
    }

    @SuppressLint("SetTextI18n")
    private void loadData() {
        new Thread(()->{
            try {
                AdminDataPojo data = new Gson().fromJson(HttpApi.getId(Admin_Api+ City.login+"_"+"0"+"_"+"0"), AdminDataPojo.class);
                runOnUiThread(()->{
                    price.setText(data.getPrice());
                    commission.setText(data.getCommission());
                    rain.setText(data.getWeather());
                    delivery.setText(data.getDelivery());
                    day.setText(data.getDay());
                    wait.setText(data.getWait());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
