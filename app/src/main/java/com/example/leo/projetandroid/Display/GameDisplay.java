package com.example.leo.projetandroid.Display;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

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

        //      DATABASE        //
        SQLiteDatabase ADB = openOrCreateDatabase(
                "ADB.db"
                , SQLiteDatabase.CREATE_IF_NECESSARY
                , null
        );
        this.game = Game.getInstance( ADB );


        //      TOUCH LISTENER      //
        View.OnTouchListener handleTouch = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchAction(x, y);
                        break;
                }
                return true;
            }
        };
        findViewById(R.id.floor).setOnTouchListener(handleTouch);


        setButtonSize();
        setRoomSpritesNames(game);
        setSpriteHeight();
        setCharacter();
        setDoors();

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
                .setCancelable(true)
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

    /**
     * set the size and position of the character
     */
    private void setCharacter(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int height = myPreferences.getInt("CELL_HEIGHT", 0);
        int width = myPreferences.getInt("CELL_WIDTH", 0);

        android.view.ViewGroup.LayoutParams params_character = findViewById(R.id.character).getLayoutParams();
        params_character.height = 2 * height;
        params_character.width = width;
        findViewById(R.id.character).setX(game.getCharacter().getRoomX() * width);
        findViewById(R.id.character).setY((game.getCharacter().getRoomY() * height)-(height/3));
        findViewById(R.id.character).setLayoutParams(params_character);
    }


    /**
     * get the ID of a ressource from its name*
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

    /**
     *the action when there the gameArea is touched
     *
     * @param x the x axis
     * @param y the y axis
     */
    public void touchAction(int x, int y){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int height = myPreferences.getInt("CELL_HEIGHT", 0);
        int width = myPreferences.getInt("CELL_WIDTH", 0);

        int cellX = x / height;
        int cellY = y / width;
        Log.i("TAG", "dans touchaction :" + x + " - " + y + ", " + cellX + " - " + cellY);

        // if the touch is inside the moving area of the character
        if(cellX>1 && cellX<12 && cellY>1 && cellY<20) {

            View character_view = findViewById(R.id.character);



            ObjectAnimator animX = ObjectAnimator.ofFloat(character_view, "x", (game.getCharacter().getRoomX()*width), (cellX * width));
            ObjectAnimator animY = ObjectAnimator.ofFloat(character_view, "y",  ((game.getCharacter().getRoomY()*height)-(height/3)), (((cellY-1) * height)-(height/3)));
            AnimatorSet animSetXY = new AnimatorSet();
            animSetXY.playTogether(animX, animY);
            animSetXY.start();

            game.getCharacter().setRoomX(cellX);
            game.getCharacter().setRoomY(cellY -1);

            //setCharacter();
        }

        if(cellX<2 && cellY>10 && cellY<13){

            View character_view = findViewById(R.id.character);

            ObjectAnimator animX = ObjectAnimator.ofFloat(character_view, "x", (game.getCharacter().getRoomX()*width), (2 * width));
            ObjectAnimator animY = ObjectAnimator.ofFloat(character_view, "y",  ((game.getCharacter().getRoomY()*height)-(height/3)), (((11) * height)-(height/3)));
            AnimatorSet animSetXY = new AnimatorSet();
            animSetXY.playTogether(animX, animY);
            animSetXY.start();

            animSetXY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator a) {
                    door_west_pressed();
                }

                @Override
                public void onAnimationStart(Animator a) {

                }

                @Override
                public void onAnimationCancel(Animator a) {

                }

                @Override
                public void onAnimationRepeat(Animator a) {

                }

            });

        }

        if(cellX>11 && cellY>10 && cellY<13){

            View character_view = findViewById(R.id.character);

            ObjectAnimator animX = ObjectAnimator.ofFloat(character_view, "x", (game.getCharacter().getRoomX()*width), (11 * width));
            ObjectAnimator animY = ObjectAnimator.ofFloat(character_view, "y",  ((game.getCharacter().getRoomY()*height)-(height/3)), (((11) * height)-(height/3)));
            AnimatorSet animSetXY = new AnimatorSet();
            animSetXY.playTogether(animX, animY);
            animSetXY.start();

            animSetXY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator a) {
                    door_east_pressed();
                }

                @Override
                public void onAnimationStart(Animator a) {

                }

                @Override
                public void onAnimationCancel(Animator a) {

                }

                @Override
                public void onAnimationRepeat(Animator a) {

                }

            });

        }

        if(cellY<2 && cellX>5 && cellX<8){

            View character_view = findViewById(R.id.character);

            ObjectAnimator animX = ObjectAnimator.ofFloat(character_view, "x", (game.getCharacter().getRoomX()*width), (6 * width));
            ObjectAnimator animY = ObjectAnimator.ofFloat(character_view, "y",  ((game.getCharacter().getRoomY()*height)-(height/3)), (((1) * height)-(height/3)));
            AnimatorSet animSetXY = new AnimatorSet();
            animSetXY.playTogether(animX, animY);
            animSetXY.start();

            animSetXY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator a) {
                    door_north_pressed();
                }

                @Override
                public void onAnimationStart(Animator a) {

                }

                @Override
                public void onAnimationCancel(Animator a) {

                }

                @Override
                public void onAnimationRepeat(Animator a) {

                }

            });

        }

        if(cellY>19 && cellX>5 && cellX<8){

            View character_view = findViewById(R.id.character);

            ObjectAnimator animX = ObjectAnimator.ofFloat(character_view, "x", (game.getCharacter().getRoomX()*width), (6 * width));
            ObjectAnimator animY = ObjectAnimator.ofFloat(character_view, "y",  ((game.getCharacter().getRoomY()*height)-(height/3)), (((18) * height)-(height/3)));
            AnimatorSet animSetXY = new AnimatorSet();
            animSetXY.playTogether(animX, animY);
            animSetXY.start();

            animSetXY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator a) {
                    door_south_pressed();
                }

                @Override
                public void onAnimationStart(Animator a) {

                }

                @Override
                public void onAnimationCancel(Animator a) {

                }

                @Override
                public void onAnimationRepeat(Animator a) {

                }

            });

        }
    }

    private void door_west_pressed() {
        game.moveToWest();
        game.getCharacter().getActualRoom().set_Visited();
        game.getCharacter().setRoomX(11);
        game.getCharacter().setRoomY(11);
        updateSprites();
    }

    private void door_east_pressed() {
        game.moveToEast();
        game.getCharacter().getActualRoom().set_Visited();
        game.getCharacter().setRoomX(2);
        game.getCharacter().setRoomY(11);
        updateSprites();
    }

    private void door_south_pressed() {
        game.moveToSouth();
        game.getCharacter().getActualRoom().set_Visited();
        game.getCharacter().setRoomX(6);
        game.getCharacter().setRoomY(1);
        updateSprites();
    }

    private void door_north_pressed() {
        game.moveToNorth();
        game.getCharacter().getActualRoom().set_Visited();
        game.getCharacter().setRoomX(6);
        game.getCharacter().setRoomY(18);
        updateSprites();
    }

    private void updateSprites() {
        setRoomSpritesNames(game);
        setCharacter();
        setDoors();
    }



    /**
     *
     */
    public void setDoors(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int height = myPreferences.getInt("CELL_HEIGHT", 0);
        int width = myPreferences.getInt("CELL_WIDTH", 0);
        int gameAreaHeight = myPreferences.getInt("GAMEAREA_HEIGHT", 0);
        int gameAreaWidth = myPreferences.getInt("GAMEAREA_WIDTH", 0);

        if(game.getCharacter().getActualRoom().isDoor_east()){
            android.view.ViewGroup.LayoutParams params_door_east = findViewById(R.id.door_east).getLayoutParams();
            params_door_east.height = 2 * height;
            params_door_east.width = 2 * width;
            //findViewById(R.id.door_east).setX((float)11.4*height);
            findViewById(R.id.door_east).setX(gameAreaWidth-(2*width));
            findViewById(R.id.door_east).setY((float)10.4*width);
            findViewById(R.id.door_east).setLayoutParams(params_door_east);
        }
        if(game.getCharacter().getActualRoom().isDoor_west()){
            android.view.ViewGroup.LayoutParams params_door_west = findViewById(R.id.door_west).getLayoutParams();
            params_door_west.height = 2 * height;
            params_door_west.width = 2 * width;
            findViewById(R.id.door_west).setX(0);
            findViewById(R.id.door_west).setY((float)10.4*width);
            findViewById(R.id.door_west).setLayoutParams(params_door_west);
        }
        if(game.getCharacter().getActualRoom().isDoor_north()){
            android.view.ViewGroup.LayoutParams params_door_north = findViewById(R.id.door_north).getLayoutParams();
            params_door_north.height = 2 * height;
            params_door_north.width = 2 * width;
            findViewById(R.id.door_north).setX((float)6*width);
            findViewById(R.id.door_north).setY(0);
            findViewById(R.id.door_north).setLayoutParams(params_door_north);
        }
        if(game.getCharacter().getActualRoom().isDoor_south()){
            android.view.ViewGroup.LayoutParams params_door_south = findViewById(R.id.door_south).getLayoutParams();
            params_door_south.height = 2 * height;
            params_door_south.width = 2 * width;
            findViewById(R.id.door_south).setX((float)6*width);
            //findViewById(R.id.door_south).setY((float)20.6*width);
            findViewById(R.id.door_south).setY(gameAreaHeight-(2*height));
            findViewById(R.id.door_south).setLayoutParams(params_door_south);
        }
    }
}