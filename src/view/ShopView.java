package view;
import controller.*;
import model.items.Item;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

/**
 * This view is responsible for displaying all shop items and facilitating
 * transactions with the player.
 */
public class ShopView extends JFrame
{
    // Constants
    public static final int WIDTH = 640;
    public static final int HEIGHT = 320;

    // Widgets
    private JList<String> products;
    private InventoryPanel playerItems;
    private JButton buyBtn;
    private JButton sellBtn;
    private JButton itemsBtn;
    private JButton enchantmentsBtn;
    private JButton closeBtn;

    // Controllers
    private PlayerController playerCon;
    private ShopController shopCon;

    private boolean enchantmentMode;

    /**
     * Constructor.
     * @param inPlayerController
     * @param inShopController
     */
    public ShopView(ShopController inShopController,
                    PlayerController inPlayerController)
    {
        super("Shop");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Controllers
        playerCon = inPlayerController; 
        shopCon = inShopController; 

        // Initialise Widgets
        products = new JList<String>();
        products.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerItems = new InventoryPanel(playerCon.getInventory(), playerCon.getPlayer());
        buyBtn = new JButton("Buy");
        sellBtn = new JButton("Sell for 50% G");
        itemsBtn = new JButton("Items");
        enchantmentsBtn = new JButton("Enchantments");
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

        // Shop Pane (for buying items and enchantments)
        JPanel shopPane = new JPanel(new BorderLayout());
        JToolBar shopToolbar = new JToolBar();
        shopToolbar.setFloatable(false);
        shopToolbar.add(buyBtn);
        shopToolbar.addSeparator(new Dimension(MainView.PADDING, MainView.PADDING));
        shopToolbar.add(itemsBtn);
        shopToolbar.add(enchantmentsBtn); 
        JScrollPane shopItemPane = new JScrollPane(products);
        shopPane.add(shopToolbar, BorderLayout.NORTH);
        shopPane.add(shopItemPane, BorderLayout.CENTER);

        // Player Inventory Pane (for selling items)
        JPanel playerInvPane = new JPanel(new BorderLayout());
        JToolBar playerInvToolbar = new JToolBar();
        playerInvToolbar.setFloatable(false);
        playerInvToolbar.add(sellBtn);  
        JScrollPane playerItemPane = new JScrollPane(playerItems);
        playerInvPane.add(playerInvToolbar, BorderLayout.NORTH);
        playerInvPane.add(playerItemPane, BorderLayout.CENTER);

        // Merge Player and Shop Panes
        JPanel tradePane = new JPanel(new GridLayout(1, 2));
        tradePane.add(shopPane);
        tradePane.add(playerInvPane);

        // Button pane (close btn)
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(closeBtn);

        // Bring it all together
        masterPane.add(tradePane);
        masterPane.add(buttonPane);
        this.add(masterPane);

        //
        // ACTION LISTENERS
        //

        ShopView view = this;

        // Buy Button
        buyBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int itemIndex = products.getSelectedIndex();
                    if (itemIndex != -1)
                    {
                        // Try buying
                        try
                        {
                            // do stuff
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

        // Items Button
        itemsBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    view.showItems(shopCon.getShopItems());
                    enchantmentMode = false;
                }
            }
        );

        // Enchantments Button
        enchantmentsBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    view.showEnchantments(shopCon.getEnchantments());
                    enchantmentMode = true;
                }
            }
        );

        // Sell Button
        sellBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int itemIndex = playerItems.getItemIndex();
                    if (itemIndex != -1)
                    {
                        playerCon.getInventory().removeItem(itemIndex); //temp
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

        showItems(shopCon.getShopItems());

        // Fit window
        pack();
    }

    /**
     * Show the products (items or enchantments)
     */
    public void showItems(List<Item> inProducts)
    {
        Vector<String> inv = new Vector<String>();
        for (Item ii : inProducts)
        {
            inv.add(ii.getCost() + " G" + Item.SPACING +  
                    ii.toString() + Item.SPACING + 
                    ii.getEffectRange());
        }

        products.setListData(inv);
    }

    public void showEnchantments(Vector<String> inEnchantments)
    {
        products.setListData(inEnchantments);
    }
}