package view;
import model.items.CharacterInventory;
import model.items.InventoryUpdateObservable;
import javax.swing.*;
import java.util.*;

/**
 * Draws a game inventory's stats.
 */
public class EquipedPanel extends JPanel implements InventoryUpdateObservable
{
    // Stats
    private JList<String> equiped;

    /**
     * Constructor.
     * @param stats
     */
    public EquipedPanel(CharacterInventory inventory) 
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        equiped = new JList<String>();
        equiped.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane equipedPane = new JScrollPane(equiped);

        this.add(equipedPane);
        inventory.addUpdateObserver(this);
        this.updateInventory(inventory);
    }

    @Override
    public void updateInventory(CharacterInventory inventory)
    {
        Vector<String> eq = new Vector<String>();
        eq.add("EQUIPED: " + inventory.getWeapon().toString() + "     " + 
                inventory.getWeapon().getEffectRange());
        eq.add("EQUIPED: " + inventory.getArmour().toString() + "     " + 
                inventory.getArmour().getEffectRange());
                
        equiped.setListData(eq);
    }  
}