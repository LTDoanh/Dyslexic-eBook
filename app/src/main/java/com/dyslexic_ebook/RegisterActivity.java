package com.dyslexic_ebook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dyslexic_ebook.controller.BookController;
import com.dyslexic_ebook.controller.ColorRuleController;
import com.dyslexic_ebook.controller.UserController;
import com.dyslexic_ebook.model.Book;
import com.dyslexic_ebook.model.ColorRule;
import com.dyslexic_ebook.model.Font;
import com.dyslexic_ebook.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private UserController controller;
    private ColorRuleController colorRuleController;
    private BookController bookController;

    private String email = "";
    private String name = "";
    private String password = "";
    private String confirmPassword = "";
    private List<ColorRule> ruleList;
    private List<Book> bookList;

    private EditText emailInput;
    private EditText nameInput;
    private EditText passwordInput;
    private EditText passwordInput2;
    private FrameLayout createButton;
    private FrameLayout signInButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        controller = new UserController(this);
        colorRuleController = new ColorRuleController(this);
        bookController = new BookController(this);

        ruleList = new ArrayList<>();
        bookList = new ArrayList<>();

        emailInput = findViewById(R.id.emailInput);
        nameInput = findViewById(R.id.nameInput);
        passwordInput = findViewById(R.id.passwordInput);
        passwordInput2 = findViewById(R.id.passwordInput2);
        createButton = findViewById(R.id.createBtn);
        signInButton = findViewById(R.id.backSignInBtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                email = emailInput.getText().toString().trim();
            }
        });

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                name = nameInput.getText().toString().trim();
            }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                password = passwordInput.getText().toString().trim();
            }
        });

        passwordInput2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmPassword = passwordInput2.getText().toString().trim();
            }
        });

        createButton.setOnClickListener(v -> createAccount());

        signInButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

}