package com.example.project;
public class Player  {
    private int treasureCount;
    private int numLives;
    private boolean win = false;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
    }

    //DO NOT DELETE THE GETTERS
    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    //DO NOT DELETE
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
    }

    //DO NOT DELETE
    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
    }

    //DO NOT DELETE
    public boolean isValid(int size, String direction){ //check grid boundaries
        return false;
    }


   
}



