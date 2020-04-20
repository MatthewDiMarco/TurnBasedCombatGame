package model;

/**
 * [description]
 */
public abstract class EnemyCharacter extends GameCharacter
{
    private final int minAttack;
    private final int maxAttack;
    private final int minDefence;
    private final int maxDefence;

    /**
     * Constructor.
     * @param inName The species
     * @param minA Minimum Attack
     * @param maxA Maximum Attack
     * @param minD Minimum Defence
     * @param maxD Maximum Defence
     */
    public EnemyCharacter(String inName, int gold, int maxHealth, 
                          int minA, int maxA, int minD, int maxD)
    {
        super(inName, gold, maxHealth);
        if (minA < 0 || maxA < 0 || minD < 0 || maxD < 0)
        {
            throw new IllegalArgumentException(
                "Attck/Defence values must be positive"
            );
        }

        minAttack = minA;
        maxAttack = maxA;
        minDefence = minD;
        maxDefence = maxD;
    }

    @Override
    public int attack()
    {
        return 0;
    }

    @Override
    public int defend()
    {
        return 0;
    }

    @Override
    public void die()
    {  
        //todo
    }
}