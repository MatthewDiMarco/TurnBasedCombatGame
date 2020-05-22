package model.items;

/**
 * Adds a fixed amount of damage to a weapon
 */
public class EnchantmentAddDamage extends Enchantment
{
    private int add;

    public EnchantmentAddDamage(DamageItem inNext, int inAdd, int inCost)
    {
        super(inNext, ("DMG +" + inAdd), inCost, inAdd, inAdd);
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