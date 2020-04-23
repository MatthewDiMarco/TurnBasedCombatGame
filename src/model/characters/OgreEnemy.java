package model.characters;
import java.util.*;

/**
 * Ogre enemy type.
 * Special abilities: 20% Chance it will attack twice in a row for a given attack.
 */
public class OgreEnemy extends EnemyCharacter
{
    // Ogre Attributes
    public static final String SPECIES_NAME = "Ogre";
    public static final int MAX_HEALTH      = 40;
    public static final int GOLD            = 40;
    public static final int MIN_ATTACK      = 5;
    public static final int MAX_ATTACK      = 10;
    public static final int MIN_DEF         = 6;
    public static final int MAX_DEF         = 12;

    /**
     * Constructor.
     */
    public OgreEnemy()
    {
        super(SPECIES_NAME, GOLD, MAX_HEALTH, 
              MIN_ATTACK, MAX_ATTACK, 
              MIN_DEF, MAX_DEF);
    }

    /**
     * Ogre String representation
     * @return Ogre as a String
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
        // 20% to attack twice in a row
        if (randGenerator.nextInt(100) < 20)
        {
            damage += randGenerator.nextInt(maxAttack + 1 - minAttack) + minAttack;
        }

        return damage;
    }
}