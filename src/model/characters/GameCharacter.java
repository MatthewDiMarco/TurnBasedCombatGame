package model.characters;
import model.items.CharacterInventory;
import java.util.*;

/**
 * Generic representation of the 'Game Character' entity, which could be  
 * A player or a number of unique enimies.
 * This class contains everything common among Character enitites (gold, 
 * health etc) and shares their state and functionality to promote reuse.
 */
public class GameCharacter
{
    // Character Attributes
    private String name;
    private int gold;
    protected CharacterInventory inventory;
    protected int currHealth;
    protected final int maxHealth;
    protected Random generator;
    private boolean dead;

    /**
     * Constructor.
     * @param inName The character's name.
     * @param inGold The character's starting gold.
     * @param inMaxHealth The character's health cap.
     * @param inInventory The character's items.
     */
    public GameCharacter(String inName, int inGold, int inMaxHealth,
                         CharacterInventory inInventory)
    {
        setName(inName);
        setGold(inGold);
        if (inMaxHealth <= 0)
        {
            throw new IllegalArgumentException(
                "Character cannot have negative health"
            );
        }
    
        maxHealth = inMaxHealth;
        currHealth = maxHealth;

        if (inInventory == null)
        {
            throw new IllegalArgumentException(
                "Inventory must not be null"
            );
        }

        inventory = inInventory;
        generator = new Random();

        dead = false;
    }

    /**
     * Name accessor.
     * @return The character's name
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
     * Maximum Health accessor.
     * @return The character's health capacity
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * Gold accessor.
     * @return The character's current coinage.
     */
    public int getGold()
    {
        return gold; 
    }

    /**
     * Inventory accessor.
     * @return The character's current items.
     */
    public CharacterInventory getInventory()
    {
        return inventory;
    }

    /**
     * Dead/Alive Status accessor.
     * @return True of character is dead.
     */
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
        return name + ", " + 
               currHealth + "/" + maxHealth + " Health, " + 
               inventory.getCurrAttackRange() + " Attack, " +
               inventory.getCurrDefenceRange() + " Defence, " +
               gold + "G";

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

    /**
     * Health mutator. Used by defend() and attack() to update Character's 
     * Health.
     * @param inHealth The character's new health
     */
    protected void setHealth(int inHealth)
    {
        if (inHealth <= 0)
        {
            currHealth = 0;
            dead = true;
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
     * Produces a number denoting the amount to subtract from an 
     * opponent's health.
     * @return The total damage
     */
    public int attack()
    {
        int dmg = 0;

        int effect = inventory.usePotion(generator);
        if (effect > 0) // It's a healing potion
        {
            this.setHealth(currHealth + effect); 
        }
        else if (effect < 0) // It's a damage potion
        {
            dmg = Math.abs(effect);
        }
        else // No potion equiped. Use weapon (including enchantments) instead.
        {
            dmg = inventory.useWeapon(generator);
        }

        return dmg;
    }

    /**
     * Absorbs an opponent's incomming attack and reduces the Character's
     * health accordingly.
     * @param dmg The damage to be absorbed
     */
    public void defend(int dmg)
    {
        int def = inventory.useArmour(generator);
        this.setHealth(currHealth - Math.max(0, dmg - def));
    }
}