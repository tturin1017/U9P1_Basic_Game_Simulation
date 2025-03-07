package com.example.project;
public class Player extends Sprite {
    private int x = this.getX();
    private int y = this.getY();
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

    public void interact(Sprite[][] grid, String direction, int numTreasures) {
        int row = grid.length-1-y;
        int col = x;

        //if a treasure or enemy is in your way
        if(direction.equals("a") && col>0){
            if(grid[row][col-1] instanceof Treasure &&!(grid[row][col-1] instanceof Trophy)){
                treasureCount++;
            }else if(grid[row][col-1] instanceof Enemy){
                numLives--;
            }else if(grid[row][col-1] instanceof Trophy){
                if(treasureCount==numTreasures){
                    win = true;
                }else if(col<grid.length-1){
                    move("d"); //move backwards
                }
            }
        } else if(direction.equals("d") && col<grid.length-1){
            if(grid[row][col+1] instanceof Treasure &&!(grid[row][col+1] instanceof Trophy)){
                treasureCount++;
            }else if (grid[row][col+1] instanceof Enemy){
                numLives--;
            }else if(grid[row][col+1] instanceof Trophy){
                if(treasureCount==numTreasures){
                    win = true;
                }else if(col>0){
                    System.out.println("HII");
                    move("a"); //move backwards
                }

            }
        }else if(direction.equals("w") && row>0){
            if(grid[row-1][col] instanceof Treasure && !(grid[row-1][col] instanceof Trophy)){
                treasureCount++;
            }else if(grid[row-1][col] instanceof Enemy){
                numLives--;
            }else if(grid[row-1][col] instanceof Trophy){
                if(treasureCount==numTreasures){
                    win = true;
                }else if(row<grid.length-1){
                    move("s"); //move backwards
                }

            }
        }else if(direction.equals("s") && row<grid.length-1){
            if(grid[row+1][col] instanceof Treasure && !(grid[row+1][col] instanceof Trophy)){
                treasureCount++;
            }else if(grid[row+1][col] instanceof Enemy){
                numLives--;
            }else if(grid[row+1][col] instanceof Trophy){
                if(treasureCount==numTreasures){
                    win = true;
                }else if(row>0){
                    move("w"); //move backwards
                }

            }
        }
    }
   
}



