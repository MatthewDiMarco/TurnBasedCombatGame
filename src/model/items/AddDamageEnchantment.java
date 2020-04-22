package model.items;
import java.util.*;

public class AddDamageEnchantment extends Enchantment
{
    public AddDamageEnchantment(DamageItem inNext, String inName, int inCost, 
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