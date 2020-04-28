package view;
import model.characters.CharacterUpdateObservable;
import model.characters.GameCharacter;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;

/**
 * Draws a game character's stats.
 */
public class CharacterStatsPanel extends JPanel implements CharacterUpdateObservable
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
    public CharacterStatsPanel(GameCharacter character) 
    {
        super();
        character.addUpdateObserver(this);
        showCharacterStats(character);
    }

    /**
     * Draw the stats
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

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
     * @param character The character to display 
     */
    public void showCharacterStats(GameCharacter character) {
        characterName = character.getName();
        characterHealth = character.getHealth() + " / " + character.getMaxHealth();
        characterGold = character.getGold() + " G";
        characterAttack = character.getAttackRange();
        characterDefence = character.getDefenceRange();
    }

    @Override
    public void updateCharacter(GameCharacter character)
    {
        showCharacterStats(character);
        this.repaint();
    }  
}