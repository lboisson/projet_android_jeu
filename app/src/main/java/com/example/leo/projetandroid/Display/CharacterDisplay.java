package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.os.Bundle;

import com.example.leo.projetandroid.R;

public class CharacterDisplay extends ButtonsDisplay {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_display);

        setButtonSize();
    }
}
