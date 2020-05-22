package model.items;

/**
 * For Inventory / CharacterInventory related exceptions.
 */
public class InventoryException extends GameStateException
{
    public InventoryException(String s)
    {
        super(s);
    }
}