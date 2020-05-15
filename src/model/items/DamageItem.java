package model.items;
import model.characters.CharacterException;
import model.characters.GameCharacter;

/**
 * Weapons are used to deal damage to an opponent.
 * They can be granted enchantments to extend their destructive potential.
 */
public abstract class DamageItem extends Item
{
    protected String dmgType;

    /**
     * Constructor.
     * @param inName The Weapon's name
     * @param inCost The Weapon's cost
     * @param inMinEff Minimum damage
     * @param inMinEff Maximum damage
     * @param inDmgType Damage type
     */
    public DamageItem(String inName, int inCost, int inMinEff, int inMaxEff, 
                      String inDmgType)
    {
        super(inName, inCost, inMinEff, inMaxEff);
        if (inDmgType.isEmpty())
        {
            throw new IllegalArgumentException(
                "Must define Item's Damage Type"
            );
        }

        dmgType = inDmgType;
    }

    public String getDamageType()
    {
        return dmgType;
    }

    @Override
    public int interactWith(GameCharacter character) throws ItemInteractionException
    {
        try
        {
            if (character.isFighting())
            {
                throw new ItemInteractionException("You cannot equip weapons while in battle");
            }
            else
            {
                character.setWeapon(this);
            }
        }
        catch (CharacterException e)
        {
            throw new ItemInteractionException(e.getMessage());
        }

        return 0;
    }
}