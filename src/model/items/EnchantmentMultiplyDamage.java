package model.items;
import java.util.*;

public class EnchantmentMultiplyDamage extends Enchantment
{
    private double factor;

    public EnchantmentMultiplyDamage(DamageItem inNext, String inName, int inCost, 
                                     String inDmgType, double inFactor)
    {
        super(inNext, inName, inCost, 1, 1, inDmgType);
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