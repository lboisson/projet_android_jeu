package com.example.leo.projetandroid;

public class Character {

    //used for the singleton implementation
    private static Character instance;
    private Character(){

    }

    /**
     * Usage of the singleton pattern for the Game class
     * @return the unique instance of the game
     */
    public static synchronized Character getInstance(){
        if(instance == null){
            instance = new Character();
        }
        return instance;
    }

}
