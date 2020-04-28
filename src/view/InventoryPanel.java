package view;
import model.items.InventoryUpdateObservable;
import model.items.CharacterInventory;
import model.characters.GameCharacter;
import model.items.Item;
import javax.swing.*;
import java.util.*;

/**
 * Draws a game character's stats.
 */
public class InventoryPanel extends JPanel implements InventoryUpdateObservable
{
    private JList<String> items;
    private JScrollPane invPane;
    private EquipedPanel equipedPane;

    /**
     * Constructor.
     * @param inv The Inventory to display
     */
    public InventoryPanel(CharacterInventory inv, GameCharacter player) 
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        items = new JList<String>();
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        invPane = new JScrollPane(items);
        equipedPane = new EquipedPanel(inv);
        
        this.add(invPane);
        this.add(equipedPane);
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
            inv.add(ii.getCost() + " G" + Item.SPACING +  
                    ii.toString() + Item.SPACING + 
                    ii.getEffectRange());
        }

        items.setListData(inv);
    }
}