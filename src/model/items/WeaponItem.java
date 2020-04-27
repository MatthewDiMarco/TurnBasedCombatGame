package model.items;
import java.util.*;

/**
 * Weapons are used to deal damage to an opponent.
 * They can be granted enchantments to extend their destructive potential.
 */
public class WeaponItem extends DamageItem
{
    private String wpnType;

    /**
     * Constructor.
     * @param inName The Weapon's name
     * @param inCost The Weapon's cost
     * @param inMinEff Minimum damage
     * @param inMinEff Maximum damage
     * @param inDmgType Damage type
     * @param inWpnType Weapon type
     */
    public WeaponItem(String inName, int inCost, int inMinEff, int inMaxEff, 
                      String inDmgType, String inWpnType)
    {
        super(inName, inCost, inMinEff, inMaxEff, inDmgType);
        if (inWpnType.isEmpty())
        {
            throw new IllegalArgumentException(
                "Must define Weapon's Type"
            );
        }

        wpnType = inWpnType;
    }

    @Override
    public String toString()
    {
        return name + " (" + wpnType + " type)";
    } 

    @Override
    public String getEffectRange()
    {
        return super.getEffectRange() + " " + dmgType + " Damage";
    }
}