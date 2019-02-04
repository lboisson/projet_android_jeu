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

    /**
     * constructor of the Room class
     */
    public Room(int latitude, int longitude){
        this.latitude = latitude;
        this.longitude = longitude;

        //TODO : random assignement of sprites for the floor, walls, doors
        this.floor = generateNameRandomSprite("floor");
        this.wall_east = generateNameRandomSprite("wall_east");
        this.wall_west = generateNameRandomSprite("wall_west");
        this.wall_north = generateNameRandomSprite("wall_north");
        this.wall_south = generateNameRandomSprite("wall_south");
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



    //        GETTERS         //

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

    public String get_floor(){
        return this.floor;
    }

}
