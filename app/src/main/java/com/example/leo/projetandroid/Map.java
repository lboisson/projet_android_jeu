package com.example.leo.projetandroid;

import java.util.ArrayList;
import java.util.List;

public class Map {

    //the list of rooms. first room start at 0.
    private List<Room> listRoom = new ArrayList<>();



    //used for the singleton implementation
    private static Map instance;

    /**
     * constructor
     */
    private Map(){
        listRoom.add(new Room(0,0));
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
        return this.listRoom.get(index);
    }
}
