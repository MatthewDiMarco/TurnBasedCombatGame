package view;
import model.items.InventoryUpdateObservable;
import model.items.CharacterInventory;
import model.items.Item;
import javax.swing.*;
import java.util.*;

/**
 * Draws a game character's stats.
 */
public class InventoryPanel extends ViewPanel implements InventoryUpdateObservable
{
    private JList<String> items;
    private JScrollPane invPane;

    /**
     * Constructor.
     * @param inv The Inventory to display
     */
    public InventoryPanel(CharacterInventory inv) 
    {
        super("", 0);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        items = new JList<String>();
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        invPane = new JScrollPane(items);
        
        this.add(invPane);
        inv.addUpdateObserver(this);
        updateInventory(inv);
    }

    public int getItemIndex()
    {
        return items.getSelectedIndex();
    }

    @Override
    public void updateInventory(CharacterInventory inventory)
    {
        List<Item> inItems = inventory.getItems();
        Vector<String> inv = new Vector<String>();
        for (Item ii : inItems)
        {
            inv.add(ii.getCost() + " G" + "   " +  
                    ii.toString() + "   " + 
                    ii.getEffectRange());
        }

        items.setListData(inv);
    }

    @Override
    public void init()
    {
        //todo
    }
}