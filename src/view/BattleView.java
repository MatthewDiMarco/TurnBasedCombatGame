package view;
import controller.BattleController;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.*;

public class BattleView extends JFrame
{
    // Constants
    public static final int WIDTH = 320;
    public static final int HEIGHT = 120;

    // Stats
    private String enemySpecies;
    private String enemyHealth;
    private String enemyGold;
    private String enemyAttack;
    private String enemyDefence;

    // Controllers
    BattleController battleCon;
    /**
     * 
     * @param inBattleController
     */
    public BattleView(BattleController inBattleController)
    {
        super("Battle");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // Controllers
        battleCon = inBattleController; 

        // Fit winodow
        pack();

        showEnemyStats(battleCon.getEnemyStats());
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
        g.drawString(enemySpecies, SPACING * COL_OFFSET, SPACING);
        g.drawString(enemyHealth, SPACING * COL_OFFSET, SPACING * 2);
        g.drawString(enemyGold, SPACING * COL_OFFSET, SPACING * 3);
        g.drawString(enemyAttack, SPACING * COL_OFFSET, SPACING * 4);
        g.drawString(enemyDefence, SPACING * COL_OFFSET, SPACING * 5);
    }

    /**
     * Assign values to the enemy's stats.
     * @param stats A list of String enemy stats.
     */
    private void showEnemyStats(List<String> stats)
    {
        enemySpecies = stats.get(0); 
        enemyHealth = stats.get(1);
        enemyGold = stats.get(2);
        enemyAttack = stats.get(3);
        enemyDefence = stats.get(4);
    }
}