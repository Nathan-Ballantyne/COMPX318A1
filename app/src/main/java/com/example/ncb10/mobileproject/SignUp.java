package com.example.ncb10.mobileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onCreateClick(View v){
        EditText user = findViewById(R.id.textUsername);
        EditText pass = findViewById(R.id.textPassword);
        String username = user.getText().toString();
        String password = pass.getText().toString();
        Intent createIntent = new Intent(getBaseContext(),
                Profile.class);
        startActivity(createIntent);
    }

    public void onReturnClick(View v){
        Intent returnIntent = new Intent(getBaseContext(),
                Login.class);
        startActivity(returnIntent);
    }
}
