package com.example.ncb10.mobileproject;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClient extends AsyncTask<Void, Void, Void> {

    String Reply;
    String message;
    Socket s;
    PrintWriter pw;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("#002", "Gets here");
        try{
            Log.d("#003", "Gets here");
            s = new Socket("116.251.192.118", 33000);
            Log.d("#001", "Gets here");
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
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
    }

    protected void getReply(String Reply_){
        Reply = Reply_;
    }
}

//public class ReadResponse extends AsyncTask<Void, Void, Void>{
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        String message;
//        try {
//            while (true) {
//                Log.d("#001", "Gets here");
//                Socket s = new Socket("130.217.252.22", 5000);
//                Log.d("#002", "Gets here");
//                InputStreamReader isr = new InputStreamReader(s.getInputStream());
//                Log.d("#003", "Gets here");
//                BufferedReader br = new BufferedReader(isr);
//                Log.d("#004", "Gets here");
//                message = br.readLine();
//                Log.d("#0a1", message);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
