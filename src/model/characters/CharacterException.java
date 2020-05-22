package model.characters;
import model.items.GameStateException;

/**
 * For all errors concerning invalid character state.
 */
public class CharacterException extends GameStateException
{
    public CharacterException(String s)
    {
        super(s);
    }
}