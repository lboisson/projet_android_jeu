package com.example.leo.projetandroid;

public class Character {

    private Room actual_room;

    //used for the singleton implementation
    private static Character instance;
    private Character(Room room){

        this.actual_room = room;
    }

    /**
     * Usage of the singleton pattern for the Game class
     * @return the unique instance of the game
     */
    public static synchronized Character getInstance(Room room){
        if(instance == null){
            instance = new Character(room);
        }
        return instance;
    }

    public Room get_actual_room(){
        return this.actual_room;
    }

}
