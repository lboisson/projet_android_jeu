package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.os.Bundle;

import com.example.leo.projetandroid.R;

public class InventoryDisplay extends ButtonsDisplay {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

        setButtonSize();
    }

}
