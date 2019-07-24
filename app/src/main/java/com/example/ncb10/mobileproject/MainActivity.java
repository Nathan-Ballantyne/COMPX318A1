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
        //Initialise Variables
        PopupWindow popUp;
        final LinearLayout layout;
        LinearLayout layout2;
        LinearLayout layout3;
        LinearLayout layout4;
        TextView tv;
        TextView tv2;
        TextView tv3;
        ViewGroup.LayoutParams params;
        final EditText editText;
        LinearLayout mainLayout;

        Button but;
        Button but2;
        boolean click = true;
        //Dialog myDialog;
        popUp = new PopupWindow(this);
        popUp.setFocusable(true);
        layout = new LinearLayout(this);
        layout2 = new LinearLayout(this);
        layout3 = new LinearLayout(this);
        layout4 = new LinearLayout(this);
        //mainLayout = new LinearLayout(this);
        tv = new TextView(this);
        tv2 = new TextView(this);
        tv3 = new TextView(this);
        editText = new EditText(this);
        Spinner s = new Spinner(this);
        Spinner s2 = new Spinner(this);
        but = new Button(this);
        but2 = new Button(this);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText.isFocusable();
                editText.requestFocus();

                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.showSoftInput(layout, InputMethodManager.SHOW_IMPLICIT);
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
        //Value of hours and minutes.
        String[] hours = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20","21", "22", "23"};
        String[] minutes = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
                "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",};

        //Adding the value in to adapter array
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, hours);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, minutes);

        //set the Drop down box with hour and minutes as value in them
        s.setAdapter(myAdapter);
        s2.setAdapter(myAdapter2);

        //Layout setting for popUp box
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        tv.setText("Creating Event Please enter the values below");
        tv2.setText("Gym Location/Name : ");
        tv3.setText("Egg hatch time : ");
        but.setText("Create Event");
        but2.setText("Cancel");
        layout.addView(tv, params);
        layout3.addView(tv2, params);
        layout3.addView(editText, params);
        layout.addView(layout3, params);
        layout2.addView(tv3, params);
        layout2.addView(s, params);
        layout2.addView(s2, params);
        layout.addView(layout2, params);
        layout4.addView(but, params);
        layout4.addView(but2, params);
        layout.addView(layout4,params);

        popUp.setContentView(layout);
        popUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
        //-300,240
    }


}


