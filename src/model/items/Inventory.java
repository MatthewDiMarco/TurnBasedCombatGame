package model.items;
import java.util.*;

/**
 * [description]
 */
public class Inventory
{
    protected ArrayList<DamageItem> weapons;
    protected ArrayList<DefenceItem> armours;
    protected ArrayList<ConsumableItem> potions;
    protected int numItems;

    public Inventory() 
    {
        weapons = new ArrayList<DamageItem>();
        armours = new ArrayList<DefenceItem>();
        potions = new ArrayList<ConsumableItem>();
        numItems = 0;
    }

    public ArrayList<DamageItem> getWeapons()
    {
        return weapons;
    }

    public ArrayList<DefenceItem> getArmours()
    {
        return armours;
    }
    
    public ArrayList<ConsumableItem> getPotions()
    {
        return potions;
    }

    public void addWeapon(DamageItem item)
    {
        weapons.add(item);
        numItems++;
    }

    public void addArmour(DefenceItem item)
    {
        armours.add(item);
        numItems++;
    }

    public void addPotion(ConsumableItem item)
    {
        potions.add(item);
        numItems++;
    }

    public void removeWeapon(int index)
    {
        weapons.remove(index);
        numItems--;
    }

    public void removeArmour(int index)
    {
        armours.remove(index);
        numItems--;
    }

    public void removePotions(int index)
    {
        potions.remove(index);
        numItems--;
    }
}