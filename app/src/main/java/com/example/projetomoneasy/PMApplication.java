package com.example.projetomoneasy;

import android.app.Application;

public class PMApplication extends Application {
    static User current_user;

    public static void setCurrentUser(User user) {
        current_user = user;
    }

    public static User getCurrentUser() {
        return current_user;
    }

}
