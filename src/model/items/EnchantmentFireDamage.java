package model.items;
import java.util.*;

public class EnchantmentFireDamage extends Enchantment
{
    public EnchantmentFireDamage(DamageItem inNext, 
                                 int inMin, int inMax, int inCost)
    {
        super(inNext, "FIRE DMG (+" + inMin + "-" + inMax + ")", 
              inCost, inMin, inMax);

    }

    @Override
    public int getEffect(Random randGenerator)
    {
        return next.getEffect(randGenerator) +
               randGenerator.nextInt(maxEffect + 1 - minEffect) + minEffect;
    }
}