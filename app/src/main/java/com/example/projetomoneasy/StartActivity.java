package com.example.projetomoneasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartActivity extends AppCompatActivity {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Button buttonlogin;
    Button buttonsignin;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        buttonlogin = findViewById(R.id.button_login);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonsignin = findViewById(R.id.button_signin);
        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        // Teste de get na API
        test = findViewById(R.id.textView_test);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                JSONObject json = ApiConnect.getData("http://10.0.2.2:5000/api/get-user/1");
                try {
                    test.setText(json.getString("name"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();

    }
}