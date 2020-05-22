package model.items;

/**
 * Adds a range of damage to a weapon.
 */
public class EnchantmentFireDamage extends Enchantment
{
    public EnchantmentFireDamage(DamageItem inNext, 
                                 int inMin, int inMax, int inCost)
    {
        super(inNext, "FIRE DMG (+" + inMin + "-" + inMax + ")", 
              inCost, inMin, inMax);

    }

    @Override
    public int getEffect(Dice dice)
    {
        return next.getEffect(dice) + dice.roll(minEffect, maxEffect);
    }
}