package com.example.leo.projetandroid.Display;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.leo.projetandroid.Game;
import com.example.leo.projetandroid.R;
import java.lang.reflect.Field;


public class GameDisplay extends ButtonsDisplay {

    private Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        this.game = Game.getInstance();


        //set the buttons
        setButtonSize();

        //set the sprites for the background of the room
        setRoomSpritesNames(game);

        //set the size of the backgroun of the room
        setRoomSpriteHeight();

        //set the character sprite
        setCharacterSprite(game);


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
    private void setRoomSpriteHeight(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int height = myPreferences.getInt("ROOM_HEIGHT", 0);
        int width =  myPreferences.getInt("ROOM_WIDTH", 0);

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
        int wall_east_ressource = getResId(game.getWallEastRessource(), R.drawable.class);
        int wall_west_ressource = getResId(game.getWallWestRessource(), R.drawable.class);
        int wall_north_ressource = getResId(game.getWallNorthRessource(), R.drawable.class);
        int wall_south_ressource = getResId(game.getWallSouthRessource(), R.drawable.class);
        int floor_ressource = getResId(game.getFloorRessource(), R.drawable.class);

        findViewById(R.id.wall_east).setBackgroundResource(wall_east_ressource);
        findViewById(R.id.wall_west).setBackgroundResource(wall_west_ressource);
        findViewById(R.id.wall_north).setBackgroundResource(wall_north_ressource);
        findViewById(R.id.wall_south).setBackgroundResource(wall_south_ressource);
        findViewById(R.id.floor).setBackgroundResource(floor_ressource);
    }

    /**
     * get the ID of a ressource from its name
     * @param resName the name of the ressource you want the ID
     * @param c the class of the ressource you want the idea of
     * @return the ID of the ressource
     */
    private static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     *
     * @param game
     */

    /**
     *
     */
    private void setCharacterSprite(Game game){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int height = myPreferences.getInt("CHARACTER_HEIGHT", 0);
        int width = myPreferences.getInt("CHARACTER_WIDTH", 0);
        int cellHeight = myPreferences.getInt("CELL_HEIGHT", 0);
        int cellWidth = myPreferences.getInt("CELL_WIDTH", 0);
        int character_ressource = getResId(game.getCharacterSprite(), R.drawable.class);
        ImageView character = (ImageView) findViewById(R.id.character);

        ViewGroup.LayoutParams params_character = character.getLayoutParams();
        params_character.height = height;
        params_character.width = width;


        character.setBackgroundResource(character_ressource);
        character.setLayoutParams(params_character);
        character.setX(game.getCharacterX()*cellWidth);
        character.setY(game.getCharacterY()*cellHeight);
    }


}
