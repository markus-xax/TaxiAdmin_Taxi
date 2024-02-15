package com.mark.taxiadmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mark.taxiadmin.API.City;
import com.mark.taxiadmin.API.HttpApi;
import com.mark.taxiadmin.API.model.AdminDataPojo;


import java.io.IOException;

public class Home extends AppCompatActivity {

    private final String Admin_Api = "http://45.86.47.12/api/admins/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        bottom_nav();

        echoPriceCommission();
    }

    @SuppressLint("SetTextI18n")
    private void echoPriceCommission(){
        new Thread(()->{
            TextView price = findViewById(R.id.price_text_numbers);
            TextView commission = findViewById(R.id.commision_text_numbers);
            TextView weather = findViewById(R.id.weather_commission_numbers);
            TextView delivery = findViewById(R.id.delivery_numbers);
            TextView day = findViewById(R.id.day_numbers);
            TextView wait = findViewById(R.id.wait_numbers);
            try {
                AdminDataPojo data = new Gson().fromJson(HttpApi.getId(Admin_Api+ City.login+"_"+"0"+"_"+"0"), AdminDataPojo.class);
                runOnUiThread(()->{
                    price.setText(data.getPrice());
                    commission.setText(data.getCommission()+"%");
                    weather.setText(data.getWeather()+"%");
                    delivery.setText(data.getDelivery()+"%");
                    day.setText(data.getDay()+"%");
                    wait.setText(data.getWait());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void bottom_nav(){
        Button home = findViewById(R.id.home);
        home.setEnabled(false);

        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener(view -> {
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        });
    }
}
