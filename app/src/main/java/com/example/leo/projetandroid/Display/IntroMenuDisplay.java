package com.example.leo.projetandroid.Display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.leo.projetandroid.R;

public class IntroMenuDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intromenu_display);
    }

    public void ActivityToGameDisplay(View v){
        Intent intent = new Intent(this, GameDisplay.class);
        startActivity(intent);
    }

    public void ActivityToOption(View v){
        Intent intent = new Intent(this, OptionDisplay.class);
        startActivity(intent);
    }
}
