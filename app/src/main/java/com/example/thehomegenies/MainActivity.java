package com.example.thehomegenies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    SwitchCompat switchCompat;
    MaterialCardView cardView, cardView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchCompat = findViewById(R.id.modeSwitch);
        cardView = findViewById(R.id.cardView);
        cardView2 = findViewById(R.id.cardView2);
        SharedPreferences prefs = getSharedPreferences("Theme", MODE_PRIVATE);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("Theme",1);
                    editor.apply();
                }
                if (isChecked == false){
//                    switchCompat.setActivated(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("Theme",0);
                    editor.apply();
                }
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PuneActivity.class);
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BangaloreActivity.class);
                startActivity(intent);
            }
        });

    }
}