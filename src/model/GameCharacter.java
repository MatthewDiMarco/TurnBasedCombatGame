package model;

/**
 * [description]
 */
public abstract class GameCharacter
{
    private String name;
    private int gold;
    private int currHealth;
    private final int maxHealth;

    /**
     * Constructor.
     * @param inName The character's name.
     */
    public GameCharacter(String inName, int inGold, int inMaxHealth)
    {
        setName(inName);
        setGold(inGold);
        if (inMaxHealth <= 0)
        {
            throw new IllegalArgumentException(
                name + " cannot have negative health"
            );
        }
        
        maxHealth = inMaxHealth;
        currHealth = maxHealth;
    }

    /**
     * Name accessor.
     * @return The character's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Health accessor.
     * @return The character's current health.
     */
    public int getHealth()
    {
        return currHealth;
    }

    /**
     * A simple string representation of the character.
     * @return A String containing name, health and gold.
     */
    public String toString()
    {
        return name + ", " + 
               currHealth + "/" + 
               maxHealth + " Health, " + 
               gold + " G";
    }

    /**
     * Name mutator.
     * @param inName The character's new name
     */
    public void setName(String inName)
    {
        if (inName.isEmpty())
        {
            throw new IllegalArgumentException(
                "Character must have a name"
            );
        }
        
        name = inName;
    }

    /**
     * Health mutator.
     * @param inHealth The character's new health
     */
    public void setHealth(int inHealth)
    {
        if (inHealth <= 0)
        {
            this.die();
        }
        else if (inHealth > maxHealth)
        {
            currHealth = maxHealth;
        }
        else
        {
            currHealth = inHealth;
        }
    }

    /**
     * Gold mutator.
     * @param inGold the Character's new gold.
     */
    public void setGold(int inGold)
    {
        if (inGold < 0)
        {
            throw new IllegalArgumentException(
                name + " cannot own negative gold"
            );
        }
        else
        {
            gold = inGold;
        }
    }

    public abstract int attack();

    public abstract int defend();

    public abstract int modifier(int value);

    public abstract void die();
}