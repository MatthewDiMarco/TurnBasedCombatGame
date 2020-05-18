package view;
import model.items.GameStateException;
import model.items.Item;
import controller.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

public class ViewShop extends ViewPanel
{
    private boolean enchantmentMode;

    // Widgets
    private JList<String> products;
    private InventoryPanel playerItems;
    private JButton buyBtn;
    private JButton sellBtn;
    private JButton itemsBtn;
    private JButton enchantmentsBtn;

    // Controllers
    private ShopController shopCon;
    private PlayerController playerCon;

    public ViewShop(ShopController inShopController, 
                    PlayerController inPlayerController)
    {
        super(new FlowLayout(), "SHOP", Window.PADDING);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        shopCon = inShopController;
        playerCon = inPlayerController;
        enchantmentMode = false;

        // Initialise Widgets
        products = new JList<String>();
        products.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerItems = new InventoryPanel(playerCon.getPlayer().getInventory());

        buyBtn = new JButton("Buy");
        sellBtn = new JButton("Sell for 50% value");
        itemsBtn = new JButton("Items");
        enchantmentsBtn = new JButton("Enchantments");
    }

    public void init()
    {
        JPanel shopPane = new JPanel(new BorderLayout());
        JPanel playerPane = new JPanel(new BorderLayout());

        // Shop
        JToolBar shpToolbar = new JToolBar();
        shpToolbar.setFloatable(false);
        shpToolbar.add(buyBtn);
        shpToolbar.addSeparator(new Dimension());
        shpToolbar.add(itemsBtn);
        shpToolbar.add(enchantmentsBtn);
        JScrollPane shpItemPane = new JScrollPane(products);
        shopPane.add(shpToolbar, BorderLayout.NORTH);
        shopPane.add(shpItemPane, BorderLayout.CENTER);

        // Player
        JToolBar plToolbar = new JToolBar();
        plToolbar.setFloatable(false);
        plToolbar.add(sellBtn);
        playerPane.add(plToolbar, BorderLayout.NORTH);
        playerPane.add(playerItems, BorderLayout.CENTER);

        // Join panes
        this.add(Box.createRigidArea(new Dimension(PADDING, PADDING)));
        this.add(shopPane);
        this.add(playerPane);

        showItems(shopCon.getShopItems());

        //
        // ACTION LISTENERS
        //

        ViewShop view = this;

        // TEMP NOTE: two possible exceptions: inv full or insuf. gold
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
                            if (enchantmentMode)
                            {
                                shopCon.buyEnchantment(playerCon.getPlayer(), itemIndex);
                            }
                            else
                            {
                                shopCon.buyItem(playerCon.getPlayer(), itemIndex);
                            }
                        }
                        catch (GameStateException e2)
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
                        // Try buying
                        try
                        {
                            shopCon.sellItem(playerCon.getPlayer(), itemIndex);
                        }
                        catch (GameStateException e2)
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
    }

    /**
     * Show the products (items or enchantments)
     */
    public void showItems(List<Item> inProducts)
    {
        Vector<String> inv = new Vector<String>();
        for (Item ii : inProducts)
        {
            inv.add(ii.getCost() + " G" + "   " +  
                    ii.toString() + "   " + 
                    ii.getEffectRange());
        }

        products.setListData(inv);
    }

    public void showEnchantments(Vector<String> inEnchantments)
    {
        products.setListData(inEnchantments);
    }
}