package com.example.leo.projetandroid;

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

    public String wall_east_ressource() {
        return this.character.get_actual_room().get_wall_east();
    }

}
