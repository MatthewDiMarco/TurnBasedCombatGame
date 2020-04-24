package view;
import controller.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

/**
 * Responsible for enabling interaction between the player and their items, 
 * including equiping and using
 */
public class InventoryView extends JFrame
{
    // Constants
    public static final int WIDTH = 320;
    public static final int HEIGHT = 180;

    // Widgets
    private JList<String> items;
    private JButton equipBtn;
    private JButton useBtn;
    private JButton closeBtn;

    // Controllers
    private PlayerController playerCon;

    /**
     * Constructor.
     * @param inPlayerController
     */
    public InventoryView(PlayerController inPlayerController)
    {
        super("Player Inventory");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Controllers
        playerCon = inPlayerController; 

        // Initialise Widgets
        items = new JList<String>();
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipBtn = new JButton("Equip");
        useBtn = new JButton("Use");
        closeBtn = new JButton("Close");

        //
        // LAYOUT
        //

        // Initialise master pane (everything in the frame stems from this)
        JPanel masterPane = new JPanel();
        masterPane.setLayout(new BoxLayout(masterPane, BoxLayout.Y_AXIS));
        masterPane.setBorder(
            BorderFactory.createEmptyBorder(
                MainView.PADDING, MainView.PADDING, MainView.PADDING, MainView.PADDING
            )
        );

        // Inventory pane
        JPanel invPane = new JPanel(new BorderLayout());
        JToolBar toolbar = new JToolBar();
        toolbar.add(equipBtn);
        toolbar.add(useBtn);
        JScrollPane itemPane = new JScrollPane(items);
        invPane.add(toolbar, BorderLayout.NORTH);
        invPane.add(itemPane, BorderLayout.CENTER);

        // Button pane (close btn)
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(closeBtn);

        // Bring it all together
        masterPane.add(invPane);
        masterPane.add(buttonPane);
        this.add(masterPane);

        //
        // ACTION LISTENERS
        //

        // Equip Button
        equipBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int itemIndex = items.getSelectedIndex();
                    if (itemIndex != -1)
                    {
                        // Try equiping item
                        try
                        {
                            playerCon.equipItem(itemIndex);
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
            }
        );

        // Use Button
        useBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int itemIndex = items.getSelectedIndex();
                    if (itemIndex != -1)
                    {
                        // Try using
                        try
                        {
                            playerCon.prepPotion(itemIndex);
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
            }
        );

        // Close Button
        closeBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    setVisible(false);
                }
            }
        );

        showInventory(playerCon.getInventory());

        // Fit window
        pack();
    }

    /**
     * Show the inventory items
     */
    public void showInventory(Vector<String> inv)
    {
        items.setListData(inv);
    }
}