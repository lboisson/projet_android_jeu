package com.example.leo.projetandroid;

import java.util.Random;

public class Room {

    //maybe change this, the number of different sprites existing
    private int numberOfSprites = 2;

    // the coordinates of the room (used for the map_display)
    private int latitude;
    private int longitude;

    // the name of the sprites used for this particular room
    private String floor, wall_east, wall_west, wall_north, wall_south;

    private int state = 0;

    /**
     * constructor of the Room class
     */
    public Room(int latitude, int longitude){
        this.latitude = latitude;
        this.longitude = longitude;

        this.floor = generateNameRandomSprite("floor");
        this.wall_east = generateNameRandomSprite("wall_east");
        this.wall_west = generateNameRandomSprite("wall_west");
        this.wall_north = generateNameRandomSprite("wall_north");
        this.wall_south = generateNameRandomSprite("wall_south");
    }

    public Room(int latitude, int longitude, String floor, String wall_west, String wall_east, String wall_south, String wall_north, int state ) {
        this.latitude = latitude;
        this.longitude = longitude;

        this.floor = floor;
        this.wall_west = wall_west;
        this.wall_east = wall_east;
        this.wall_south = wall_south;
        this.wall_north = wall_north;

        this.state = state;
    }

    /**
     *
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

    public void set_Visited() { this.state = 1; }

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

    public int get_state() { return this.state; }

}
