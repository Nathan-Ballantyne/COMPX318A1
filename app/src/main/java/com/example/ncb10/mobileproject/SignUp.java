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
    private static Socket s;
    private static PrintWriter pw;
    private SendResponse sender;
    //Change this to whatever IP Address you are using it on
    private static String ipadd = "116.251.192.118";
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
        sender = new SendResponse();
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
    public static class SendResponse extends AsyncTask<String, Void, String> {

        String Reply;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                s = new Socket(ipadd,4000);
                pw = new PrintWriter(s.getOutputStream());
                pw.write(Reply);
                pw.flush();
                pw.close();
                s.close();

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        protected void getReply(String Reply_){
            Reply = Reply_;
        }
    }
}
