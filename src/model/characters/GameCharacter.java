package model.characters;
import java.util.*;

/**
 * Generic representation of the 'Game Character' entity, which could be  
 * A player or a number of unique enimies.
 * This class contains everything common among Character enitites (gold, 
 * health etc) and shares their state and functionality to promote reuse.
 */
public abstract class GameCharacter
{
    // Character Attributes
    private int gold;
    protected int currHealth;
    private final int maxHealth;
    private boolean dead;

    /**
     * Constructor.
     * @param inGold The character's starting gold.
     * @param inMaxHealth The character's health cap.
     */
    public GameCharacter(int inGold, int inMaxHealth)
    {
        setGold(inGold);
        if (inMaxHealth <= 0)
        {
            throw new IllegalArgumentException(
                "cannot have negative health"
            );
        }
    
        maxHealth = inMaxHealth;
        currHealth = maxHealth;
        dead = false;
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
     * Health mutator.
     * @param inHealth The character's new health
     */
    public void setHealth(int inHealth)
    {
        if (inHealth <= 0)
        {
            currHealth = 0;
            this.die();
        }
        else if (inHealth > maxHealth) // Stop health exceeding max
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
                "cannot own negative gold"
            );
        }
        else
        {
            gold = inGold;
        }
    }

    public boolean isDead()
    {
        return dead; 
    }

    /**
     * A simple string representation of the character.
     * @return A String containing health and gold.
     */
    public String toString()
    {
        return currHealth + "/" + 
               maxHealth + " Health, " + 
               gold + "G";
    }

    protected void die()
    {
        dead = true;
    }

    public abstract int attack();

    public abstract void defend(int dmg);
}