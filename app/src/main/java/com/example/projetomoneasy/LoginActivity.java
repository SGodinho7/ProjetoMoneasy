package com.example.projetomoneasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoginActivity extends AppCompatActivity {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    JSONObject user_json;
    JSONArray transactions_json;
    Button button_confirm;
    Button button_cancel;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_login_email);
        password = findViewById(R.id.et_login_password);

        button_confirm = findViewById(R.id.button_login_confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserJson();
                setTransactionArray();
                if (user_json != null && transactions_json != null && PMApplication.loginUser(user_json, password.getText().toString(), transactions_json)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                executorService.shutdown();
                finish();
            }
        });

        button_cancel = findViewById(R.id.button_login_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.shutdown();
                finish();
            }
        });
    }

    protected void setUserJson() {
        Future<JSONObject> result_user = executorService.submit(() -> {
            return ApiConnect.getData("http://10.0.2.2:5000/api/get-user/" + email.getText().toString());
        });
        try {
            user_json = result_user.get();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    protected void setTransactionArray() {
        Future<JSONArray> result_transactions = executorService.submit(() -> {
            return ApiConnect.getDataArray("http://10.0.2.2:5000/api/get-user-transaction-all/" + user_json.getInt("id_user"));
        });
        try {
            transactions_json = result_transactions.get();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }
}