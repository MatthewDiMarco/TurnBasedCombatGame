package model.items;
import java.util.*;

/**
 * Potions are used to either deal damage or heal the user.
 */
public class ConsumableItem extends Item
{
    private char effType;

    /**
     * Constructor.
     * @param inName The Potion's name
     * @param inCost The Potion's cost
     * @param inMinEff Minimum effect
     * @param inMinEff Maximum effect
     * @param inType Char for Healing or Damage (H/D)
     */
    public ConsumableItem(String inName, int inCost, 
                          int inMinEff, int inMaxEff, char inType)
    {
        super(inName, inCost, inMinEff, inMaxEff);
        if (Character.toUpperCase(inType) != 'H' &&
            Character.toUpperCase(inType) != 'D')
        {
            throw new IllegalArgumentException(
                "Potion type must be H or D for 'Healing' or 'Damage'"
            );
        }

        effType = Character.toUpperCase(inType);
    }

    @Override
    public int getEffect(Dice dice)
    {
        int effect = super.getEffect(dice);
        if (!isHealing())
        {
            effect = -(effect); // Damage
        }

        return effect;
    }

    /**
     * Type accessor.
     * @return True if healing, false if damage
     */
    public boolean isHealing()
    {
        return effType == 'H';
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public String getEffectRange()
    {
        String effect = "Healing";
        if (!isHealing())
        {
            effect = "Damage";
        }
        return super.getEffectRange() + " " + effect;
    }
}