package com.example.leo.projetandroid.Display;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
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

        // Set language
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = preferences.getString("APP_LANG", "findFAIL");
        if (!(lang.equals("findFail")) && !(lang.equals(Locale.getDefault().getLanguage()))) {
            Locale myLocale = new Locale(lang);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }

        // Set size of buttons
        writeIntoPreferences();

        // Create DB
        createADB();

        Intent intent = new Intent(this, IntroMenuDisplay.class);
        startActivity(intent);

        finish();

    }

    @SuppressLint("WrongConstant")
    private void createADB() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean exist = preferences.getBoolean("FST_LAUNCH", false);
        if (!exist) {

            SQLiteDatabase tempADB = openOrCreateDatabase(
                    "ADB.db"
                    , SQLiteDatabase.CREATE_IF_NECESSARY
                    , null
            );

            final String Create_table_ROOM =
                    "CREATE TABLE t_room ("
                            + "Id_Room INTEGER PRIMARY KEY,"
                            + "Long INTEGER,"
                            + "Lat INTEGER,"
                            + "Floor INTEGER,"
                            + "NS_Floor TEXT,"
                            + "NSW_West TEXT,"
                            + "NSW_East TEXT,"
                            + "NSW_South TEXT,"
                            + "NSW_North TEXT);";

            tempADB.execSQL(Create_table_ROOM);

            SharedPreferences.Editor DB_EXIST_EDIT = preferences.edit();
            DB_EXIST_EDIT.putBoolean("FST_LAUNCH", true);
            DB_EXIST_EDIT.commit();

        }

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

    private int getWidthOfCell(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return (((size.x)/14)+1);
    }

    private int getHeightOfCell(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return ((((size.y)-getWidthOfButton())/22)+1);
    }

    private int getWidthOfGameArea(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    private int getHeightOfGameArea(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return ((size.y)-getWidthOfButton());
    }

    /**
     * write the general informations into shared preferences, so that the game doesn't have to get it  again
     */
    private void writeIntoPreferences(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("BUTTON_SIZE", getWidthOfButton());
        myEditor.putInt("CELL_HEIGHT", getHeightOfCell());
        myEditor.putInt("CELL_WIDTH", getWidthOfCell());
        myEditor.putInt("GAMEAREA_HEIGHT", getHeightOfGameArea());
        myEditor.putInt("GAMEAREA_WIDTH", getWidthOfGameArea());
        myEditor.apply();

    }

}