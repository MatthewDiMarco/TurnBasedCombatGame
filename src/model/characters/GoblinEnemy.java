package model.characters;
import model.items.CharacterInventory;
import model.items.Dice;

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
     * @param inInventory Inventory containing attack/defence usage.
     */
    public GoblinEnemy(CharacterInventory inInventory)
    {
        super(SPECIES_NAME, GOLD, MAX_HEALTH, 
              MIN_ATTACK, MAX_ATTACK, MIN_DEF, MAX_DEF, 
              inInventory);
    }

    /**
     * Define this species' special abilities here.
     * Goblin Special abilities: 50% chance that it's attacks will impose an 
     * extra 3 damage in addition to the base damage.
     */
    @Override
    protected int modifier(int damage, Dice dice)
    {
        final int ADD = 3;

        // 50% for an extra +3 damage
        if (dice.roll(1, 100) < 50)
        {
            super.notifyActionObservers(SPECIES_NAME + 
                " USED ABILITY (+" + ADD + " DAMAGE)");
            damage += ADD;
        }

        return damage;
    }
}