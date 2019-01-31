package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioGroup;

import com.example.leo.projetandroid.R;

import java.util.Locale;

public class OptionDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionmenu_display);

        /*if ( Locale.getDefault().toString().equals( "fr_FR" ) ) {
            RadioGroup buttonGroup = findViewById(R.id.button_group);
            buttonGroup.check( R.id.button_french );
        }*/
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    public void onEnglishClicked(View v) {
        Locale myLocale = new Locale("en");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, OptionDisplay.class);
        startActivity(refresh);
        this.finish();
    }

    public void onFrenchClicked(View v) {
        Locale myLocale = new Locale("fr");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, OptionDisplay.class);
        startActivity(refresh);
        finish();
    }
}
