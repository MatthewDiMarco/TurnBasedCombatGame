package model.items;
import java.util.*;

/**
 * [description]
 */
public class PlayerInventory extends Inventory 
{
    public static final int CAPACITY = 15;
    private DefenceItem currArmour;
    private DamageItem currWeapon;
    private ConsumableItem currPotion;
    private Random generator;

    public PlayerInventory() 
    {
        super();
        currArmour = null;
        currWeapon = null;
        currPotion = null; 
        generator = new Random();
    }

    public int useWeapon()
    {
        //todo NoEquipedItemException
        return currWeapon.getEffect(generator);
    }

    public int useArmour()
    {
        //todo NoEquipedItemException
        return currArmour.getEffect(generator);
    }

    /**
     * Use the current potion
     * Returns 0 if there's no equiped potion,
     * greater than 0 if it's a healing potion,
     * less 0 if it's a damage potion.
     * @return The potion's effect as an integer.
     */
    public int usePotion()
    {
        int effect = 0;
        if (currPotion != null)
        {
            effect = currPotion.getEffect(generator);

            // If it's a damage potion return effect as negative to indicate it's
            // is reducing hp, not recovering it.
            if (!currPotion.isHealing())
            {   
                effect = -effect;
            }
        }

        items.remove(currPotion);
        currPotion = null;
        return effect;
    }

    public String getCurrAttackRange()
    {
        String range = "0 - 0";
        if (currWeapon != null)
        {
            range = currWeapon.getEffectRange();
        }
        
        return range;
    }

    public String getCurrDefenceRange()
    {
        String range = "0 - 0";
        if (currWeapon != null)
        {
            range = currArmour.getEffectRange();
        }
        
        return range;
    }

    public void equipWeapon(int index)
    {
        try
        {
            currWeapon = (DamageItem)items.get(index);
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("Not a weapon");
        }
    }

    public void equipArmour(int index)
    {
        try
        {
            currArmour = (DefenceItem)items.get(index);
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("Not armour");
        }
    }

    public void equipPotion(int index)
    {
        try
        {
            currPotion = (ConsumableItem)items.get(index);
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("Not a potion");
        }
    }

    @Override
    public void addItem(Item inItem)
    {
        //todo exception here
        if (numItems < CAPACITY)
        {
            super.addItem(inItem);
        }
        else
        {
            throw new IllegalArgumentException("Inventory full");
        }
    } 
}