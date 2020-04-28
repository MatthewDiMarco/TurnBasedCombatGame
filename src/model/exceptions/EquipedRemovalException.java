package model.exceptions;

/**
 * Attempting to remove / sell an item from a character inventory that is 
 * currently equiped.
 */
public class EquipedRemovalException extends GameStateException
{
    public EquipedRemovalException(String s)
    {
        super(s);
    }
}