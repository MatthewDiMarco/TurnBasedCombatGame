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
    private Random generator;

    public PlayerInventory() 
    {
        super();
        currArmour = null;
        currWeapon = null;
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

    public void equipWeapon(int index)
    {
        currWeapon = weapons.get(index);
    }

    public void equipArmour(int index)
    {
        currArmour = armours.get(index); 
    }

    @Override
    public void addWeapon(DamageItem item)
    {
        //todo exception here
        if (numItems < CAPACITY)
        {
            super.addWeapon(item);
        }
    }

    @Override
    public void addArmour(DefenceItem item)
    {
        //todo exception here
        if (numItems < CAPACITY)
        {
            super.addArmour(item);
        }
    }

    @Override
    public void addPotion(ConsumableItem item)
    {
        //todo exception here
        if (numItems < CAPACITY)
        {
            super.addPotion(item);
        }
    }
}