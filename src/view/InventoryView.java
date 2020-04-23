package view;
import controller.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

public class InventoryView extends JFrame
{
    // Constants
    private static final int PADDING = 20;
    public static final int WIDTH = 480;
    public static final int HEIGHT = 240;

    // Widgets
    private JList<String> items;
    private JButton equipBtn;
    private JButton useBtn;
    private JButton sellBtn;
    private JButton closeBtn;

    // Controllers
    private PlayerController playerCon;
    private BattleController battleCon;
    private ShopController shopCon;

    // Other
    private boolean inBattle;

    /**
     * 
     * @param inPlayerController
     * @param inBattleController
     * @param inShopController
     */
    public InventoryView(PlayerController inPlayerController,
                         BattleController inBattleController,
                         ShopController inShopController)
    {
        super("Player Inventory");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Controllers
        playerCon = inPlayerController; 
        battleCon = inBattleController; 
        shopCon = inShopController; 

        // Initialise Widgets
        items = new JList<String>();
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipBtn = new JButton("Equip");
        useBtn = new JButton("Use");
        sellBtn = new JButton("Sell");
        closeBtn = new JButton("Close");

        // Layout
        JScrollPane scrollPane = new JScrollPane(items);
        JToolBar toolbar = new JToolBar();

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(toolbar, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        toolbar.add(equipBtn);
        toolbar.add(useBtn);
        toolbar.add(sellBtn);
        toolbar.addSeparator(new Dimension(PADDING, PADDING));
        toolbar.add(closeBtn);

        getRootPane().setContentPane(contentPane);

        // Equip Button
        equipBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if (!inBattle) // todo: Change this so controller passes an exception instead if player is fighting
                    {
                        int itemIndex = items.getSelectedIndex();
                        if (itemIndex != -1)
                        {
                            playerCon.equipItem(itemIndex);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "You cannot equip items in battle");
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
                    if (inBattle)
                    {
                        //talk to battle controller
                        System.out.println("*use*");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "You can only use items in battle");
                    }
                }
            }
        );

        // Sell Button
        sellBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if (!inBattle)
                    {
                        // controller stuff
                        System.out.println("*sell*");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "You must visit the shop to sell items");
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
                    System.out.println("*Closing*");
                    setVisible(false);
                }
            }
        );

        // Default
        inBattle = false;
        showInventory();

        // Fit window
        pack();
    }

    public void inBattle(boolean bool)
    {
        inBattle = bool;
    }

    public void showInventory()
    {
        items.setListData(playerCon.getInventory());
    }
}