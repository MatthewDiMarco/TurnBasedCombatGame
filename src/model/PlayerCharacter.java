package model;

/**
 * [description]
 */
public class PlayerCharacter extends GameCharacter
{
    // Player Attributes
    public static final int MAX_HEALTH  = 30;
    public static final int DEFAULT_GOLD    = 100;
    private InventoryList inventory;

    /**
     * Constructor.
     * @param inName The player's name.
     * @param inInventory The player's inventory.
     */
    public PlayerCharacter(String inName, InventoryList inInventory)
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
     * Inventory accessor.
     * @return The InventoryList object
     */
    public InventoryList getInventory()
    {
        return inventory;
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
    public int modifier(int damage)
    {
        return 0;
    }

    @Override
    public void die()
    {  
        //todo
    }
}