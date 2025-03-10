package com.example.project;

public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) {
        this.size = size;
        grid = new Sprite[size][size];

        for(int i =0; i<grid.length;i++){
            for(int j=0; j<grid[i].length;j++){
                grid[i][j]=new Dot(i,j);
            }
        }
    }

    public Sprite[][] getGrid(){return grid;}

    public void placeSprite(Sprite s){
        grid[size-1-s.getY()][s.getX()]=s;
    }

    public void placeSprite(Sprite s, String direction) {
        int x = s.getX();
        int y = s.getY();
        int row = (size-1)-s.getY();
        int col = s.getX();

        if(!isValid(x,y)){
            if(col<0){
                s.move("d");
            }else if(col>size-1){
                s.move("a");
            }else if (row<0){
                s.move("s");
            }else if(row>size-1){
                s.move("w");
            }
        }else{
            if(direction.equals("w")){
                grid[row+1][col]=new Dot(x,y-1);
            }else if(direction.equals("a")){
                grid[row][col+1]=new Dot(x+1,y);
            }else if (direction.equals("s")){
                grid[row-1][col]=new Dot(x,y+1);
            }else if(direction.equals("d")){
                grid[row][col-1]=new Dot(x-1,y);
            }
        }
   
        placeSprite(s);
    }


    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] instanceof Dot) {
                    System.out.print("⬜");
                } else if (grid[i][j] instanceof Player) {
                    System.out.print("🦄");
                } else if (grid[i][j] instanceof Enemy) {
                    System.out.print("🦂");
                } else if (grid[i][j] instanceof Trophy){
                    System.out.print("🏆");
                }else if (grid[i][j] instanceof Treasure) {
                    System.out.print("🌈");
                } 
            }
            System.out.println();
        }
    }
    
    public void gameover(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(!(grid[i][j] instanceof Player)){
                    System.out.print("💀");
                }else{
                    System.out.print("🦄");
                }
            }
            System.out.println();
        }
    }

    public void win(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(!(grid[i][j] instanceof Player)){
                    System.out.print("🌈");
                }else{
                    System.out.print("🦄");
                }
            }
            System.out.println();
        }
    }

    public boolean isValid(int x, int y){
        return x>=0 && x < size && y>=0 && y < size;
    }

}