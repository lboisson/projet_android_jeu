package com.example.leo.projetandroid.Display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
     * go to the Inventory activity
     * @param v
     */
    public void InventoryButton(View v){
        Intent intent = new Intent(this, InventoryDisplay.class);
        startActivity(intent);
        finish();
    }

    /**
     * go to the map activity
     * @param v
     */
    public void MapButton(View v){ finish(); }

    /**
     * go to the character activity
     * @param v
     */
    public void CharacterButton(View v){
        Intent intent = new Intent(this, CharacterDisplay.class);
        startActivity(intent);
        finish();
    }

}
