package com.example.projetomoneasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    JSONArray array;
    Button buttonadd;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonadd = (Button) findViewById(R.id.button_transaction_confirm);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        // Teste de get na API
        Future<JSONArray> result = executorService.submit(() -> {
            return ApiConnect.getDataArray("http://10.0.2.2:5000/api/get-user-transaction-all/0");
        });
        try {
            array = result.get();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }


        test = findViewById(R.id.textView_test);
        try {
            test.setText(array.get(2).toString());
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
        //test.setText(Integer.toString(PMApplication.getCurrentUser().getId()));

    }
}