package model.characters;

/**
 * Character Update Observers are notified when the character's state changes.
 */
public interface CharacterUpdateObservable
{
    public void updateCharacter(GameCharacter character);
}