package model.exceptions;

/**
 * When inventory capacity is reached.
 */
public class NotUsableException extends GameStateException
{
    public NotUsableException(String s)
    {
        super(s);
    }
}