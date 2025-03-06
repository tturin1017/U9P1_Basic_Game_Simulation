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

    public void placeSprite(Sprite s){
        grid[s.getY()][s.getX()]=s;
    }

    public void placeSprite(Sprite s, String direction) {
        if(!isValid(s.getX(),s.getY())){
            if(s.getX()<0){
                s.move("s");
            }else if(s.getX()>size-1){
                s.move("w");
            }else if (s.getY()<0){
                s.move("d");
            }else if(s.getY()>size-1){
                s.move("a");
            }
        }
        if(direction.equals("w")){
            grid[s.getY()+1][s.getX()]=new Dot(s.getX(),s.getY()+1);
        }else if(direction.equals("a")){
            grid[s.getY()][s.getX()+1]=new Dot(s.getX()+1,s.getY());
        }else if (direction.equals("s")){
            grid[s.getY()-1][s.getX()]=new Dot(s.getX(),s.getY()-1);
        }else if(direction.equals("d")){
            grid[s.getY()][s.getX()-1]=new Dot(s.getX()-1,s.getY());
        }
        placeSprite(s);
    }


    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] instanceof Dot) {
                    System.out.print(". ");
                } else if (grid[i][j] instanceof Player) {
                    System.out.print("P ");
                } else if (grid[i][j] instanceof Enemy) {
                    System.out.print("E ");
                } else if (grid[i][j] instanceof Treasure) {
                    System.out.print("T ");
                }
            }
            System.out.println();
        }
    }

    public boolean isValid(int x, int y){
        return x>=0 && x < size && y>=0 && y < size;
    }

}