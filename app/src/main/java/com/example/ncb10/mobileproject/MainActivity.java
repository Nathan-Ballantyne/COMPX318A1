package com.example.ncb10.mobileproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
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
        final LinearLayout layout;
        TextView tv;
        TextView tv2;
        ViewGroup.LayoutParams params;
        final EditText editText;
        final EditText editText2;
        LinearLayout mainLayout;

        Button but;
        boolean click = true;
        Dialog myDialog;
        popUp = new PopupWindow(this);
        popUp.setFocusable(true);
        layout = new LinearLayout(this);
        //mainLayout = new LinearLayout(this);
        tv = new TextView(this);
        tv2 = new TextView(this);
        editText = new EditText(this);
        editText2 = new EditText(this);
        Spinner s = new Spinner(this);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText.isFocusable();
                editText.requestFocus();

                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.showSoftInput(layout, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText2.isFocusable();
                v.requestFocus();
            }
        });
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(editText, InputMethodManager.SHOW_FORCED);

        if (click) {
            //popUp.showAtLocation(layout, Gravity.TOP, -300, 240);
            popUp.update(-0, 0, 900, 1350);
            click = false;
        } else {
            popUp.dismiss();
            click = true;
        }
        String[] hours = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20","21", "22", "23"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, hours);
        s.setAdapter(myAdapter);
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        tv.setText("Hi this is a sample text for popup window");
        tv2.setText("Gym Location/Name : ");
        layout.addView(tv, params);
        layout.addView(tv2, params);
        layout.addView(editText, params);
        layout.addView(editText2, params);
        layout.addView(s, params);
        popUp.setContentView(layout);
        popUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
        //-300,240
    }


}


