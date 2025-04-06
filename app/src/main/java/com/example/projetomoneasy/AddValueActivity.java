package com.example.projetomoneasy;

import com.example.projetomoneasy.ApiConnect;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddValueActivity extends AppCompatActivity {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    ApiConnect api = new ApiConnect();
    Button buttonsubmit;
    EditText value;
    EditText date;
    EditText desc;
    EditText category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_value);

        value = (EditText) findViewById(R.id.et_value);
        date = (EditText) findViewById(R.id.et_date);
        desc = (EditText) findViewById(R.id.et_description);
        category = (EditText) findViewById(R.id.et_category);

        buttonsubmit = (Button) findViewById(R.id.button_submit);
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("value", value.getText().toString());
                    postData.put("date", date.getText().toString());
                    postData.put("desc", desc.getText().toString());
                    postData.put("category", category.getText().toString());

                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            api.postData("http://10.0.2.2:5000/api/post-value", postData.toString());
                        }
                    });
                    executorService.shutdown();
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}