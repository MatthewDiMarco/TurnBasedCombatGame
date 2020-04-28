package model.items;
import java.util.*;

/**
 * [description]
 */
public class CharacterInventory extends Inventory 
{
    public static final int CAPACITY = 15;
    private EquipItem currWeapon;
    private EquipItem currArmour;
    private List<InventoryUpdateObservable> updateObservers;

    public CharacterInventory() 
    {
        super();
        updateObservers = new ArrayList<InventoryUpdateObservable>();
        currWeapon = null;
        currArmour = null;
    }

    public EquipItem getWeapon()
    {
        return currWeapon;
    }

    public EquipItem getArmour()
    {
        return currArmour;
    }

    public int useWeapon(Dice dice)
    {
        int dmg = 0;
        if (currWeapon != null)
        {
            dmg += currWeapon.getEffect(dice);
        }
        return dmg;
    }

    public int useArmour(Dice dice)
    {
        int def = 0;
        if (currArmour != null)
        {
            def += currArmour.getEffect(dice);
        }
        return def;
    }

    public int useItem(int index, Dice dice)
    {
        // Try getting item
        ConsumableItem item;
        try
        {
            item = (ConsumableItem)this.getItem(index);
        }
        catch(ClassCastException e)
        {
            throw new IllegalArgumentException("Not a consumable item");
            //throw new ItemNotConsumableException();
        }

        // Use the item
        int effect = item.getEffect(dice); // damage items will return negative

        // Once 'consumed', remove from inventory
        this.removeItem(index);

        return effect;
    }

    /**
     * 
     * @param index
     */
    public void equip(int index)
    {
        // Get the equipment
        EquipItem equipment;
        try
        {
            equipment = (EquipItem)getItem(index);
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("Not equipable");
            // throw new NotEquipableException(); todo
        }
        
        // If no exceptions were raised, it's either WEAPON or ARMOUR
        EquipItem.EquipType type = equipment.getEquipType();
        switch (type)
        {
            case WEAPON:
                currWeapon = equipment;
                break;

            case ARMOUR:
                currArmour = equipment;
                break;
        }

        this.notifyUpdateObservers();
    }

    @Override
    public void addItem(Item inItem)
    {
        if (numItems < CAPACITY)
        {
            super.addItem(inItem);
            notifyUpdateObservers();
        }
        else
        {
            //todo exception here
            throw new IllegalArgumentException("Inventory full");
        }
    } 

    @Override
    public void removeItem(int index)
    {
        if (this.getItem(index) == currWeapon || 
            this.getItem(index) == currArmour)
        {
            throw new IllegalArgumentException("Can't remove equiped items");
            //todo throw IsEquipedException();
        }

        super.removeItem(index);
        notifyUpdateObservers();
    }

    public void addUpdateObserver(InventoryUpdateObservable ob)
    {
        updateObservers.add(ob);
    }

    protected void notifyUpdateObservers()
    {
        for (InventoryUpdateObservable ob : updateObservers)
        {
            ob.updateInventory(this);
        }
    }
}