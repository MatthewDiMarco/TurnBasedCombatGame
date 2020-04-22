package model.items;

/**
 * Weapons are used to deal damage to an opponent.
 * They can be granted enchantments to extend their destructive potential.
 */
public abstract class DamageItem extends Item
{
    private String dmgType;

    /**
     * Constructor.
     * @param inName The Weapon's name
     * @param inCost The Weapon's cost
     * @param inMinEff Minimum damage
     * @param inMinEff Maximum damage
     * @param inDmgType Damage type
     * @param inWpnType Weapon type
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

    /**
     * A simple string representation of the Item
     * @return A String containing the attributes
     */
    @Override
    public String toString()
    {
        return super.toString() + ", " + 
               minEffect + "-" + maxEffect + " " + dmgType + " Damage";
    }
}