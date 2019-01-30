package com.example.leo.projetandroid.Display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.leo.projetandroid.R;

public class InventoryDisplay extends ButtonsDisplay {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

        setButtonSize();

        findViewById(R.id.button_bag).setBackgroundResource(R.drawable.button_clicked);
    }

    /**
     * overload that method, goes back to game
     * @param v
     */
    public void InventoryButton(View v){
        Intent intent = new Intent(this, GameDisplay.class);
        startActivity(intent);
    }

}
