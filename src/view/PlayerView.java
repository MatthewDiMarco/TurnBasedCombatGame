package view;
import controller.PlayerController;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.*;

public class PlayerView extends JFrame
{
    // Constants
    public static final int WIDTH = 320;
    public static final int HEIGHT = 120;

    // Stats
    private String playerName;
    private String playerHealth;
    private String playerGold;
    private String playerAttack;
    private String playerDefence;

    // Controllers
    PlayerController playerCon;

    /**
     * 
     * @param inPlayerController
     */
    public PlayerView(PlayerController inPlayerController)
    {
        super("Player Stats");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // Controllers
        playerCon = inPlayerController; 

        // Fit winodow
        pack();

        showPlayerStats(playerCon.getPlayerStats());
    }

    /**
     * Draw the stats
     */
    public void paint(Graphics g)
    {
        final int SPACING = 20;
        final int COL_OFFSET = 6;

        // Col 1: Descriptors
        g.drawString("Name:     ", SPACING, SPACING);
        g.drawString("Health:   ", SPACING, SPACING * 2);
        g.drawString("Gold:     ", SPACING, SPACING * 3);
        g.drawString("Attack:   ", SPACING, SPACING * 4);
        g.drawString("Defence:  ", SPACING, SPACING * 5);

        // Col 2: Data
        g.drawString(playerName, SPACING * COL_OFFSET, SPACING);
        g.drawString(playerHealth, SPACING * COL_OFFSET, SPACING * 2);
        g.drawString(playerGold, SPACING * COL_OFFSET, SPACING * 3);
        g.drawString(playerAttack, SPACING * COL_OFFSET, SPACING * 4);
        g.drawString(playerDefence, SPACING * COL_OFFSET, SPACING * 5);
    }

    /**
     * Assign values to the player's stats.
     * @param stats A list of String player stats.
     */
    private void showPlayerStats(List<String> stats)
    {
        playerName = stats.get(0);
        playerHealth = stats.get(1);
        playerGold = stats.get(2);
        playerAttack = stats.get(3);
        playerDefence = stats.get(4);
    }
}