package model;

/**
 * [description]
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
     * @param inName The enemy species' name.
     */
    public SlimeEnemy()
    {
        super(SPECIES_NAME, GOLD, MAX_HEALTH, 
              MIN_ATTACK, MAX_ATTACK, MIN_DEF, MAX_DEF);
    }

    @Override
    public String toString()
    {
        return super.toString() + ", " +
            MIN_ATTACK + "-" + MAX_ATTACK + " attack, " +
            MIN_DEF + "-" + MAX_DEF + " defence, ";
    }

    /**
     * Define this species' special abilities here
     */
    @Override
    public int modifier(int damage)
    {
        return 0; //todo
    }
}