package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.example.leo.projetandroid.R;

public class GameDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        setButtonSize();
        setSpriteHeight();
    }


    /**
     * used to set the size of the bottom buttons
     * @return 1/4 of the width of the screen
     */
    public int getWidthOfButton(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return ((size.x)/4);
    }

    /**
     *
     * @return the height of the sprites (the height of the screen minus the height of the buttons).
     */
    public int getHeightOfSprites(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return ((size.y)-getWidthOfButton());
    }

    public int getWidthOfSprites(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * Set the size of the buttons according the the screen width
     * (each button width and height is 1/4 of the screen width)
     */
    private void setButtonSize(){
        int size = (int)getWidthOfButton();
        android.view.ViewGroup.LayoutParams params_1 = findViewById(R.id.button_1).getLayoutParams();
        params_1.height = size;
        params_1.width = size;
        findViewById(R.id.button_1).setLayoutParams(params_1);

        android.view.ViewGroup.LayoutParams params_2 = findViewById(R.id.button_2).getLayoutParams();
        params_2.height = size;
        params_2.width = size;
        findViewById(R.id.button_2).setLayoutParams(params_2);

        android.view.ViewGroup.LayoutParams params_3 = findViewById(R.id.button_3).getLayoutParams();
        params_3.height = size;
        params_3.width = size;
        findViewById(R.id.button_3).setLayoutParams(params_3);

        android.view.ViewGroup.LayoutParams params_4 = findViewById(R.id.button_4).getLayoutParams();
        params_4.height = size;
        params_4.width = size;
        findViewById(R.id.button_4).setLayoutParams(params_4);
    }

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


}
