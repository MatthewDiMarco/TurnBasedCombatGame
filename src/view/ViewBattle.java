package view;
import controller.*;
import model.characters.CharacterActionObservable;
import model.characters.CharacterDeathObservable;
import model.items.GameStateException;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

/**
 * This view displays the stats events of an ongoing battle between the player 
 * and their opponent. 
 */
public class ViewBattle extends ViewPanel implements CharacterActionObservable,
                                                     CharacterDeathObservable
{
    // Widgets
    private JPanel userOptions;
    private CharacterStatsPanel enemyStats;
    private InventoryPanel playerInventory;
    private JList<String> messages;
    private JScrollPane feed;
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
        super(new FlowLayout(), "", PADDING);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        battleCon = inBattleCon;
        playerCon = inPlayerCon;

        // Initialise Widgets
        userOptions = new JPanel(new CardLayout());
        enemyStats = new CharacterStatsPanel(battleCon.getEnemy());
        playerInventory = new InventoryPanel(playerCon.getPlayer().getInventory());
        messages = new JList<String>(new DefaultListModel<String>());
        messages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        feed = new JScrollPane(messages);
        attackBtn = new JButton("Attack");
        usePotionBtn = new JButton("Use Potion");
        selectBtn = new JButton("Select");
        backBtn = new JButton("Back");

        playerCon.getPlayer().addActionObserver(this);
        playerCon.getPlayer().addDeathObserver(this);
    }

    public void init()
    {
        this.setLayout(new BorderLayout());

        // Message feed
        JPanel messagePane = new ViewPanel(new BorderLayout(), "", PADDING);
        JToolBar msgToolbar = new JToolBar();
        msgToolbar.setFloatable(false);
        msgToolbar.add(attackBtn);
        msgToolbar.add(usePotionBtn);

        messagePane.add(msgToolbar, BorderLayout.NORTH);
        messagePane.add(feed, BorderLayout.CENTER);

        // Inventory selection
        JPanel inventoryPane = new ViewPanel(new BorderLayout(), "", PADDING);
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
                        battleCon.endTurn(-1);
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
        );

        // Select Button
        selectBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int itemIndex = playerInventory.getItemIndex();
                    if (itemIndex != -1)
                    {
                        try
                        {
                            battleCon.endTurn(itemIndex);
                            view.setPane("MSG");
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

    public void newBattle()
    {
        battleCon.newBattle();
        battleCon.getEnemy().addActionObserver(this);
        battleCon.getEnemy().addUpdateObserver(enemyStats);
        battleCon.getEnemy().addDeathObserver(this);
        enemyStats.updateCharacter(battleCon.getEnemy());
        ((DefaultListModel<String>)messages.getModel()).removeAllElements();
    }

    @Override
    public void updateBattle(String msg)
    {
        ((DefaultListModel<String>)messages.getModel()).addElement(" ");
        ((DefaultListModel<String>)messages.getModel()).addElement(msg);
        
        // Keep the scroll bar at the bottom (w/ most recent messages)
        SwingUtilities.invokeLater(
        () -> 
            {
                JScrollBar bar = feed.getVerticalScrollBar();
                bar.setValue(bar.getMaximum());
            }
        );
    }

    @Override
    public void characterDead()
    {
        boolean playerDead = playerCon.getPlayer().getHealth() == 0;
        if (playerDead)
        {
            JOptionPane.showMessageDialog(
                null,
                "You were slain"
            );
        }
        else
        {
            JOptionPane.showMessageDialog(
                null,
                "Victory! +" + battleCon.getEnemy().getGold() + " GOLD"
            );
        }
    }
}