package model.items;

/**
 * When inventory capacity is reached.
 */
public class InventoryException extends GameStateException
{
    public InventoryException(String s)
    {
        super(s);
    }
}