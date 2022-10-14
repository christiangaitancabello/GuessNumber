package com.example.guessnumber;

import android.app.Application;

import com.example.guessnumber.data.User;

/**
 * Clase de la Aplicación que guarda un Usuario
 */
public class GuessNumberApplication extends Application {
    private User user;
    @Override
    public void onCreate() {
        super.onCreate();
        user = new User(getResources().getString(R.string.DefaultUser), getResources().getString(R.string.DefaultNtries));
    }

    public User getUser() {
        return user;
    }
}
