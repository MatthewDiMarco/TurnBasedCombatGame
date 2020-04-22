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

    /**
     * Type accessor.
     * @return True if healing, false if damage
     */
    public boolean isHealing()
    {
        return effType == 'H';
    }

    /**
     * A simple string representation of the Potion
     * @return A String containing the attributes
     */
    @Override
    public String toString()
    {
        String effect = "Healing";
        if (effType != 'H')
        {
            effect = "Damage";
        }

        return super.toString() + ", " + 
               minEffect + "-" + maxEffect + " " + effect + ", " +
               "COST: " + getCost() + "G"; 
    }
}