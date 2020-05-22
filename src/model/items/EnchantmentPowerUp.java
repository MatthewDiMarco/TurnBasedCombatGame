package model.items;

/**
 * Multiplies damage by an amount (factor)
 */
public class EnchantmentPowerUp extends Enchantment
{
    private double factor;

    public EnchantmentPowerUp(DamageItem inNext, double inFactor, int inCost)
    {
        super(inNext, ("POW-UP (x" + inFactor + ")"), inCost, 
             (int)(inNext.getMin()*inFactor) - inNext.getMin(), 
             (int)(inNext.getMax()*inFactor) - inNext.getMax());

        if (inFactor <= 1)
        {
            throw new IllegalArgumentException(
                "Damage factor must be at least 1.x"
            );
        }

        factor = inFactor;
    }

    @Override
    public int getEffect(Dice dice)
    {
        return (int)Math.round(next.getEffect(dice) * factor);
    }   
}