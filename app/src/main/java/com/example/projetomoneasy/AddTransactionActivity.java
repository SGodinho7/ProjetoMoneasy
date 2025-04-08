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
    EditText value;
    EditText date;
    EditText desc;
    Spinner category;
    String[] categories = {"Residência", "Automovel", "Compras", "Servicos"};
    ArrayAdapter<String> adapterCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        value = (EditText) findViewById(R.id.et_value);
        date = (EditText) findViewById(R.id.et_date);
        desc = (EditText) findViewById(R.id.et_description);
        category = (Spinner) findViewById(R.id.sp_category);

        adapterCategories = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        category.setAdapter(adapterCategories);

        buttonsubmit = (Button) findViewById(R.id.button_submit);
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("value", value.getText().toString());
                    postData.put("date", date.getText().toString());
                    postData.put("desc", desc.getText().toString());
                    postData.put("category", category.getSelectedItem().toString());

                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            api.postTransactionData("http://10.0.2.2:5000/api/post-transaction", postData.toString());
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