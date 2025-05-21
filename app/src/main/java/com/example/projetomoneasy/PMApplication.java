package com.example.projetomoneasy;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONObject;

public class PMApplication extends Application {
    private static User current_user;

    public static User getCurrentUser() {
        return current_user;
    }

    public static boolean loginUser(JSONObject user_json, String login_password, JSONArray transaction_array) {
        current_user = new User();
        try {
            int id = user_json.getInt("id_user");
            String name = user_json.getString("name");
            String email = user_json.getString("email");
            String password = user_json.getString("password");
            if (login_password.equals(password)) {
                current_user.setInfo(id, name, email, password, transaction_array);
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
