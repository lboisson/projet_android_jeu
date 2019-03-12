package com.example.leo.projetandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.leo.projetandroid.DBContractTest.*;

public class Game {

    private Inventory inventory;
    private Character character;
    private Map map;

    public SQLiteDatabase ADB;

    //used for the singleton implementation
    private static Game instance;

    private Game() {
        this.inventory = Inventory.getInstance();

        this.map = Map.getInstance();

        this.character = Character.getInstance(map.getRoom(0));
    }


    /**
     * Usage of the singleton pattern for the Game class
     *
     * @return the unique instance of the game
     */
    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void saveEverything () {
        for (int i = 0; i < map.listRoom/*CreatedThisInstance*/.size(); i++) {
            saveRoom( map.listRoom/*CreatedThisInstance*/.get(i) );
        }
    }

    private void saveRoom ( Room room ) {

        /*ContentValues values = new ContentValues();
        values.put(table_ROOM.COLUMN_LONG, room.longitude);
        values.put(table_ROOM.COLUMN_LAT, room.latitude);
        values.put(table_ROOM.COLUMN_FLOOR, 0);
        values.put(table_ROOM.COLUMN_FLOOR_SPRITE_NUMBER, room.floor);
        values.put(table_ROOM.COLUMN_WALL_WEST_SPRITE_NAME, room.wall_west);
        values.put(table_ROOM.COLUMN_WALL_EAST_SPRITE_NAME, room.wall_east);
        values.put(table_ROOM.COLUMN_WALL_SOUTH_SPRITE_NAME, room.wall_south);
        values.put(table_ROOM.COLUMN_WALL_NORTH_SPRITE_NAME, room.wall_north);

        ADB.insert(table_ROOM.TABLE_NAME, null, values);*/

        final String Insert_Data="INSERT INTO t_ROOM VALUES (NULL,"+room.longitude+","+room.latitude+",0,'"+room.floor+"','"+room.wall_west+"','"+room.wall_east+"','"+room.wall_south+"','"+room.wall_north+"')";
        ADB.execSQL(Insert_Data);

    }

    public int getNumberOfRows () {

        String[] projection = {
                "Id_Room"
        };

        Cursor cursor = ADB.query(
                table_ROOM.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return ( cursor.getCount() );

    }

    public String wall_east_ressource() {
        return this.character.getActualRoom().get_wall_east();
    }

    public String wall_west_ressource() {
        return this.character.getActualRoom().get_wall_west();
    }

    public String wall_north_ressource() {
        return this.character.getActualRoom().get_wall_north();
    }

    public String wall_south_ressource() {
        return this.character.getActualRoom().get_wall_south();
    }

    public String floor_ressource() {
        return this.character.getActualRoom().get_floor();
    }

}
