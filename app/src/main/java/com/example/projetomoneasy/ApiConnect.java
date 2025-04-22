package com.example.projetomoneasy;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnect {

    protected void postData(String... params) {

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            httpURLConnection.setDoOutput(true);

            DataOutputStream os = new DataOutputStream(httpURLConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params[1]);
            writer.close();
            os.close();
            /*os.writeBytes(params[1]);
            os.flush();
            os.close();*/

            Log.d("API", "Response Code: " + httpURLConnection.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

}
