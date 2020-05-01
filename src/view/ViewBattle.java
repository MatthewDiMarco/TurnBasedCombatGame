package view;
import controller.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.*;

public class ViewBattle extends ViewPanel
{
    // Widgets
    private JPanel userOptions;
    private CharacterStatsPanel enemyStats;
    private InventoryPanel playerInventory;
    private JList<String> messages;
    private JButton attackBtn;
    private JButton usePotionBtn;
    private JButton selectBtn;
    private JButton backBtn;

    // Controllers
    private BattleController battleCon;
    private PlayerController playerCon;

    public ViewBattle(BattleController inBattleCon, 
                      PlayerController inPlayerCon)
    {
        super("", PADDING);
        battleCon = inBattleCon;
        playerCon = inPlayerCon;

        // Initialise Widgets
        userOptions = new JPanel(new CardLayout());
        enemyStats = new CharacterStatsPanel(battleCon.getEnemy());
        playerInventory = new InventoryPanel(playerCon.getPlayer().getInventory());
        messages = new JList<String>(new DefaultListModel<String>());
        messages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attackBtn = new JButton("Attack");
        usePotionBtn = new JButton("Use Potion");
        selectBtn = new JButton("Select");
        backBtn = new JButton("Back");
    }

    @Override
    public void init()
    {
        this.setLayout(new BorderLayout());

        // Message feed
        JPanel messagePane = new JPanel(new BorderLayout());
        JToolBar msgToolbar = new JToolBar();
        msgToolbar.setFloatable(false);
        msgToolbar.add(attackBtn);
        msgToolbar.add(usePotionBtn);
        JScrollPane feed = new JScrollPane(messages);
        messagePane.add(msgToolbar, BorderLayout.NORTH);
        messagePane.add(feed, BorderLayout.CENTER);

        // Inventory selection
        JPanel inventoryPane = new JPanel(new BorderLayout());
        JToolBar invToolbar = new JToolBar();
        invToolbar.setFloatable(false);
        invToolbar.add(selectBtn);
        invToolbar.add(backBtn);
        inventoryPane.add(invToolbar, BorderLayout.NORTH);
        inventoryPane.add(playerInventory, BorderLayout.CENTER);

        // Card layout for attack and potions
        userOptions.add(messagePane, "MSG");
        userOptions.add(inventoryPane, "INV");

        // Join panes
        this.add(enemyStats, BorderLayout.NORTH);
        this.add(userOptions, BorderLayout.CENTER);

        //
        // ACTION LISTENERS
        //

        ViewBattle view = this;

        // Attack Button
        attackBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        //todo
                        System.out.println("*ATTACK*");
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

        // Select Button
        selectBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        //todo
                        System.out.println("*SELECTED ITEM*");
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

        // Use Potion button
        usePotionBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    view.setPane("INV");
                }
            }
        );

        // Exit Inventory button
        backBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    view.setPane("MSG");
                }
            }
        );
    }

    public void setPane(String name)
    {
        CardLayout cl = (CardLayout)(userOptions.getLayout());
        cl.show(userOptions, name);
    }   
}