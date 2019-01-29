package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.view.Display;

import com.example.leo.projetandroid.R;

public class ButtonsDisplay extends Activity {

    /**
     * Set the size of the buttons according the the screen width
     * (each button width and height is 1/4 of the screen width)
     */
    public void setButtonSize(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int size = myPreferences.getInt("BUTTON_SIZE", 0);
        android.view.ViewGroup.LayoutParams params_1 = findViewById(R.id.button_object).getLayoutParams();
        params_1.height = size;
        params_1.width = size;
        findViewById(R.id.button_object).setLayoutParams(params_1);

        android.view.ViewGroup.LayoutParams params_2 = findViewById(R.id.button_bag).getLayoutParams();
        params_2.height = size;
        params_2.width = size;
        findViewById(R.id.button_bag).setLayoutParams(params_2);

        android.view.ViewGroup.LayoutParams params_3 = findViewById(R.id.button_you).getLayoutParams();
        params_3.height = size;
        params_3.width = size;
        findViewById(R.id.button_you).setLayoutParams(params_3);

        android.view.ViewGroup.LayoutParams params_4 = findViewById(R.id.button_map).getLayoutParams();
        params_4.height = size;
        params_4.width = size;
        findViewById(R.id.button_map).setLayoutParams(params_4);
    }
}
