package com.example.project;
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win = false;

    public Player(int x, int y) {
        super(x, y);
        treasureCount = 0;
        numLives = 2;
    }

    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    @Override
    public void move(String direction) {
        // Implement player movement based on user input
        if(direction.equals("w")){
            this.setY(this.getY()+1); //move ^^^
        }else if(direction.equals("a")){
            this.setX(this.getX()-1); //move <<
        }else if(direction.equals("s")){
            this.setY(this.getY()-1); //move vv
        }else if(direction.equals("d")){
            this.setX(this.getX()+1); //move >>
        }
    }

    @Override
    public String getCoords(){
        return "Player:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){
        return "Player:"+ super.getRowCol(size);
    }



    public void interact(int size, String direction, int numTreasures, Object obj) {
            if(obj instanceof Treasure && !(obj instanceof Trophy)){
                treasureCount++;
            }else if(obj instanceof Enemy){
                numLives--;
            }else if(obj instanceof Trophy){
                if(treasureCount==numTreasures){
                    win = true;
                }else{
                    switch(direction){
                        case "w": move("s"); break;
                        case "a": move("d"); break;
                        case "s": move("w"); break;
                        case "d": move("a"); break;
                    }
                }
            }
    }

    public boolean isValid(int size, String direction){ //checking boundaries
        switch(direction){
            case "w": return (getY()+1)<size; 
            case "a": return (getX()-1)>=0;
            case "s": return (getY()-1)>=0;
            case "d": return (getX()+1)<size;
        }

        return false;
    }


   
}



