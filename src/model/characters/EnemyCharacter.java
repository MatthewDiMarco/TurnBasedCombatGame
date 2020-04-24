package model.characters;
import model.items.CharacterInventory;
import java.util.*;

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
                          CharacterInventory inInventory)
    {
        super(inSpecies, inGold, inMaxHealth, inInventory);
    }

    /**
     * Produces a number denoting the amount to subtract from an 
     * opponent's health. The effect might be amplified or dampened by the
     * enemies internal modifier method which applies special abilities
     * unique to the species.
     * @return The total damage
     */
    @Override
    public int attack()
    {
        int dmg = super.attack();
        modifier(generator, dmg); // Unique special abilities
        return dmg;
    }
    
    /**
     * This is where the enemy's special abilities will be implemented.
     */
    protected abstract int modifier(Random generator, int damage);
}