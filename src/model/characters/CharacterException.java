package model.characters;
import model.items.GameStateException;

/**
 * When inventory capacity is reached.
 */
public class CharacterException extends GameStateException
{
    public CharacterException(String s)
    {
        super(s);
    }
}