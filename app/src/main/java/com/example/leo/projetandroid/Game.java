package com.example.leo.projetandroid;

import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

public class Game {

    private Inventory inventory;
    private Character character;
    private Map map;

    //used for the singleton implementation
    private static Game instance;

    private Game( SQLiteDatabase DB ) {
        this.inventory = Inventory.getInstance();

        this.map = Map.getInstance( DB );

        this.character = Character.getInstance(map.getRoom(0));
    }

    /**
     * Usage of the singleton pattern for the Game class
     *
     * @return the unique instance of the game
     */
    public static synchronized Game getInstance( SQLiteDatabase DB ) {
        if (instance == null) {
            instance = new Game( DB );
        }
        return instance;
    }

    // The following 4 methods move the character to the next room ( west go to the left one and etc )
    // Could be done by one method probably

    public void moveToWest() {

        Room tempRoom = character.getActualRoom();
        int latitude = tempRoom.get_latitude();
        int longitude = tempRoom.get_longitude()-1;

        character.setRoom( map.getRoom( latitude, longitude ) );

        tempRoom = map.getRoom( latitude, longitude-1 );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude, longitude-1, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        tempRoom = map.getRoom( latitude-1, longitude );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude-1, longitude, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        tempRoom = map.getRoom( latitude+1, longitude );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude+1, longitude, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        map.updateMinMaxLongLat();

    }

    public void moveToEast() {

        Room tempRoom = character.getActualRoom();
        int latitude = tempRoom.get_latitude();
        int longitude = tempRoom.get_longitude()+1;

        character.setRoom( map.getRoom( latitude, longitude ) );

        tempRoom = map.getRoom( latitude, longitude+1 );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude, longitude+1, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        tempRoom = map.getRoom( latitude-1, longitude );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude-1, longitude, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        tempRoom = map.getRoom( latitude+1, longitude );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude+1, longitude, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        map.updateMinMaxLongLat();

    }

    public void moveToSouth() {

        Room tempRoom = character.getActualRoom();
        int latitude = tempRoom.get_latitude()+1;
        int longitude = tempRoom.get_longitude();

        character.setRoom( map.getRoom( latitude, longitude ) );

        tempRoom = map.getRoom( latitude+1, longitude );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude+1, longitude, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        tempRoom = map.getRoom( latitude, longitude-1 );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude, longitude-1, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        tempRoom = map.getRoom( latitude, longitude+1 );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude, longitude+1, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        map.updateMinMaxLongLat();

    }

    public void moveToNorth() {

        Room tempRoom = character.getActualRoom();
        int latitude = tempRoom.get_latitude()-1;
        int longitude = tempRoom.get_longitude();

        character.setRoom( map.getRoom( latitude, longitude ) );

        tempRoom = map.getRoom( latitude-1, longitude );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude-1, longitude, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        tempRoom = map.getRoom( latitude, longitude-1 );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude, longitude-1, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        tempRoom = map.getRoom( latitude, longitude+1 );
        if ( tempRoom == null ) {
            tempRoom = new Room ( latitude, longitude+1, true, true, true, true );
            map.addRoom ( tempRoom );
            map.incrNbRooms();
            map.saveRoom ( tempRoom );
        }

        map.updateMinMaxLongLat();

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

    public Character getCharacter(){ return this.character;}

}
