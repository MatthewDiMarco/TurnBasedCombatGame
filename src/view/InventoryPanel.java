package view;
import model.items.InventoryUpdateObservable;
import model.items.CharacterInventory;
import model.items.Item;
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.*;

/**
 * Draws a game character's stats.
 */
public class InventoryPanel extends JPanel implements InventoryUpdateObservable
{
    private JList<String> items;
    private JScrollPane invPane;

    /**
     * Constructor.
     * @param inv The Inventory to display
     */
    public InventoryPanel(CharacterInventory inv) 
    {
        super(new BorderLayout());
        items = new JList<String>();
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        invPane = new JScrollPane(items);
        this.add(invPane);

        inv.addUpdateObserver(this);
        showInventory(inv.getItems());
    }

    /**
     * Show the inventory items
     */
    public void showInventory(List<Item> inventory)
    {
        Vector<String> inv = new Vector<String>();
        for (Item ii : inventory)
        {
            inv.add(ii.toString());
        }

        items.setListData(inv);
    }

    public int getItemIndex()
    {
        return items.getSelectedIndex();
    }

    @Override
    public void updateInventory(List<Item> items)
    {
        showInventory(items);
    }
}