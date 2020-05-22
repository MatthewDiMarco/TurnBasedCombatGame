package model.items;
import java.util.*;

/**
 * For storing and accessing a list of items
 */
public class Inventory
{
    private ArrayList<Item> items;
    protected int numItems;

    public Inventory() 
    {
        items = new ArrayList<Item>();
        numItems = 0;
    }

    public Item getItem(int index)
    {
        if (index >= items.size())
        {
            throw new IllegalArgumentException("No item at index " + index);
        }
        
        return items.get(index);
    }

    public List<Item> getItems()
    {
        return items;
    }

    public void addItem(Item inItem) throws InventoryException
    {
        items.add(inItem);
        numItems++;
    }

    public void removeItem(int index) throws InventoryException
    {
        items.remove(index);
        numItems--;
    }
}