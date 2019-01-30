package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.leo.projetandroid.R;

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
}
