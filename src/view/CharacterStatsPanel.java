package view;
import model.characters.CharacterUpdateObservable;
import model.characters.GameCharacter;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Draws a game character's stats.
 */
public class CharacterStatsPanel extends ViewPanel implements CharacterUpdateObservable
{
    // Stats
    private JLabel characterName;
    private JLabel characterHealth;
    private JLabel characterGold;
    private JLabel characterAttack;
    private JLabel characterDefence;

    public CharacterStatsPanel(GameCharacter character) 
    {
        super(new FlowLayout(), "STATS", PADDING);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Labels
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        JPanel panel4 = new JPanel(new BorderLayout());
        JPanel panel5 = new JPanel(new BorderLayout());

        this.add(Box.createRigidArea(new Dimension(10, 10)));
        this.add(panel1);
        this.add(new JSeparator());
        this.add(panel2);
        this.add(new JSeparator());
        this.add(panel3);
        this.add(new JSeparator());
        this.add(panel4);
        this.add(new JSeparator());
        this.add(panel5);
        this.add(new JSeparator());
        this.add(Box.createRigidArea(new Dimension(10, 10)));

        panel1.add(characterName = new JLabel(), BorderLayout.SOUTH);
        panel2.add(characterHealth = new JLabel(), BorderLayout.SOUTH);
        panel3.add(characterGold = new JLabel(), BorderLayout.SOUTH);
        panel4.add(characterAttack = new JLabel(), BorderLayout.SOUTH);
        panel5.add(characterDefence = new JLabel(), BorderLayout.SOUTH);

        character.addUpdateObserver(this);
        showCharacterStats(character);
    }

    /**
     * Assign values to the character's stats.
     * @param character The character to display 
     */
    public void showCharacterStats(GameCharacter character) {
        characterName.setText(character.getName());
        characterHealth.setText(character.getHealth() + " / " + character.getMaxHealth() + " Health");
        characterGold.setText(character.getGold() + " Gold");
        characterAttack.setText(character.getAttackRange() + " Attack");
        characterDefence.setText(character.getDefenceRange() + " Defence");
    }

    @Override
    public void updateCharacter(GameCharacter character)
    {
        showCharacterStats(character);
    }  
}