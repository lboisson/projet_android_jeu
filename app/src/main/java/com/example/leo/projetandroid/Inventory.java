package com.example.leo.projetandroid;

public class Inventory {

    //used for the singleton implementation
    private static Inventory instance;
    private Inventory(){ }

    /**
     * Usage of the singleton pattern for the Inventory class
     * @return the unique instance of the inventory
     */
    public static synchronized Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }
}
