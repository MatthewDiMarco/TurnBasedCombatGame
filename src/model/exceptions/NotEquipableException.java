package model.exceptions;

/**
 * When inventory capacity is reached.
 */
public class NotEquipableException extends GameStateException
{
    public NotEquipableException(String s)
    {
        super(s);
    }
}