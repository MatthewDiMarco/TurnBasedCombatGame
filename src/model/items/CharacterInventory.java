package model.items;
import java.util.*;

/**
 * [description]
 */
public class CharacterInventory extends Inventory 
{
    public static final int CAPACITY = 15;
    private DamageItem currWeapon;
    private DefenceItem currArmour;
    private List<InventoryUpdateObservable> updateObservers;

    public CharacterInventory() 
    {
        super();
        updateObservers = new ArrayList<InventoryUpdateObservable>();
        currWeapon = null;
        currArmour = null;
    }

    public DamageItem getWeapon()
    {
        return currWeapon;
    }

    public DefenceItem getArmour()
    {
        return currArmour;
    }

    public void setWeapon(DamageItem inWeapon) throws InventoryException
    {
        boolean inInventory = false;
        for (Item ii : super.getItems())
        {
            if (inWeapon == ii)
            {
                inInventory = true;
            }
        }
    
        if (!inInventory)
        {
            throw new InventoryException(
                "Weapon must be inside inventory to equip"
            );
        }
        
        this.currWeapon = inWeapon;
        this.notifyUpdateObservers();
    }

    public void setArmour(DefenceItem inArmour) throws InventoryException
    {
        boolean inInventory = false;
        for (Item ii : super.getItems())
        {
            if (inArmour == ii)
            {
                inInventory = true;
            }
        }

        if (!inInventory)
        {
            throw new InventoryException(
                "Armour must be inside inventory to equip"
            );
        }

        this.currArmour = inArmour;
        this.notifyUpdateObservers();
    }

    @Override
    public void addItem(Item inItem) throws InventoryException
    {
        if (numItems < CAPACITY)
        {
            super.addItem(inItem);
            notifyUpdateObservers();
        }
        else
        {
            //todo exception here
            throw new InventoryException(
                "Invantory at max capacity (" + CAPACITY + " items)"
            );
        }
    } 

    public void removeItem(Item inItem) throws InventoryException
    {
        List<Item> items = super.getItems();
        for (int ii = 0; ii < items.size(); ii++)
        {
            if (items.get(ii) == inItem)
            {
                this.removeItem(ii);
                this.notifyUpdateObservers();
            }
        }
    }

    @Override
    public void removeItem(int index) throws InventoryException
    {
        if (this.getItem(index) == currWeapon || 
            this.getItem(index) == currArmour)
        {
            throw new InventoryException("Can't remove equiped items");
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