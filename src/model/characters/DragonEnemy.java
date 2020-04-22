package model.characters;
import java.util.*;

/**
 * Dragon enemy type.
 * Special abilities: 25% chance the damage inflicted will double, or 10%
 * chance the dragon will recover 10 health.
 */
public class DragonEnemy extends EnemyCharacter
{
    // Dragon Attributes
    public static final String SPECIES_NAME = "Dragon";
    public static final int MAX_HEALTH      = 100;
    public static final int GOLD            = 100;
    public static final int MIN_ATTACK      = 15;
    public static final int MAX_ATTACK      = 30;
    public static final int MIN_DEF         = 15;
    public static final int MAX_DEF         = 20;

    /**
     * Constructor.
     */
    public DragonEnemy()
    {
        super(GOLD, MAX_HEALTH, 
              MIN_ATTACK, MAX_ATTACK, 
              MIN_DEF, MAX_DEF);
    }

    /**
     * Dragon String representation
     * @return Dragon as a String
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
        // 25% chance of double damage
        if (randGenerator.nextInt(100) < 25)
        {
            damage *= 2;
        }

        // 10% chance of self-heal
        if (randGenerator.nextInt(100) < 10)
        {
            setHealth(currHealth + 10);
        }

        return damage;
    }
}