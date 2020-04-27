package model.items;
import java.util.*;

public class EnchantmentPowerUp extends Enchantment
{
    private double factor;

    public EnchantmentPowerUp(DamageItem inNext, double inFactor, int inCost)
    {
        super(inNext, ("POW-UP (x" + inFactor + ")"), inCost, 0, 0);
        if (inFactor <= 1)
        {
            throw new IllegalArgumentException(
                "Damage factor must be at least 1.x"
            );
        }

        factor = inFactor;
    }

    @Override
    public int getEffect(Random randGenerator)
    {
        return (int)Math.round(next.getEffect(randGenerator) * factor);
    }   
}