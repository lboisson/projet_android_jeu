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

import com.example.leo.projetandroid.R;

import java.util.Locale;

public class IntroMenuDisplay extends Activity {

    private int numberOfRowInGrid = 23;
    private int numberOfColumnInGrid = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this );

        //TODO make it a method ?
        String lang = myPreferences.getString("APP_LANG", "findFAIL");
        if ( !( lang.equals("findFail") ) && !( lang.equals( Locale.getDefault().getLanguage() ) ) ) {
            Locale myLocale = new Locale( lang );
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }

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

    /**
     *
     * @param v
     */
    public void ActivityToGameDisplay(View v){
        Intent intent = new Intent(this, GameDisplay.class);

        writeIntoPreferences("BUTTON_SIZE", getWidthOfButton());
        writeIntoPreferences("ROOM_HEIGHT", getHeightOfRoom());
        writeIntoPreferences("ROOM_WIDTH", getWidthOfRoom());
        writeIntoPreferences("CHARACTER_HEIGHT", getHeightOfCharacter());
        writeIntoPreferences("CHARACTER_WIDTH", getWidthOfCharacter());

        //TODO have an xml file with values, such as the number of row/column in the game grid
        writeIntoPreferences("GRID_ROW", numberOfRowInGrid);
        writeIntoPreferences("GRID_COLUMN", numberOfColumnInGrid);
        writeIntoPreferences("CELL_WIDTH", getWidthOfCell());
        writeIntoPreferences("CELL_HEIGHT", getHeightOfCell());
        writeIntoPreferences("CELL_OFFSET", getOffsetOfCell());

        startActivity(intent);
        finish();
    }

    /**
     *
     * @param v
     */
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
     * @return the height of the sprites (the height of the screen minus the height of the buttons).
     */
    public int getHeightOfRoom(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return ((size.y)-(size.x)/4);
    }

    /**
     *
     * @return the width for the sprites (which is the width of the screen)
     */
    public int getWidthOfRoom(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public int getWidthOfCharacter(){
        /** this part stretched the character sprite : not ideal
         *
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return (size.x/14);
         **/
        return getHeightOfCharacter()/2;
    }

    public int getHeightOfCharacter(){
        return (getHeightOfCell()*2);
    }

    public int getWidthOfCell(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return (size.x)/numberOfColumnInGrid;
    }

    public int getHeightOfCell(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return (size.y)/numberOfRowInGrid;
    }

    public int getOffsetOfCell(){
        return getWidthOfCell() - getWidthOfCharacter() ;
    }

    private void writeIntoPreferences(String key, int value){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt(key, value);
        myEditor.apply();
    }

}
