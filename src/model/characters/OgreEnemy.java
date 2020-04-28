package model.characters;
import model.items.CharacterInventory;
import model.items.Dice;

/**
 * Ogre enemy type.
 * Special abilities: 20% Chance it will attack twice in a row for a given 
 * attack.
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
     * @param inInventory Inventory containing attack/defence usage.
     */
    public OgreEnemy(CharacterInventory inInventory)
    {
        super(SPECIES_NAME, GOLD, MAX_HEALTH, 
              MIN_ATTACK, MAX_ATTACK, MIN_DEF, MAX_DEF, 
              inInventory);
    }

    /**
     * Define this species' special abilities here.
     * Special abilities: 20% Chance it will attack twice in a row for a given 
     * attack.
     */
    @Override
    protected int modifier(int damage, Dice dice)
    {
        // 20% to attack twice in a row
        if (dice.roll(1, 100) < 20)
        {
            damage += super.attack(dice);
        }

        return damage;
    }
}