package view;
import controller.MainController;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Window extends JFrame
{   
    // Constants
    public static final int PADDING = 5;
    public static final int WIDTH = 768;
    public static final int HEIGHT = 480;

    // Panels
    private JPanel views;
    private CharacterStatsPanel playerStatsPane;
    private InventoryPanel playerInvPane;
    private JPanel masterPane;

    // Buttons
    private JButton shopBtn;
    private JButton changeNameBtn;
    private JButton invBtn;
    private JButton battleBtn;
    private JButton exitBtn;

    // Controllers
    private MainController controller;

    /**
     * Constructor
     * @param inController The main game controller
     */
    public Window(MainController inController)
    {
        // Init basics
        super("Turn-Based Combat Game");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller = inController;

        // Initialise Widgets
        views = new JPanel(new CardLayout());

        playerStatsPane = new CharacterStatsPanel(controller.getPlayer());
        playerInvPane = new InventoryPanel(controller.getPlayer().getInventory());

        shopBtn = new JButton("Shop");
        changeNameBtn = new JButton("Change Name");
        invBtn = new JButton("Equip Items");
        battleBtn = new JButton("Start Battle");
        exitBtn = new JButton("Exit Game");

        // Fit Window
        pack();
    }

    /**
     * Call this after successful construction to setup buttons, widgets
     * and panels.
     */
    public void start(ViewShop inShopView, 
                      ViewEquip inEquipView, 
                      ViewNamePrompt inNameView,
                      ViewBattle inBattleView)
    {
        masterPane = new JPanel();
        masterPane.setLayout(new GridLayout(1, 2));
        masterPane.setBorder(
            BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)
        );

        // Side pane
        JPanel playerPane = new JPanel(new BorderLayout());
        JPanel sidePane = new JPanel(new BorderLayout());
        JPanel buttonsPane = new JPanel(new GridLayout(5, 1)); // Menu options

        buttonsPane.add(shopBtn);
        buttonsPane.add(changeNameBtn);
        buttonsPane.add(invBtn);
        buttonsPane.add(battleBtn);
        buttonsPane.add(exitBtn);

        playerPane.add(playerStatsPane, BorderLayout.NORTH);
        playerPane.add(playerInvPane, BorderLayout.CENTER);
        sidePane.add(playerPane, BorderLayout.NORTH);
        sidePane.add(buttonsPane, BorderLayout.CENTER);

        // Create view pane and add views
        views.add(inShopView, "SHOP");
        views.add(inEquipView, "EQUIP");
        views.add(inNameView, "NAME");
        views.add(inBattleView, "BATTLE");

        // Join panes
        masterPane.add(sidePane);
        masterPane.add(views);
        getRootPane().setContentPane(masterPane);

        //
        // ACTION LISTENERS
        //

        Window win = this;

        // Shop button
        shopBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    win.setView("SHOP");
                }
            }
        );

        // Name prompt button
        changeNameBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    win.setView("NAME");
                }
            }
        );

        // Inventory button
        invBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    win.setView("EQUIP");
                }
            }
        );

        // Start Battle button
        battleBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    win.setView("BATTLE");
                }
            }
        );

        // Exit button
        exitBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    win.dispose();
                }
            }
        );
    }

    public void setView(String name)
    {
        CardLayout cl = (CardLayout)(views.getLayout());
        cl.show(views, name);
    }   
}