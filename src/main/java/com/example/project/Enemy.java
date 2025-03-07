package com.example.project;

public class Enemy extends Sprite {
    
    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(String direction) {
        // Implement random or chasing movement
    }

    @Override
    public String getCoords(){
        return "Enemy:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){
        return "Enemy:"+super.getRowCol(size);
    }
}