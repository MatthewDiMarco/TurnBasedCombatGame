package model.exceptions;

/**
 * When inventory capacity is reached.
 */
public class InventoryFullException extends GameStateException
{
    public InventoryFullException(String s)
    {
        super(s);
    }
}