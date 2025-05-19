package com.dyslexic_ebook.controller;

import android.content.Context;
import android.util.Log;

import com.dyslexic_ebook.model.ColorRule;
import com.dyslexic_ebook.model.User;

import java.util.concurrent.CompletableFuture;


public class UserController extends FireBaseController {
    public UserController() {
        super();
    }

    public UserController(Context context) {
        super(context);
    }

    public void addUser(User user) {
        if (user == null) {
            return;
        }
        database.getReference("Users").child(String.valueOf(user.getId())).setValue(user)
                .addOnFailureListener(e -> Log.e("Register", "Error saving user data"));
    }

}
