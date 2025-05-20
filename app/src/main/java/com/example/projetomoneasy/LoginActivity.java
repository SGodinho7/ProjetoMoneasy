package com.example.projetomoneasy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoginActivity extends AppCompatActivity {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    JSONObject user_json;
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
                Future<JSONObject> result = executorService.submit(() -> {
                    return ApiConnect.getData("http://10.0.2.2:5000/api/get-user/" + email.getText().toString());
                });
                try {
                    user_json = result.get();
                } catch (Exception e) {
                    e.getCause().printStackTrace();
                }
                if (user_json != null && PMApplication.loginUser(user_json, password.getText().toString())) {
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
}