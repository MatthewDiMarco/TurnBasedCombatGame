package model.characters;

/**
 * Character Action Observers are updated with a message of what the character
 * did (e.g. Healed, Attacked, etc).
 */
public interface CharacterActionObservable
{
    public void updateBattle(String message);
}