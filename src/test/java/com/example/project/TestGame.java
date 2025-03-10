package com.example.project;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGame {
    
    private Game game;
    private Player player;
    private Grid grid;
    private Trophy trophy;
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        int size = 10;
        game = new Game(size);
        player = game.getPlayer();
        grid = game.getGrid();
        trophy = new Trophy(9, 9);
        enemy = new Enemy(5, 5);

        // Place objects on the grid
        grid.placeSprite(trophy);
        grid.placeSprite(enemy);
    }

    @Test
    void testPlayerWins() {
        // Simulate collecting all treasures
        player.interact(10, "d", 0, trophy); // Player has enough treasures (0 required)

        // Verify the player wins
        assertTrue(player.getWin(), "Player should win after interacting with trophy");
    }

    @Test
    void testPlayerLoses() {
        // Simulate losing all lives
        player.interact(10, "d", 0, enemy); // Lose one life
        player.interact(10, "d", 0, enemy); // Lose another life

        // Verify the player loses
        assertEquals(0, player.getLives(), "Player should have 0 lives after losing all lives");
    }

    @Test
    void testPlayerWinsAfterCollectingAllTreasures() {
        // Simulate collecting all treasures
        Treasure treasure1 = new Treasure(2, 2);
        Treasure treasure2 = new Treasure(3, 3);
        grid.placeSprite(treasure1);
        grid.placeSprite(treasure2);

        // Collect the first treasure
        player.interact(10, "d", 2, treasure1);
        assertEquals(1, player.getTreasureCount(), "Player should have 1 treasure after collecting the first treasure");

        // Collect the second treasure
        player.interact(10, "d", 2, treasure2);
        assertEquals(2, player.getTreasureCount(), "Player should have 2 treasures after collecting the second treasure");

        // Interact with the trophy
        player.interact(10, "d", 2, trophy);

        // Verify the player wins
        assertTrue(player.getWin(), "Player should win after collecting all treasures and interacting with trophy");
    }

    @Test
    void testPlayerLosesAfterLosingAllLives() {
        // Simulate losing all lives
        player.interact(10, "d", 0, enemy); // Lose one life
        assertEquals(1, player.getLives(), "Player should have 1 life after losing one life");

        player.interact(10, "d", 0, enemy); // Lose another life
        assertEquals(0, player.getLives(), "Player should have 0 lives after losing all lives");

        // Verify the player loses
        assertTrue(player.getLives() == 0, "Player should lose when lives reach 0");
    }
}
