package model.items;
import java.util.*;

public class EnchantmentAddDamage extends Enchantment
{
    public EnchantmentAddDamage(DamageItem inNext, String inName, int inCost, 
                                int inMinEff, int inMaxEff, 
                                String inDmgType)
    {
        super(inNext, inName, inCost, inMinEff, inMaxEff, inDmgType);
    }

    @Override
    public int getEffect(Random randGenerator)
    {
        return next.getEffect(randGenerator) + 
               randGenerator.nextInt(maxEffect + 1 - minEffect) + minEffect;
    }
}