package model.items;

/**
 * For all errors relating to Item / Character interactions.
 */
public class ItemInteractionException extends GameStateException
{
    public ItemInteractionException(String s)
    {
        super(s);
    }
}