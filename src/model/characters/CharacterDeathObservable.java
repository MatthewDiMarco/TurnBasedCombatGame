package model.characters;

/**
 * Character Death Observers are notified if the character they're observing
 * is killed (health = 0)
 */
public interface CharacterDeathObservable
{
    public void characterDead();
}