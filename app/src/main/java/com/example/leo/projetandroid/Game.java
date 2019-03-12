package com.example.leo.projetandroid;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Context;

public class Game {

    private Inventory inventory;
    private Character character;
    private Map map;


    //used for the singleton implementation
    private static Game instance;

    private Game() {
        this.inventory = Inventory.getInstance();

        this.map = Map.getInstance();

        this.character = Character.getInstance(map.getRoom(0));


    }


    /**
     * Usage of the singleton pattern for the Game class
     *
     * @return the unique instance of the game
     */
    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;

    }

    //        GETTERS         //

    public String getWallEastRessource() {
        return this.character.getActualRoom().get_wall_east();
    }

    public String getWallWestRessource() {
        return this.character.getActualRoom().get_wall_west();
    }

    public String getWallNorthRessource() {
        return this.character.getActualRoom().get_wall_north();
    }

    public String getWallSouthRessource() {
        return this.character.getActualRoom().get_wall_south();
    }

    public String getFloorRessource() {
        return this.character.getActualRoom().get_floor();
    }

    public String getCharacterSprite(){
        return this.character.getCharacterSprite();
    }

    public int getCharacterX(){
        return this.character.getRoomX();
    }

    public int getCharacterY(){
        return this.character.getRoomY();
    }

}
