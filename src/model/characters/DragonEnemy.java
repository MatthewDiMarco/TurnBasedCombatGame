package model.characters;
import model.items.CharacterInventory;
import model.items.Dice;

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
     * @param inInventory Inventory containing attack/defence usage.
     */
    public DragonEnemy(CharacterInventory inInventory)
    {
        super(SPECIES_NAME, GOLD, MAX_HEALTH, 
              MIN_ATTACK, MAX_ATTACK, MIN_DEF, MAX_DEF, 
              inInventory);
    }

    /**
     * Define this species' special abilities here
     * Special abilities: 25% chance the damage inflicted will double, or 10%
     * chance the dragon will recover 10 health.
     */
    @Override
    protected int modifier(int damage, Dice dice)
    {
        int result = dice.roll(1, 100);

        // 25% chance of (only) double damage
        if (result < 25)
        {
            damage *= 2;
        }
        else if (result < 10) // 10% chance of (only) self-heal
        {
            this.setHealth(currHealth + 10);
        }

        return damage;
    }
}