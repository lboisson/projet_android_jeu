package com.example.leo.projetandroid;

public class Character {

    private Room actual_room;
    private String characterSprite;

    //position of the character in his actual_room
    private int roomY;
    private int roomX;

    private boolean lookingEast;

    //used for the singleton implementation
    private static Character instance;
    private Character(Room room){

        this.actual_room = room;
        room.set_Visited();
        this.characterSprite = "character";
        this.lookingEast = false;

        this.roomX = 2;
        this.roomY = 18;

    }

    private Character(){}

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

    public static synchronized Character getInstance(){
        if(instance == null){
            instance = new Character();
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

    public void setRoomX(int X){
        this.roomX = X;
    }

    public void setRoomY(int Y){
        this.roomY = Y;
    }

    public void setRoom ( Room room ) {
        this.actual_room = room;
    }

    public boolean isLookingEast(){
        return lookingEast;
    }

    public void setLookingEast(boolean lookingEast){
        this.lookingEast = lookingEast;
    }
}
