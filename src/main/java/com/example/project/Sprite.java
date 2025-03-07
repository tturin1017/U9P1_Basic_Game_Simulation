package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}

    public String getCoords(){
        return "("+x+", "+(y)+")";
    }

    public String getRowCol(int size){
        return "["+((size-1)-y)+"]"+"["+x+"]";
    }
    

    public void move(String direction) {
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() {
        // Default behavior (can be overridden by subclasses)
    }

}