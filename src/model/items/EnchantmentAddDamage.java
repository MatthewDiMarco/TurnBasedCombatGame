package model.items;
import java.util.*;

public class EnchantmentAddDamage extends Enchantment
{
    private int add;

    public EnchantmentAddDamage(DamageItem inNext, int inAdd, int inCost)
    {
        super(inNext, ("DMG +" + inAdd), inCost, 0, inAdd);
        if (inAdd <= 0)
        {
            throw new IllegalArgumentException(
                "Damage added must be > 0"
            );
        }

        add = inAdd; 
    }

    @Override
    public int getEffect(Dice dice)
    {
        return next.getEffect(dice) + add;
    }
}