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

    // Abstract Methods
    protected abstract int modifier(Random generator, int damage);
    protected abstract void init();

    /**
     * Enemy factory. Constructs an instance of an enemy extending this class.
     * Returns null if the type does not exist.
     * @return The fully initialised enemy
     */
    public static EnemyCharacter makeEnemy(String enemyStr)
    {
        EnemyCharacter enemy = null;
        if (enemyStr.toUpperCase().equals("SLIME"))
        {   
            enemy = new SlimeEnemy(new CharacterInventory());
            enemy.init();
        }
        else if (enemyStr.toUpperCase().equals("GOBLIN"))
        {
            enemy = new GoblinEnemy(new CharacterInventory());
            enemy.init();
        }
        else if (enemyStr.toUpperCase().equals("OGRE"))
        {
            enemy = new OgreEnemy(new CharacterInventory());
            enemy.init();
        }
        else if (enemyStr.toUpperCase().equals("DRAGON"))
        {
            enemy = new DragonEnemy(new CharacterInventory());
            enemy.init();
        }

        return enemy;
    }
}