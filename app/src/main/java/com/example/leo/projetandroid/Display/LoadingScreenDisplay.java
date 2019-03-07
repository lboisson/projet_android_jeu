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

import com.example.leo.projetandroid.R;

import java.util.Locale;

public class LoadingScreenDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loadingscreen_display);

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this );
        String lang = myPreferences.getString("APP_LANG", "findFAIL");
        if ( !( lang.equals("findFail") ) && !( lang.equals( Locale.getDefault().getLanguage() ) ) ) {
            Locale myLocale = new Locale( lang );
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }

        writeIntoPreferences();

        Intent intent = new Intent(this, IntroMenuDisplay.class);
        startActivity(intent);

        finish();
    }

    /**
     * used to set the size of the bottom buttons
     * @return 1/4 of the width of the screen
     */
    private int getWidthOfButton(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return ((size.x)/4);
    }

    /**
     * write the size of the button into shared preferences, so that the game doesn't have to get it  again
     */
    private void writeIntoPreferences(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("BUTTON_SIZE", getWidthOfButton());
        myEditor.apply();
    }

}