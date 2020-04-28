package model.exceptions;

/**
 * When inventory capacity is reached.
 */
public class InsufficientGoldFundsException extends GameStateException
{
    public InsufficientGoldFundsException(String s)
    {
        super(s);
    }
}