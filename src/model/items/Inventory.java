package model.items;
import model.exceptions.*;
import java.util.*;

/**
 * [description]
 */
public class Inventory
{
    protected ArrayList<Item> items;
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

    public void addItem(Item inItem)
    {
        items.add(inItem);
        numItems++;
    }

    public void removeItem(int index)
    {
        items.remove(index);
        numItems--;
    }
}