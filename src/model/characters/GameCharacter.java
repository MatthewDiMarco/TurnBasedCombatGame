package model.characters;
import model.items.CharacterInventory;
import model.items.DamageItem;
import model.items.DamagePotion;
import model.items.DefenceItem;
import model.items.Dice;
import model.items.InventoryException;

import java.util.*;

/**
 * Generic representation of the 'Game Character' entity, which could be  
 * A player or a number of unique enimies.
 * This class contains everything common among Character enitites (gold, 
 * health etc) and shares their state and functionality to promote reuse.
 */
public class GameCharacter
{
    // Fields
    private String name;
    private int gold;
    private int baseMinDamage;
    private int baseMaxDamage;
    private int baseMinDefence;
    private int baseMaxDefence;
    private boolean fighting;

    protected CharacterInventory inventory;
    protected int currHealth;
    protected final int maxHealth;

    // Observers
    private List<CharacterUpdateObservable> updateObservers;
    private List<CharacterActionObservable> actionObservers;
    private List<CharacterDeathObservable> deathObservers;

    /**
     * Constructor.
     * @param inName Character name
     * @param inGold Character starting gold
     * @param inMaxHealth Character health capacity
     * @param inMinA Minimum attack
     * @param inMaxA Maximum attack
     * @param inMinD Minimum defence
     * @param inMaxD Maximum defence
     * @param inInventory Inventory of item's
     */
    public GameCharacter(String inName, int inGold, int inMaxHealth,
                         int inMinA, int inMaxA, int inMinD, int inMaxD,
                         CharacterInventory inInventory)
    {
        // Init observer lists
        updateObservers = new ArrayList<CharacterUpdateObservable>();
        actionObservers = new ArrayList<CharacterActionObservable>();
        deathObservers = new ArrayList<CharacterDeathObservable>();

        // Validate ranges
        if (inName.isEmpty())
        {
            throw new IllegalArgumentException(
                "Character must have a name"
            );
        }
        else if (inMaxHealth <= 0)
        {
            throw new IllegalArgumentException(
                "Character cannot have negative health"
            );
        }
        else if (inMinA < 0 || inMaxA < 0 || inMinD < 0 || inMaxD < 0)
        {
            throw new IllegalArgumentException(
                "Attack and Defence must be >= 0"
            );
        }
        else if (inMinA > inMaxA || inMinD > inMaxD)
        {
            throw new IllegalArgumentException(
                "Attack and Defence must be >= 0"
            );
        }
        else if (inGold < 0)
        {
            throw new IllegalArgumentException(
                "Gold must be >= 0"
            );
        }

        // Set everything
        name = inName;
        gold = inGold;
        baseMinDamage = inMinA;
        baseMaxDamage = inMaxA;
        baseMinDefence = inMinD;
        baseMaxDefence = inMaxD;
        maxHealth = inMaxHealth;
        setHealth(maxHealth);
        inventory = inInventory;
        fighting = false;
    }

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return currHealth;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getGold()
    {
        return gold; 
    }

    public CharacterInventory getInventory()
    {
        return inventory;
    }

    public String getAttackRange()
    {
        int min = baseMinDamage;
        int max = baseMaxDamage;
        if (inventory.getWeapon() != null)
        {
            min += inventory.getWeapon().getMin();
            max += inventory.getWeapon().getMax();
        }

        return min + " - " + max;
    }

    public String getDefenceRange()
    {
        int min = baseMinDefence;
        int max = baseMaxDefence;
        if (inventory.getArmour() != null)
        {
            min += inventory.getArmour().getMin();
            max += inventory.getArmour().getMax();
        }

        return min + " - " + max;
    }

    public boolean isFighting()
    {
        return fighting;
    }

    public void setWeapon(DamageItem inWeapon) throws CharacterException
    {
        try
        {
            inventory.setWeapon(inWeapon);
            this.notifyUpdateObservers();
        }
        catch (InventoryException e)
        {
            throw new CharacterException(e.getMessage());
        }
    }

    public void setArmour(DefenceItem inArmour) throws CharacterException
    {
        try
        {
            inventory.setArmour(inArmour);
            this.notifyUpdateObservers();
        }
        catch (InventoryException e)
        {
            throw new CharacterException(e.getMessage());
        }
    }

    public void setName(String inName) throws CharacterException
    {
        if (inName.isEmpty())
        {
            throw new CharacterException("Character must have a name");
        }
        
        name = inName;
        this.notifyUpdateObservers();
    }

    public void setGold(int inGold) throws CharacterException
    {
        if (inGold < 0)
        {
            throw new CharacterException("Gold must be >= 0");
        }
        else
        {
            gold = inGold;
            this.notifyUpdateObservers();
        }
    }

    public void setFighting(boolean b)
    {
        fighting = b;
    }

    /**
     * Health mutator. Used by defend() and attack() to update Character's 
     * Health.
     * @param inHealth The character's new health
     */
    public void setHealth(int inHealth)
    {
        if (inHealth <= 0)
        {
            currHealth = 0;
            this.notifyDeathObservers();
        }
        else if (inHealth > maxHealth) // Stop health exceeding max
        {
            currHealth = maxHealth;
        }
        else
        {
            currHealth = inHealth;
        }
        
        this.notifyUpdateObservers();
    }

    /**
     * Produces a number denoting the amount of damage to inflict upon an
     * opponent.
     * @param dice Random generator
     * @return The total damage
     */
    public int attack(Dice dice)
    {
        int dmg = 0;

        // Try use potion
        if ((dmg = inventory.useDamagePotion(dice)) == 0)
        {
            // Calculate base damage
            dmg += dice.roll(baseMinDamage, baseMaxDamage);

            // If the character has a weapon, use it and append it's damage
            if (inventory.getWeapon() != null)
            {
                dmg += inventory.getWeapon().getEffect(dice);
            }
        }

        // Update the battle
        this.notifyActionObservers(name + " ATTACKED for " + 
                                   dmg + " DAMAGE");

        return dmg; 
    }

    /**
     * Absorbs an opponent's incomming attack and reduces the Character's
     * health accordingly.
     * @param dmg The incomming damage
     * @param dice Random generator
     */
    public void defend(int dmg, Dice dice)
    {
        int def = 0;

        // Calculate base defence
        def += dice.roll(baseMinDefence, baseMaxDefence);

        // If the character has armour, use it to absorb damage
        if (inventory.getArmour() != null)
        {
            def += inventory.getArmour().getEffect(dice);
        }

        // Take damage
        int oldHealth = this.getHealth();
        this.setHealth(currHealth - Math.max(0, dmg - def));

        // Update the battle
        this.notifyActionObservers(name + " ABSORBED " + 
                                   def + " DAMAGE, and LOST " + 
                                   (oldHealth - this.getHealth()) + 
                                   " HEALTH");
    }

    public void addUpdateObserver(CharacterUpdateObservable ob)
    {
        updateObservers.add(ob);
    }

    public void addActionObserver(CharacterActionObservable ob)
    {
        actionObservers.add(ob);
    }

    public void addDeathObserver(CharacterDeathObservable ob)
    {
        deathObservers.add(ob);
    }

    public void notifyUpdateObservers()
    {
        for (CharacterUpdateObservable ob : updateObservers)
        {
            ob.updateCharacter(this);
        }
    }

    public void notifyActionObservers(String actionMsg)
    {
        for (CharacterActionObservable ob : actionObservers)
        {
            ob.updateBattle(actionMsg);
        }
    }

    public void notifyDeathObservers()
    {
        for (CharacterDeathObservable ob : deathObservers)
        {
            ob.characterDead();
        }
    }
}