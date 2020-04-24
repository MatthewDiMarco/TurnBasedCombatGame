package view;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.util.List;

/**
 * Draws a game character's stats.
 */
public class CharacterStatsPanel extends JPanel 
{
    // Stats
    private String characterName;
    private String characterHealth;
    private String characterGold;
    private String characterAttack;
    private String characterDefence;

    /**
     * Constructor.
     * @param stats
     */
    public CharacterStatsPanel(List<String> stats) 
    {
        super();
        showCharacterStats(stats);
    }

    /**
     * Draw the stats
     */
    @Override
    public void paint(Graphics g)
    {
        final int SPACING = 20;
        final int COL_OFFSET = 6;

        g.setFont(new Font("default", Font.BOLD, 12));

        // Col 1: Descriptors
        g.drawString("Name:     ", SPACING, SPACING);
        g.drawString("Health:   ", SPACING, SPACING * 2);
        g.drawString("Gold:     ", SPACING, SPACING * 3);
        g.drawString("Attack:   ", SPACING, SPACING * 4);
        g.drawString("Defence:  ", SPACING, SPACING * 5);

        // Col 2: Data
        g.drawString(characterName, SPACING * COL_OFFSET, SPACING);
        g.drawString(characterHealth, SPACING * COL_OFFSET, SPACING * 2);
        g.drawString(characterGold, SPACING * COL_OFFSET, SPACING * 3);
        g.drawString(characterAttack, SPACING * COL_OFFSET, SPACING * 4);
        g.drawString(characterDefence, SPACING * COL_OFFSET, SPACING * 5);
    }

    /**
     * Assign values to the character's stats.
     * Index Format of List:
     * 0 = name
     * 1 = health
     * 2 = gold
     * 3 = attack range
     * 4 = defence range
     * @param list A list of String character stats.
     */
    public void showCharacterStats(List<String> list) {
        characterName = list.get(0);
        characterHealth = list.get(1);
        characterGold = list.get(2);
        characterAttack = list.get(3);
        characterDefence = list.get(4);
    }
}