package model.characters;
import model.items.CharacterInventory;
import model.items.Dice;

/**
 * Generic representation of the 'Enemy Character' entity. Requires a child to
 * specify the enemy's special abilities, described in the modifier() method.
 */
public abstract class EnemyCharacter extends GameCharacter
{
    /**
     * Constructor.
     * @param inName The enemies' species.
     * @param inGold The enemies' starting gold.
     * @param inMaxHealth The enemies' health cap.
     * @param inInventory The enemies' items.
     */
    public EnemyCharacter(String inSpecies, int inGold, int inMaxHealth,
                          int inMinA, int inMaxA, int inMinD, int inMaxD,
                          CharacterInventory inInventory)
    {
        super(inSpecies, inGold, inMaxHealth, 
              inMinA, inMaxA, inMinD, inMaxD, 
              inInventory);
    }

    /**
     * Produces a number denoting the amount to subtract from an 
     * opponent's health. The effect might be amplified or dampened by the
     * enemies internal modifier method which applies special abilities
     * unique to the species.
     * @param dice Random generator
     * @return The total damage
     */
    @Override
    public int attack(Dice dice)
    {
        int dmg = super.attack(dice);
        modifier(dmg, dice); // Unique special abilities
        return dmg;
    }

    // Abstract Methods
    protected abstract int modifier(int dmg, Dice dice);
}