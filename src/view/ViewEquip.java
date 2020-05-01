package view;
import controller.PlayerController;
import model.items.InventoryUpdateObservable;
import model.items.CharacterInventory;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class ViewEquip extends ViewPanel implements InventoryUpdateObservable
{
    // Widgets
    private InventoryPanel invPane;
    private JLabel currWeaponLbl;
    private JLabel currArmourLbl;
    private JButton selectBtn;

    // Controllers
    private PlayerController playerCon;

    /**
     * Constructor.
     * @param inPlayerCon
     */
    public ViewEquip(PlayerController inPlayerCon)
    {
        super("EQUIP WEAPONS AND ARMOUR", Window.PADDING);
        playerCon = inPlayerCon;

        // Initialise Widgets
        invPane = new InventoryPanel(playerCon.getPlayer().getInventory());
        selectBtn = new JButton("Equip");
        currWeaponLbl = new JLabel("Equiped Weapon: ");
        currArmourLbl = new JLabel("Equiped Armour: ");
    }

    @Override
    public void init()
    {
        JPanel masterPane = new JPanel(new BorderLayout());
        
        JPanel equipPane = new JPanel(new GridLayout(3, 1));
        equipPane.add(currWeaponLbl);
        equipPane.add(currArmourLbl);
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(selectBtn);
        equipPane.add(toolbar);

        masterPane.add(equipPane, BorderLayout.NORTH);
        masterPane.add(invPane, BorderLayout.CENTER);

        this.add(Box.createRigidArea(new Dimension(PADDING, PADDING)));
        this.add(masterPane);

        updateInventory(playerCon.getPlayer().getInventory());

        //
        // ACTION LISTENERS
        //

        // Apply Button
        selectBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        //todo
                        System.out.println("*EQUIPED*");
                    }
                    catch (IllegalArgumentException e2)
                    {
                        // Controller will let us know of problems
                        JOptionPane.showMessageDialog(
                            null, e2.getMessage()
                        );
                    }
                }
            }
        );
    }

    @Override
    public void updateInventory(CharacterInventory inv)
    {
        currWeaponLbl.setText("Equiped Weapon: " + inv.getWeapon().toString() + 
                              " " + inv.getWeapon().getEffectRange());
        currArmourLbl.setText("Equiped Armour: " + inv.getArmour().toString() + 
                              " " + inv.getWeapon().getEffectRange());
    }
}