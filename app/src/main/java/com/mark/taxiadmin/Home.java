package com.mark.taxiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
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
        setContentView(R.layout.home_settings);
        Button home = findViewById(R.id.home);
        home.setEnabled(false);

        Button commision = findViewById(R.id.commision);
        commision.setOnClickListener(view -> {
            Intent i = new Intent(this, Commision.class);
            startActivity(i);
        });

        Button price = findViewById(R.id.price);

        price.setOnClickListener(view -> {
            Intent i = new Intent(this, Price.class);
            startActivity(i);
        });

        echoPriceCommision();
    }

    private void echoPriceCommision(){
        new Thread(()->{
            TextView price = findViewById(R.id.price_text_numbers);
            TextView commisnion = findViewById(R.id.commision_text_numbers);
            try {
                AdminDataPojo data = new Gson().fromJson(HttpApi.getId(Admin_Api+ City.login+"_"+"0"+"_"+"0"), AdminDataPojo.class);
                runOnUiThread(()->{
                    price.setText(data.getPrice());
                    commisnion.setText(data.getCommission()+"%");
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
