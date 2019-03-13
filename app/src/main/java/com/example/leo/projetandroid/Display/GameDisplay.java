package com.example.leo.projetandroid.Display;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leo.projetandroid.DBContractTest.*;
import com.example.leo.projetandroid.Game;
import com.example.leo.projetandroid.R;

import java.lang.reflect.Field;


public class GameDisplay extends ButtonsDisplay {

    private Game game;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        this.game = Game.getInstance();
        this.game.ADB = openOrCreateDatabase(
                "ADB.db"
                , SQLiteDatabase.CREATE_IF_NECESSARY
                , null
        );

        setButtonSize();
        setRoomSpritesNames(game);
        setSpriteHeight();
        setCharacter();



    }

    @Override
    protected void onDestroy() {
        this.game.saveEverything();
        super.onDestroy();
    }

    /**
     * @return the height of the sprites (the height of the screen minus the height of the buttons).
     */
    public int getHeightOfSprites(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return ((size.y)-(size.x)/4);
    }

    /**
     *
     * @return the width for the sprites (which is the width of the screen)
     */
    public int getWidthOfSprites(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    @Override
    /**
     * action when back button is pressed
     */
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.quit_text)
                .setCancelable(false)
                .setPositiveButton(R.string.quit_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.quit_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * set the size for the sprites
     */
    private void setSpriteHeight(){
        int height = (int)getHeightOfSprites();
        int width =  (int)getWidthOfSprites();

        //**********    WALLS   ***********//
        android.view.ViewGroup.LayoutParams params_wall_east = findViewById(R.id.wall_east).getLayoutParams();
        params_wall_east.height = height;
        params_wall_east.width = width;
        findViewById(R.id.wall_east).setLayoutParams(params_wall_east);

        android.view.ViewGroup.LayoutParams params_wall_west = findViewById(R.id.wall_west).getLayoutParams();
        params_wall_west.height = height;
        params_wall_west.width = width;
        findViewById(R.id.wall_west).setLayoutParams(params_wall_west);

        android.view.ViewGroup.LayoutParams params_wall_north = findViewById(R.id.wall_north).getLayoutParams();
        params_wall_north.height = height;
        params_wall_north.width = width;
        findViewById(R.id.wall_north).setLayoutParams(params_wall_north);

        android.view.ViewGroup.LayoutParams params_wall_south = findViewById(R.id.wall_south).getLayoutParams();
        params_wall_south.height = height;
        params_wall_south.width = width;
        findViewById(R.id.wall_south).setLayoutParams(params_wall_south);


        //*************     FLOOR       ************//
        android.view.ViewGroup.LayoutParams params_floor = findViewById(R.id.floor).getLayoutParams();
        params_floor.height = height;
        params_floor.width = width;
        findViewById(R.id.floor).setLayoutParams(params_floor);

    }

    /**
     * Set the name of the sprites for the room the character is in
     */
    private void setRoomSpritesNames(Game game){
        int wall_east_ressource = getResId(this.game.wall_east_ressource(), R.drawable.class);
        int wall_west_ressource = getResId(this.game.wall_west_ressource(), R.drawable.class);
        int wall_north_ressource = getResId(this.game.wall_north_ressource(), R.drawable.class);
        int wall_south_ressource = getResId(this.game.wall_south_ressource(), R.drawable.class);
        int floor_ressource = getResId(this.game.floor_ressource(), R.drawable.class);

        findViewById(R.id.wall_east).setBackgroundResource(wall_east_ressource);
        findViewById(R.id.wall_west).setBackgroundResource(wall_west_ressource);
        findViewById(R.id.wall_north).setBackgroundResource(wall_north_ressource);
        findViewById(R.id.wall_south).setBackgroundResource(wall_south_ressource);
        findViewById(R.id.floor).setBackgroundResource(floor_ressource);
    }

    private void setCharacter(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int height = myPreferences.getInt("CELL_HEIGHT", 0);
        int width = myPreferences.getInt("CELL_WIDTH", 0);

        int screenHeight = myPreferences.getInt("GAMEAREA_HEIGHT", 0);
        int screenWidth = myPreferences.getInt("GAMEAREA_WIDTH", 0);

        android.view.ViewGroup.LayoutParams params_character = findViewById(R.id.character).getLayoutParams();
        ImageView IVCharacter = findViewById(R.id.character);
        params_character.height = 2 * height;
        params_character.width = width;
        Toast.makeText(this, "Y : " + game.getCharacter().getRoomY() * height * width + ".", Toast.LENGTH_LONG).show();
        findViewById(R.id.character).setX(game.getCharacter().getRoomX() * width);
        findViewById(R.id.character).setY((game.getCharacter().getRoomY() * height)-(height/3));
        findViewById(R.id.character).setLayoutParams(params_character);
    }

    /**
     * get the ID of a ressource from its name
     * @param resName the name of the ressource you want the ID
     * @param c the class of the ressource you want the idea of
     * @return the ID of the ressource
     */
    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}