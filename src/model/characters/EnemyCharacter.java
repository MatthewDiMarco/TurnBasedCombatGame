package model.characters;
import java.util.*;

/**
 * Generic representation of the 'Enemy Character' entity. Requires a child to
 * specify the enemy's base attributes and special abilities, described in the 
 * modifier() method.
 */
public abstract class EnemyCharacter extends GameCharacter
{
    protected final int minAttack;
    protected final int maxAttack;
    protected final int minDefence;
    protected final int maxDefence;
    private Random generator;

    /**
     * Constructor.
     * @param minA Minimum Attack
     * @param maxA Maximum Attack
     * @param minD Minimum Defence
     * @param maxD Maximum Defence
     */
    public EnemyCharacter(String inSpecies, int gold, int maxHealth, 
                          int minA, int maxA, int minD, int maxD)
    {
        super(inSpecies, gold, maxHealth);
        if (minA < 0 || maxA < 0 || minD < 0 || maxD < 0)
        {
            throw new IllegalArgumentException(
                "Attck/Defence values must be positive"
            );
        }

        if (maxA < minA || maxD < minD)
        {
            throw new IllegalArgumentException(
                "Max effect should be >= Min effect"
            );
        }

        minAttack = minA;
        maxAttack = maxA;
        minDefence = minD;
        maxDefence = maxD;
        generator = new Random();
    }

    public String getAttackRange()
    {
        return minAttack + " - " + maxAttack; 
    }

    public String getDefenceRange()
    {
        return minDefence + " - " + maxDefence; 
    }

    /**
     * Represent species as a string
     * @return Attributes as a String
     */
    @Override
    public String toString()
    {
        return super.toString() + ", " +
            minAttack + "-" + maxAttack + " attack, " +
            minDefence + "-" + maxDefence + " defence";
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
        int dmg = modifier(
            generator,
            generator.nextInt(maxAttack + 1 - minAttack) + minAttack
        );

        return dmg;
    }

    /**
     * Absorbs an opponent's incomming attack and reduces the enemies'
     * health accordingly.
     * @param dmg The damage to be absorbed
     */
    @Override
    public void defend(int dmg)
    {
        int def = generator.nextInt(maxDefence + 1 - minDefence) + minDefence;
        this.setHealth(currHealth - Math.max(0, dmg - def));
    }

    /**
     * todo
     */
    @Override
    protected void die()
    {  
        //todo
        super.die();
        System.out.println("THE ENEMY WAS SLAIN"); //temp
    }

    protected abstract int modifier(Random generator, int damage);
}