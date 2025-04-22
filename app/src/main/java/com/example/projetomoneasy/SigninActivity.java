package com.example.projetomoneasy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SigninActivity extends AppCompatActivity {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    ApiConnect api = new ApiConnect();
    Button button_confirm;
    Button button_cancel;
    EditText name;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        name = findViewById(R.id.et_signin_name);
        email = findViewById(R.id.et_signin_email);
        password = findViewById(R.id.et_signin_password);

        button_confirm = findViewById(R.id.button_signin_confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("name", name.getText().toString());
                    postData.put("email", email.getText().toString());
                    postData.put("password", password.getText().toString());

                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            api.postTransactionData("http://10.0.2.2:5000/api/post-user", postData.toString());
                        }
                    });
                    executorService.shutdown();
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        button_cancel = findViewById(R.id.button_signin_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}