package com.example.leo.projetandroid.Display;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.leo.projetandroid.R;

import java.util.Locale;


public class OptionDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionmenu_display);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    public void onEnglishClicked(View v) {
        setLang( "en" );
    }

    public void onFrenchClicked(View v) {
        setLang( "fr" );
    }

    private void setLang ( String lang ) {
        Locale myLocale = new Locale( lang );
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this );
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putString("APP_LANG", lang );
        myEditor.commit();

        Intent refresh = new Intent(this, OptionDisplay.class);
        startActivity(refresh);
        finish();
    }

}
