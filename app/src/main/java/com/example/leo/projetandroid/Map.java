package com.example.leo.projetandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Map {

    //the list of rooms. first room start at 0.
    private List<Room> listRoom = new ArrayList<>();
    private int numberOfRoom = 0;
    private int minLong, maxLong, minLat, maxLat;

    public SQLiteDatabase ADB;

    private static SQLiteDatabase ADBstatic;

    //used for the singleton implementation
    private static Map instance;

    /**
     * constructor
     */
    private Map( SQLiteDatabase DB ){
        ADB = DB;
        ADBstatic = DB;
        loadOrCreateRooms();
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

    /**
     * Set the room corresponding to Lat/Long to Visited
     * @param Lat Latitude of the room
     * @param Long Longitude of the room
     */
    public static void set_Visited(int Lat, int Long) {

        ContentValues cv = new ContentValues();
        cv.put("Visited", 1);
        ADBstatic.update ( "t_room", cv, "Lat = ? AND Long = ?", new String[]{String.valueOf(Lat), String.valueOf(Long)} );

    }

    /**
     * Load all the rooms from the Database and if it is empty, create the first 5 rooms ( center, up, down, left, right ), load them and save them
     */
    private void loadOrCreateRooms () {

        int rowCount;
        int i;
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
            tempRoom = new Room ( 0, 0, true, true, true, true );
            addRoom ( tempRoom );
            saveRoom ( tempRoom );
            tempRoom = new Room ( -1, 0, true, true, true, true );
            addRoom ( tempRoom );
            saveRoom ( tempRoom );
            tempRoom = new Room ( 1, 0, true, true, true, true );
            addRoom ( tempRoom );
            saveRoom ( tempRoom );
            tempRoom = new Room ( 0, -1, true, true, true, true );
            addRoom ( tempRoom );
            saveRoom ( tempRoom );
            tempRoom = new Room ( 0, 1, true, true, true, true );
            addRoom ( tempRoom );
            saveRoom ( tempRoom );
            this.minLong = -1;
            this.maxLong = 1;
            this.minLat = -1;
            this.maxLat = 1;
            this.numberOfRoom = 5;

        } else {

            cursor.moveToFirst();
            for ( i = 0; i < rowCount; i++ ) {
                tempRoom = new Room ( cursor.getInt(1), cursor.getInt(2), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getInt(9), true, true, true, true);
                addRoom ( tempRoom );
                incrNbRooms();
                cursor.moveToNext();
            }

            updateMinMaxLongLat();

        }

    }

    /**
     * Save room in the Database
     * @param room room that has to be saved
     */
    public void saveRoom ( Room room ) {

        final String Insert_Data="INSERT INTO t_room VALUES (NULL,"+room.get_latitude()+","+room.get_longitude()+",0,'"+room.get_floor()+"','"+room.get_wall_west()+"','"+room.get_wall_east()+"','"+room.get_wall_south()+"','"+room.get_wall_north()+"','"+room.get_state()+"')";
        ADB.execSQL(Insert_Data);

    }

    /**
     * get the room corresponding to the index
     * @param index index of the room
     * @return the room
     */
    public Room getRoom(int index){
        if ( index > numberOfRoom ) {
            return null;
        }
        return this.listRoom.get(index);
    }

    /**
     * get the room corresponding to the Latitude / Longitude passed
     * @param Lat Latitude of the room
     * @param Long Longitude of the room
     * @return the room
     */
    public Room getRoom( int Lat, int Long ) {

        Room room;

        for ( int i = 0; i < getNumberOfRoom(); i++ ) {
            room = getRoom(i);
            if ( ( room.get_longitude() == Long ) && ( room.get_latitude() == Lat ) ) { return room; }
        }

        return null;
    }

    /**
     * Add the room in the list of room
     * @param room room that has to be saved
     */
    public void addRoom( Room room ) {
        listRoom.add(room);
    }

    /**
     * Get the number of rooms in the list of room
     * @return the number
     */
    public int getNumberOfRoom () {
        return this.numberOfRoom;
    }

    /**
     * Update MinMax values for Latitude/Longitude, used in MapDisplay
     */
    public void updateMinMaxLongLat () {

        Room tempRoom;

        for ( int i = 0; i < getNumberOfRoom(); i++ ) {

            tempRoom = getRoom ( i );

            if ( tempRoom.get_longitude() < minLong ) { this.minLong = tempRoom.get_longitude(); }
            if ( tempRoom.get_longitude() > maxLong ) { this.maxLong = tempRoom.get_longitude(); }
            if ( tempRoom.get_longitude() < minLat ) { this.minLat = tempRoom.get_latitude(); }
            if ( tempRoom.get_longitude() > maxLat ) { this.maxLat = tempRoom.get_latitude(); }

        }
    }

    public int getMinLong() { return this.minLong; }

    public int getMaxLong() { return this.maxLong; }

    public int getMinLat() { return this.minLat; }

    public int getMaxLat() { return this.maxLat; }

    public void incrNbRooms() { this.numberOfRoom += 1; }

}
