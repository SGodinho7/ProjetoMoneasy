package com.example.projetomoneasy;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddTransactionActivity extends AppCompatActivity {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    ApiConnect api = new ApiConnect();
    Button buttonsubmit;
    Button buttoncancel;
    EditText value;
    EditText date;
    EditText desc;
    Spinner category;
    String[] categories = {"Residência", "Automóvel", "Compras", "Serviços"};
    ArrayAdapter<String> adapterCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        value = findViewById(R.id.et_value);
        date = findViewById(R.id.et_date);
        desc = findViewById(R.id.et_description);
        category = findViewById(R.id.sp_category);

        adapterCategories = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        category.setAdapter(adapterCategories);

        buttonsubmit = findViewById(R.id.button_transaction_confirm);
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject post_data = new JSONObject();
                try {
                    post_data.put("value", value.getText().toString());
                    post_data.put("date", date.getText().toString());
                    post_data.put("desc", desc.getText().toString());
                    post_data.put("category", category.getSelectedItem().toString());

                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            api.postData("http://10.0.2.2:5000/api/post-transaction", post_data.toString());
                        }
                    });
                    executorService.shutdown();
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        buttoncancel = findViewById(R.id.button_transaction_cancel);
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}