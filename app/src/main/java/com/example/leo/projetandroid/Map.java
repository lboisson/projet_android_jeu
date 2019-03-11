package com.example.leo.projetandroid;

import java.util.ArrayList;
import java.util.List;

public class Map {

    //the list of rooms. first room start at 0.
    public List<Room> listRoom = new ArrayList<>();
    public List<Room> listRoomCreatedThisInstance = new ArrayList<>();
    public int numberOfRoom = 0;


    //used for the singleton implementation
    private static Map instance;

    /**
     * constructor
     */
    private Map(){
        Room room = new Room(0,0 );
        listRoom.add( room );
        listRoomCreatedThisInstance.add( room );
    }

    /**
     * Usage of the singleton pattern for the Map class
     * @return the unique instance of the map
     */
    public static synchronized Map getInstance(){
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }

    public Room getRoom(int index){
        if ( index > numberOfRoom ) {
            return this.listRoom.get(0); // Return room 0 by default
        }
        return this.listRoom.get(index);
    }
}
