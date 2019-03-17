package com.example.leo.projetandroid.Display;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.example.leo.projetandroid.Map;
import com.example.leo.projetandroid.Character;
import com.example.leo.projetandroid.R;
import com.example.leo.projetandroid.Room;

public class MapDisplay extends ButtonsDisplay {

    private Map map;

    private int VISITED = Color.parseColor("#FFFFFF");
    private int UNVISITED = Color.parseColor("#A9A9A9");
    private int PRESENT = Color.parseColor("#FF2800");
    private int BACKGROUND = Color.parseColor("#000000");

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

        // Set the map
        set_ColorLayout();
        set_Map();

        // Set the buttons
        setButtonSize();
        findViewById(R.id.button_map).setBackgroundResource(R.drawable.button_clicked);

    }


    /**
     * Set the background color for the map
     */
    private void set_ColorLayout() {

        findViewById(R.id.map_layout).setBackgroundColor( BACKGROUND );

    }

    /**
     * Create and print all the rooms
     */
    private void set_Map() {

        ConstraintLayout layout = findViewById(R.id.map_layout);

        int heightScreen;
        int widthScreen;

        int sizeRoom = 30;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        heightScreen = preferences.getInt("GAMEAREA_HEIGHT", 0);
        widthScreen = preferences.getInt("GAMEAREA_WIDTH", 0);

        int centerX = ( widthScreen - sizeRoom )/2;
        int centerY = ( heightScreen - sizeRoom )/2;

        Room characRoom = Character.getInstance().getActualRoom();
        int characLat = characRoom.get_latitude();
        int characLong = characRoom.get_longitude();

        View roomView;
        LayoutParams params;
        Room tempRoom;

        // Go through all the room and set them each in a View
        for ( int i = 0; i < map.getNumberOfRoom(); i++ ) {

            roomView = new View( this );
            params = new LayoutParams( sizeRoom, sizeRoom );

            tempRoom = map.getRoom(i);

            roomView.setX(centerX+((tempRoom.get_longitude()-characLong)*45));
            roomView.setY(centerY+((tempRoom.get_latitude()-characLat)*45));
            roomView.setLayoutParams(params);

            if ( tempRoom.get_state() == 0 ) {
                roomView.setBackgroundColor(UNVISITED);
            } else {
                roomView.setBackgroundColor(VISITED);
            }

            layout.addView( roomView );

        }

        // Set the character room
        roomView = new View( this );
        params = new LayoutParams( sizeRoom, sizeRoom );

        roomView.setX(centerX);
        roomView.setY(centerY);
        roomView.setLayoutParams(params);

        roomView.setBackgroundColor(PRESENT);

        layout.addView( roomView );

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