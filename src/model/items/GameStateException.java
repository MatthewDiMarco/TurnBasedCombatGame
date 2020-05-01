package model.items;

/**
 * All exceptions relating to illegal game model state will extend this.
 */
public class GameStateException extends Exception
{
    public GameStateException(String s)
    {
        super(s);
    }
}