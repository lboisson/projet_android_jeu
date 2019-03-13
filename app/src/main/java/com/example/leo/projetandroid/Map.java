package com.example.leo.projetandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Map {

    //the list of rooms. first room start at 0.
    private List<Room> listRoom = new ArrayList<>();
    private int numberOfRoom = 0;
    private int minLong, maxLong, minLat, maxLat;

    public SQLiteDatabase ADB;

    //used for the singleton implementation
    private static Map instance;

    /**
     * constructor
     */
    private Map( SQLiteDatabase DB ){
        ADB = DB;
        loadOrCreate();
    }

    /**
     * Usage of the singleton pattern for the Map class
     * @return the unique instance of the map
     */
    public static synchronized Map getInstance( SQLiteDatabase DB ){
        if(instance == null){
            instance = new Map( DB );
        }
        return instance;
    }

    private void loadOrCreate () {

        int rowCount, columnCount;
        int i, j;
        Room tempRoom;

        Cursor cursor = ADB.query(
                "t_room",
                null,
                null,
                null,
                null,
                null,
                null
        );

        rowCount = cursor.getCount();

        if ( rowCount == 0 ) {
            tempRoom = new Room ( 0, 0 );
            addRoom ( tempRoom );
            saveRoom ( tempRoom );
            this.minLong = 0;
            this.maxLong = 0;
            this.minLat = 0;
            this.maxLat = 0;
            this.numberOfRoom = 1;
        } else {

            cursor.moveToFirst();
            for ( i = 0; i < rowCount; i++ ) {
                tempRoom = new Room ( cursor.getInt(1), cursor.getInt(2), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getInt(9));
                addRoom ( tempRoom );
                if ( tempRoom.get_longitude() < minLong ) { this.minLong = tempRoom.get_longitude(); }
                if ( tempRoom.get_longitude() > maxLong ) { this.maxLong = tempRoom.get_longitude(); }
                if ( tempRoom.get_longitude() < minLat ) { this.minLat = tempRoom.get_latitude(); }
                if ( tempRoom.get_longitude() > maxLat ) { this.maxLat = tempRoom.get_latitude(); }
                this.numberOfRoom += 1;
            }

        }

    }

    private void saveRoom ( Room room ) {

        final String Insert_Data="INSERT INTO t_ROOM VALUES (NULL,"+room.get_longitude()+","+room.get_latitude()+",0,'"+room.get_floor()+"','"+room.get_wall_west()+"','"+room.get_wall_east()+"','"+room.get_wall_south()+"','"+room.get_wall_north()+"','"+room.get_state()+"')";
        ADB.execSQL(Insert_Data);

    }

    public Room getRoom(int index){
        if ( index > numberOfRoom ) {
            return this.listRoom.get(0); // Return room 0 by default
        }
        return this.listRoom.get(index);
    }

    public void addRoom( Room room ) {
        listRoom.add(room);
    }

    public int getNumberOfRoom () {
        return this.numberOfRoom;
    }

    public int getMinLong() { return this.minLong; }

    public int getMaxLong() { return this.maxLong; }

    public int getMinLat() { return this.minLat; }

    public int getMaxLat() { return this.maxLat; }

}
