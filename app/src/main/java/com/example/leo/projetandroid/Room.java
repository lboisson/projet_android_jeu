package com.example.leo.projetandroid;

import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

import com.example.leo.projetandroid.Map;

public class Room {

    //maybe change this, the number of different sprites existing
    private int numberOfSprites = 2;

    // the coordinates of the room (used for the map_display)
    private int latitude;
    private int longitude;

    // the name of the sprites used for this particular room
    private String floor, wall_east, wall_west, wall_north, wall_south;

    //is there a door ?
    private boolean door_east, door_west, door_north, door_south;

    private int state = 0;

    /**
     * constructor of the Room class
     */
    public Room(int latitude, int longitude, boolean door_east, boolean door_north, boolean door_south, boolean door_west){
        this.latitude = latitude;
        this.longitude = longitude;

        this.door_east = door_east;
        this.door_north = door_north;
        this.door_south = door_south;
        this.door_west = door_west;

        this.floor = generateNameRandomSprite("floor");
        this.wall_east = generateNameRandomSprite("wall_east");
        this.wall_west = generateNameRandomSprite("wall_west");
        this.wall_north = generateNameRandomSprite("wall_north");
        this.wall_south = generateNameRandomSprite("wall_south");
    }

    /**
     * constructor of the Room class
     */
    public Room(int latitude, int longitude, String floor, String wall_west, String wall_east, String wall_south, String wall_north, int state, boolean door_east, boolean door_north, boolean door_south, boolean door_west) {
        this.latitude = latitude;
        this.longitude = longitude;

        this.door_east = door_east;
        this.door_north = door_north;
        this.door_south = door_south;
        this.door_west = door_west;

        this.floor = floor;
        this.wall_west = wall_west;
        this.wall_east = wall_east;
        this.wall_south = wall_south;
        this.wall_north = wall_north;

        this.state = state;
    }

    /**
     * @param spriteName
     * @return
     */
    private String generateNameRandomSprite(String spriteName){
        final int randomNumber = new Random().nextInt((numberOfSprites - 1) + 1) + 1;
        if(randomNumber<10){
            return spriteName + "_00" + randomNumber;
        }
        else if(randomNumber<100){
            return spriteName + "_0" + randomNumber;
        }
        else{
            return spriteName + "_" + randomNumber;
        }
    }

    public void set_Visited() {

        this.state = 1;

        Map.set_Visited( this.latitude, this.longitude );

    }

    //        GETTERS         //

    public int get_longitude(){
        return this.longitude;
    }

    public int get_latitude(){
        return this.latitude;
    }

    public String get_floor(){
        return this.floor;
    }

    public String get_wall_east(){
        return this.wall_east;
    }

    public String get_wall_west(){
        return this.wall_west;
    }

    public String get_wall_north(){
        return this.wall_north;
    }

    public String get_wall_south(){
        return this.wall_south;
    }

    public boolean isDoor_east(){
        return door_east;
    }

    public boolean isDoor_west(){
        return door_west;
    }

    public boolean isDoor_north(){
        return door_north;
    }

    public boolean isDoor_south(){
        return door_south;
    }

    public int get_state() { return this.state; }

}
