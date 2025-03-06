package com.example.project;
public class Player extends Sprite {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(String direction) {
        // Implement player movement based on user input
        if(direction.equals("w")){
            this.setY(this.getY()-1); //move ^^^
        }else if(direction.equals("a")){
            this.setX(this.getX()-1); //move <<
        }else if(direction.equals("s")){
            this.setY(this.getY()+1); //move vv
        }else if(direction.equals("d")){
            this.setX(this.getX()+1); //move >>
        }
    }

    @Override
    public void interact() {
        // Implement interaction logic (e.g., collect treasure)
    }

    // public void interact(String x){ //overloaded

    // }
}



