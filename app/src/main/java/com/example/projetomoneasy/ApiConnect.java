package com.example.projetomoneasy;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiConnect {

    public static JSONObject getData(String address) {

        String line = "";
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(address);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //httpURLConnection.connect();
            Log.d("API", "Response Code: " + httpURLConnection.getResponseCode());

            StringBuilder informationString = new StringBuilder();
            //Scanner scanner = new Scanner(url.openStream());

            while ((line = bufferedReader.readLine()) != null) {
                informationString.append(line);
            }
            //scanner.close();

            return new JSONObject(informationString.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("API", "FAIL");
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return new JSONObject();
    }

    public static void postData(String... params) {

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

            Log.d("API", "Response Code: " + httpURLConnection.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("API", "FAIL");
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

}
