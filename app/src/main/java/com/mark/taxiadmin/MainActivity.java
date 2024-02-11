package com.mark.taxiadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.mark.taxiadmin.API.City;
import com.mark.taxiadmin.API.HttpApi;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String Admin_Api = "http://45.86.47.12/api/admins/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button log = findViewById(R.id.button);
        log.setOnClickListener(view -> login());
    }

    private void login(){
        EditText login = findViewById(R.id.loginPhone);
        EditText pass = findViewById(R.id.loginPass);
        new Thread(()->{
            try {
                if(HttpApi.getId(Admin_Api+login.getText()+"_"+pass.getText()+"_"+"1").equals("\"OK\"")) {
                    City.login = login.getText().toString();
                    Intent i = new Intent(this, Home.class);
                    startActivity(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}