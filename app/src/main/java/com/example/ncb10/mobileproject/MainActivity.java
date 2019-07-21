package com.example.ncb10.mobileproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View v){
        Intent myIntent = new Intent(getBaseContext(),
                StartScreen.class);
            startActivity(myIntent);


    }
    public void createEvent(View v){
        PopupWindow popUp;
        LinearLayout layout;
        TextView tv;
        ViewGroup.LayoutParams params;
        LinearLayout mainLayout;
        Button but;
        boolean click = true;
        Dialog myDialog;
        popUp = new PopupWindow(this);
        layout = new LinearLayout(this);
        //mainLayout = new LinearLayout(this);
        tv = new TextView(this);
        if (click) {
            //popUp.showAtLocation(layout, Gravity.TOP, -300, 240);
            popUp.update(-0, 0, 900, 1350);
            click = false;
        } else {
            popUp.dismiss();
            click = true;
        }
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        tv.setText("Hi this is a sample text for popup window");
        layout.addView(tv, params);
        popUp.setContentView(layout);
        popUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
        //-300,240
    }


}


