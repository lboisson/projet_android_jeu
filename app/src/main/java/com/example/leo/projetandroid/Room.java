package com.example.leo.projetandroid;

public class Room {

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
        this.floor = "floor";
        this.wall_east = "wall_east_2";
        this.wall_west = "wall_west";
        this.wall_north = "wall_north";
        this.wall_south = "wall_south";
    }

    public String get_wall_east(){
        return this.wall_east;
    }

}
