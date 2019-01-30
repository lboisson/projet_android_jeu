package com.example.leo.projetandroid;

public class Game {

    //used for the singleton implementation
    private static Game instance;
    private Game(){ }

    private Inventory inventory;
    private Character character;
    private Map map;

    /**
     * Usage of the singleton pattern for the Game class
     * @return the unique instance of the game
     */
    public static synchronized Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

}
