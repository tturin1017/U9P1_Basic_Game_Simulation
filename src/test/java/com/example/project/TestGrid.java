package com.example.project;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGrid {
    
    private Grid grid;
    private Player player;
    private Enemy enemy;
    private Treasure treasure;
    private Trophy trophy;

    @BeforeEach
    public void setUp() {
        int size = 10;
        grid = new Grid(size);
        player = new Player(0, 0);
        enemy = new Enemy(5, 5);
        enemy = new Enemy(7,8);
        treasure = new Treasure(2, 2);
        treasure = new Treasure(7,1);
        trophy = new Trophy(9, 9);

        // Row 0: [ ][ ][ ][ ][ ][ ][ ][ ][ ][W]
        // Row 1: [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        // Row 2: [ ][T][ ][ ][ ][ ][ ][E][ ][ ]
        // Row 3: [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        // Row 4: [ ][ ][ ][ ][ ][E][ ][ ][ ][ ]
        // Row 5: [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        // Row 6: [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        // Row 7: [ ][ ][T][ ][ ][ ][ ][ ][ ][ ]
        // Row 8: [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        // Row 9: [P][ ][ ][ ][ ][ ][ ][ ][ ][ ]

        // Place objects on the grid
        grid.placeSprite(player);
        grid.placeSprite(enemy);
        grid.placeSprite(treasure);
        grid.placeSprite(trophy);
    }

    @Test
    public void testInitialGridState() {
        assertEquals(player, grid.getGrid()[9][0], "Player should be at [9][0]");
        assertEquals(enemy, grid.getGrid()[4][5], "Enemy should be at [4][5]");
        assertEquals(treasure, grid.getGrid()[7][2], "Treasure should be at [7][2]");
        assertEquals(trophy, grid.getGrid()[0][9], "Trophy should be at [0][9]");
    }

    @Test
    public void testPlayerMoveUpdatesGrid() {

      
        player.move("d"); //move right
        grid.placeSprite(player, "d");

        assertEquals(player, grid.getGrid()[9][1], "Player should be at [9][1] after moving right");

        // Verify the previous position is now a Dot
        assertTrue(grid.getGrid()[9][0] instanceof Dot, "Previous position [9][0] should be a Dot");
    }

    @Test
    public void testPlayerInteractWithTreasureUpdatesGrid() {
        // Move the player to the treasure's position

        player.move("d");//[9][1]
        grid.placeSprite(player, "d");
    
        player.move("d");//[9][2]
        grid.placeSprite(player, "d");

        player.move("w");//[8][2]
        grid.placeSprite(player, "w");


        player.interact(10, "w", 1, treasure);
        player.move("w");//[7][2]
        grid.placeSprite(player, "w");

        player.move("d");//[7][3]
        grid.placeSprite(player, "d");

        
        // Verify the treasure is collected and removed from the grid
        assertTrue(grid.getGrid()[7][2] instanceof Dot, "Treasure should be replaced with a Dot after collection");
    }

    @Test
    void testPlayerInteractWithEnemyUpdatesGrid() {
        // Move the player to the enemy's position [4][5]
        for(int i=0;i<5;i++){
            player.move("w"); //[9-5][0]
            grid.placeSprite(player, "w");
        }

        for(int i=0;i<4;i++){
            player.move("d");//[5][4]
            grid.placeSprite(player, "d");
        }

         // Interact with the enemy
        player.interact(10, "d", 1, enemy);
        player.move("d");//[4][5]
        grid.placeSprite(player, "d");

        // Verify the player loses a life and the enemy remains
        assertEquals(1, player.getLives(), "Player should lose a life after interacting with enemy");
        assertEquals(player, grid.getGrid()[4][5], "Player should be at [4][5]");


    }

    @Test
    void testPlayerInteractWithTrophyUpdatesGrid() {
        // Move the player to the trophy's position
        player.move("w");
        player.move("w");
        player.move("w");
        player.move("w");
        player.move("a");
        player.move("a");
        player.move("a");
        player.move("a");
        player.move("a");

        player.interact(10, "a", 0, trophy); 
        grid.placeSprite(player, "a");



    
        assertTrue(player.getWin(), "Player should win after interacting with trophy");
        assertEquals(player, grid.getGrid()[0][9], "Player should be  at [0][9]");
    }
}
