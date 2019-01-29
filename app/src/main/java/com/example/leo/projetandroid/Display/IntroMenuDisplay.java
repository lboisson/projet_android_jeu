package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.widget.EditText;

import com.example.leo.projetandroid.R;

public class IntroMenuDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intromenu_display);
    }

    public void ActivityToGameDisplay(View v){
        Intent intent = new Intent(this, GameDisplay.class);

        writeIntoPreferences();
        startActivity(intent);
    }

    public void ActivityToOption(View v){
        Intent intent = new Intent(this, OptionDisplay.class);
        startActivity(intent);
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
     * write the size of the button into shared preferences, so that the game doesnt have to get it  again
     */
    private void writeIntoPreferences(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("BUTTON_SIZE", getWidthOfButton());
        myEditor.commit();
    }


}
