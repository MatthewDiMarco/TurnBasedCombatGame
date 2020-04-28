package model.items;

/**
 * Weapons are used to deal damage to an opponent.
 * They can be granted enchantments to extend their destructive potential.
 */
public abstract class DamageItem extends EquipItem
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
        super(inName, inCost, inMinEff, inMaxEff, EquipItem.EquipType.WEAPON);
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
}