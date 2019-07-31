package com.example.ncb10.mobileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String[] Level = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
                "40",};
        String[] Team ={"Valor (Red)", "Mystic (Blue)", "Instinct (Yellow)"};

        final Spinner levelS = findViewById(R.id.levelSpinner);
        final Spinner teamS = findViewById(R.id.teamSpinner);

        //Adding the value in to adapter array
        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(Profile.this,
                android.R.layout.simple_list_item_1, Level);
        ArrayAdapter<String> Adapter2 = new ArrayAdapter<String>(Profile.this,
                android.R.layout.simple_list_item_1, Team);

        levelS.setAdapter(Adapter1);
        teamS.setAdapter(Adapter2);
    }

    public void onSaveCLick(View v){

    }

    public void onReturnMainClick(View v){
        Intent returnIntent = new Intent(getBaseContext(),
                MainActivity.class);
        startActivity(returnIntent);
    }



}
