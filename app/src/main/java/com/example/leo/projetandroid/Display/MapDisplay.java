package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.leo.projetandroid.R;

public class MapDisplay extends ButtonsDisplay {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_display);

        setButtonSize();

        findViewById(R.id.button_map).setBackgroundResource(R.drawable.button_clicked);
    }

    /**
     * overload the map button to go back to game
     * @param v
     */
    public void MapButton(View v){
        Intent intent = new Intent(this, GameDisplay.class);
        startActivity(intent);
    }

}
