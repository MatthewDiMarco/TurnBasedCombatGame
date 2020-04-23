package model.characters;
import model.items.PlayerInventory;

/**
 * Represents the player entity. Builds upon GameCharacter by aggregating an
 * inventory to handle item storing and weapon/armour usage.
 */
public class PlayerCharacter extends GameCharacter
{
    // Player Attributes
    public static final int MAX_HEALTH      = 30;
    public static final int DEFAULT_GOLD    = 100;
    private String name;
    private PlayerInventory inventory;

    /**
     * Constructor.
     * @param inName The player's name.
     * @param inInventory The player's inventory.
     */
    public PlayerCharacter(String inName, PlayerInventory inInventory)
    {
        super(inName, DEFAULT_GOLD, MAX_HEALTH);
        if (inInventory == null)
        {
            throw new IllegalArgumentException(
                "Invalid inventory"
            );
        }

        inventory = inInventory;
    }
    
    /**
     * Player String representation
     * @return Player as a String
     */
    @Override
    public String toString()
    {
        return name + ", " + super.toString();
    }

    /**
     * Produces a number denoting the amount to subtract from an 
     * opponent's health.
     * @return The total damage
     */
    @Override
    public int attack()
    {
        return inventory.useWeapon();
    }

    /**
     * Absorbs an opponent's incomming attack and reduces the player's
     * health accordingly.
     * @param dmg The damage to be absorbed
     */
    @Override
    public void defend(int dmg)
    {
        int def = inventory.useArmour();
        this.setHealth(currHealth - Math.max(0, dmg - def));
    }

    @Override
    protected void die()
    {  
        //todo
        super.die();
        System.out.println(name + " WAS KILLED IN BATTLE");//temp
    }
}