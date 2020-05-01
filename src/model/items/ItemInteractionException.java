package model.items;

/**
 * When inventory capacity is reached.
 */
public class ItemInteractionException extends GameStateException
{
    public ItemInteractionException(String s)
    {
        super(s);
    }
}