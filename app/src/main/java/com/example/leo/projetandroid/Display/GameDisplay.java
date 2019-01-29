package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.leo.projetandroid.Character;
import com.example.leo.projetandroid.Inventory;
import com.example.leo.projetandroid.Display.ButtonsDisplay;
import com.example.leo.projetandroid.R;

public class GameDisplay extends ButtonsDisplay {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        setButtonSize();
        setSpriteHeight();
    }

    /**
     * go to the Inventory activity
     * @param v
     */
    public void ActivityToInventoryDisplay(View v){
        Intent intent = new Intent(this, InventoryDisplay.class);
        startActivity(intent);
    }

    /**
     * go to the map activity
     * @param v
     */
    public void ActivityToMapDisplay(View v){
        Intent intent = new Intent(this, MapDisplay.class);
        startActivity(intent);
    }

    /**
     * go to the character activity
     * @param v
     */
    public void ActivityToCharacterDisplay(View v){
        Intent intent = new Intent(this, CharacterDisplay.class);
        startActivity(intent);
    }

    /**
     * use the equiped object
     */
    public void useObject(View v) {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Using your object", duration);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     *
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
