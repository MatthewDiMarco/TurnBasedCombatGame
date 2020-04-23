package model.characters;
import java.util.*;

/**
 * Goblin enemy type.
 * Special abilities: 50% chance that it's attacks will impose an extra 3 
 * damage in addition to the base damage.
 */
public class GoblinEnemy extends EnemyCharacter
{
    // Goblin Attributes
    public static final String SPECIES_NAME = "Goblin";
    public static final int MAX_HEALTH      = 30;
    public static final int GOLD            = 20;
    public static final int MIN_ATTACK      = 3;
    public static final int MAX_ATTACK      = 8;
    public static final int MIN_DEF         = 4;
    public static final int MAX_DEF         = 8;

    /**
     * Constructor.
     */
    public GoblinEnemy()
    {
        super(SPECIES_NAME, GOLD, MAX_HEALTH, 
              MIN_ATTACK, MAX_ATTACK, 
              MIN_DEF, MAX_DEF);
    }

    /**
     * Goblin String representation
     * @return Goblin as a String
     */
    @Override
    public String toString()
    {
        return SPECIES_NAME + ", " + super.toString();
    }

    /**
     * Define this species' special abilities here
     */
    @Override
    protected int modifier(Random randGenerator, int damage)
    {
        // 50% for an extra +3 damage
        if (randGenerator.nextInt(100) < 50)
        {
            damage += 3;
        }

        return damage;
    }
}