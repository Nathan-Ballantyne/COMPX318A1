package com.example.ncb10.mobileproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.PrintWriter;
import java.net.Socket;

public class SignUp extends AppCompatActivity {

    private ServerClient sender;
    //Change this to whatever IP Address you are using it on

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
        sender = new ServerClient();
        sender.getReply("add user " + username + " " + password);
        sender.execute();
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
