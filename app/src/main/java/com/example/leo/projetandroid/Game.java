package com.example.leo.projetandroid;

import android.database.sqlite.SQLiteDatabase;

public class Game {

    private Inventory inventory;
    private Character character;
    private Map map;

    //used for the singleton implementation
    private static Game instance;

    private Game( SQLiteDatabase DB ) {
        this.inventory = Inventory.getInstance();

        this.map = Map.getInstance( DB );

        this.character = Character.getInstance(map.getRoom(0));
    }

    /**
     * Usage of the singleton pattern for the Game class
     *
     * @return the unique instance of the game
     */
    public static synchronized Game getInstance( SQLiteDatabase DB ) {
        if (instance == null) {
            instance = new Game( DB );
        }
        return instance;
    }

    public String wall_east_ressource() {
        return this.character.getActualRoom().get_wall_east();
    }

    public String wall_west_ressource() {
        return this.character.getActualRoom().get_wall_west();
    }

    public String wall_north_ressource() {
        return this.character.getActualRoom().get_wall_north();
    }

    public String wall_south_ressource() {
        return this.character.getActualRoom().get_wall_south();
    }

    public String floor_ressource() {
        return this.character.getActualRoom().get_floor();
    }

}
