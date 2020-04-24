package model.characters;
import model.items.CharacterInventory;
import java.util.*;

/**
 * Slime enemy type.
 * Special abilities: 20% chance that when it's attack() method is called, there
 * will be no damage (i.e. zero effect on opponent).
 */
public class SlimeEnemy extends EnemyCharacter
{
    // Slime Attributes
    public static final String SPECIES_NAME = "Slime";
    public static final int MAX_HEALTH      = 10;
    public static final int GOLD            = 10;
    public static final int MIN_ATTACK      = 3;
    public static final int MAX_ATTACK      = 5;
    public static final int MIN_DEF         = 0;
    public static final int MAX_DEF         = 2;
    
    /**
     * Constructor.
     * @param inInventory Inventory containing attack/defence usage.
     */
    public SlimeEnemy(CharacterInventory inInventory)
    {
        super(SPECIES_NAME, GOLD, MAX_HEALTH, inInventory);
    }

    /**
     * Define this species' special abilities here.
     * Special abilities: 20% chance that when it's attack() method is called, 
     * there will be no damage (i.e. zero effect on opponent).
     */
    @Override
    protected int modifier(Random randGenerator, int damage)
    {
        // 20% chance attack will have no damage
        if (randGenerator.nextInt(100) < 20)
        {
            damage = 0;
        }

        return damage;
    }
}