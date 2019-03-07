package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import com.example.leo.projetandroid.R;

import java.util.Locale;

public class IntroMenuDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intromenu_display);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
        Intent refresh = new Intent(this, IntroMenuDisplay.class);
        startActivity(refresh);
        finish();
    }

    public void ActivityToGameDisplay(View v){
        Intent intent = new Intent(this, GameDisplay.class);
        startActivity(intent);

        finish();
    }

    public void ActivityToOption(View v){
        Intent intent = new Intent(this, OptionDisplay.class);
        startActivity(intent);
    }

}
