package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Grid getGrid(){return grid;}
    public Player getPlayer(){return player;}
    public Enemy[] getEnemies(){return enemies;}
    public Treasure[] getTreasures(){return treasures;}
    public int getSize(){return size;}

    public Game(int size){
        this.size = size;
        this.initialize();
        this.play();
    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equals("q") && player.getLives()!=0 && !player.getWin()){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen

            grid.display();
            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Treasure Collected: "+player.getTreasureCount());
            System.out.println("Lives remaining: "+ player.getLives());
            System.out.print("Enter a direction (w,a,s,d) or 'q' to exit: ");
            input = scanner.nextLine().trim().toLowerCase();

            
            player.interact(grid.getGrid(),input, treasures.length);
            player.move(input);

            grid.placeSprite(player, input);
        }
        clearScreen();
        if(player.getLives()==0){
            grid.gameover();
            System.out.println("    GAMEOVER");
        }else if(player.getWin()){
            grid.win();
            System.out.println("    YOU WIN");
        }
    }

    public void initialize(){
        player = new Player (0,0);
        trophy = new Trophy(0,9);
        grid = new Grid(size);

        grid.placeSprite(player);
        grid.placeSprite(trophy);

        enemies = new Enemy[3];
        enemies[0] = new Enemy(5, 5);
        enemies[1] = new Enemy(7, 2);
        enemies[2] = new Enemy(3, 8);
        for (Enemy enemy : enemies) {
            grid.placeSprite(enemy);
        }

        treasures = new Treasure[2];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(8, 8);
        for (Treasure treasure : treasures) {
            grid.placeSprite(treasure);
        }
    }

    public int generateCoord(){
        return (int) (Math.random()*(size-0)+0); //generate 0-size (exclusive)
    }

    public static void main(String[] args) {
        Game g = new Game(10);
        
    }
}