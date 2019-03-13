package com.example.leo.projetandroid.Display;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.leo.projetandroid.Map;
import com.example.leo.projetandroid.R;

public class MapDisplay extends ButtonsDisplay {

    private Map map;

    @Override
    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_display);

        SQLiteDatabase ADB = openOrCreateDatabase(
                "ADB.db"
                , SQLiteDatabase.CREATE_IF_NECESSARY
                , null
        );
        this.map = Map.getInstance( ADB );

        set_TableLayout();
        set_Map();

        setButtonSize();

        findViewById(R.id.button_map).setBackgroundResource(R.drawable.button_clicked);
    }

    private void set_TableLayout() {

        TableLayout layout = findViewById(R.id.map_layout);

        int sizeLong = ( map.getMaxLong() ) - ( map.getMinLong() ) + 1;
        int sizeLat = ( map.getMaxLat() ) - ( map.getMinLat() ) + 1;


    }

    private void set_Map() {

        TableLayout layout = findViewById(R.id.map_layout);
        for(int i=0;i<10;i++)
        {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
            image.setMaxHeight(20);
            image.setMaxWidth(20);

            // Adds the view to the layout
            layout.addView(image);
        }

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
