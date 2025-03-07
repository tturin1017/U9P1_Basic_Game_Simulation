package com.example.project;
public class Player extends Sprite {
    private int x = this.getX();
    private int y = this.getY();
    private int count;
    private int numLives;

    public Player(int x, int y) {
        super(x, y);
        count = 0;
        numLives = 2;
    }

    public int getCount(){return count;}
    public int getLives(){return numLives;}

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
    public String getCoords(int size){
        return "Player " + super.getCoords(size);
    }

    @Override
    public String getRowCol(int size){
        return "Player "+ super.getRowCol(size);
    }

    public void interact(Sprite[][] grid, String direction) {
        int row = grid.length-1-this.getY();
        int col = this.getX();

        //if a treasure or enemy is in your way
        if(direction.equals("a") && col>0){
            if(grid[row][col-1] instanceof Treasure){
                count++;
            }else if(grid[row][col-1] instanceof Enemy){
                numLives--;
            }
        } else if(direction.equals("d") && col<grid.length-1){
            if(grid[row][col+1] instanceof Treasure){
                count++;
            }else if (grid[row][col+1] instanceof Enemy){
                numLives--;
            }
        }else if(direction.equals("w") && row>0){
            if(grid[row-1][col] instanceof Treasure){
                count++;
            }else if(grid[row-1][col] instanceof Enemy){
                numLives--;
            }
        }else if(direction.equals("s") && row<grid.length-1){
            if(grid[row+1][col] instanceof Treasure){
                count++;
            }else if(grid[row+1][col] instanceof Enemy){
                numLives--;
            }
        }
    }
    

    
    // public void interact(String x){ //overloaded

    // }
}



