package model.items;
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