package com.example.leo.projetandroid;

public class Character {

    private Room actual_room;
    private String characterSprite;

    //position of the character in his actual_room
    private int roomY;
    private int roomX;



    //used for the singleton implementation
    private static Character instance;
    private Character(Room room){

        this.actual_room = room;
        this.characterSprite = "character";

        this.roomX = 2;
        this.roomY = 1;

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

    public Room getActualRoom(){
        return this.actual_room;
    }

    public String getCharacterSprite(){
        return this.characterSprite;
    }

    public int getRoomY(){
        return this.roomY;
    }

    public int getRoomX(){
        return this.roomX;
    }


}
