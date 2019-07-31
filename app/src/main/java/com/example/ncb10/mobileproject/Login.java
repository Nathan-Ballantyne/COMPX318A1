package com.example.ncb10.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onLoginClick(View v){
        EditText user = findViewById(R.id.textUsername);
        EditText pass = findViewById(R.id.textPassword);
        String username = user.getText().toString();
        String password = pass.getText().toString();

        Intent loginIntent = new Intent(getBaseContext(),
                Profile.class);
        startActivity(loginIntent);
    }

    public void onReturnCLICK(View v){
        Intent ReturnIntent = new Intent(getBaseContext(),
                MainActivity.class);
        startActivity(ReturnIntent);
    }

    public void onSignupClick(View v){
        Intent signUpIntent = new Intent(getBaseContext(),
                SignUp.class);
        startActivity(signUpIntent);
    }

}
