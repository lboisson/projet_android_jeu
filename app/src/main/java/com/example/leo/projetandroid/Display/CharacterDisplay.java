package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.leo.projetandroid.R;

public class CharacterDisplay extends ButtonsDisplay {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_display);

        setButtonSize();

        findViewById(R.id.button_you).setBackgroundResource(R.drawable.button_clicked);
    }

    /**
     * overload the character button, go back to game
     * @param v
     */
    public void CharacterButton(View v){
        Intent intent = new Intent(this, GameDisplay.class);
        startActivity(intent);
    }
}
