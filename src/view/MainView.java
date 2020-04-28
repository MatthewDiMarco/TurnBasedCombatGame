package view;
import controller.PlayerController;
import controller.BattleController;
import model.characters.CharacterDieObservable;
import model.characters.GameCharacter;
import model.characters.EnemyCharacter;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * The main application view from which other views stem from.
 * Contains the main menu and displays the player's stats at all times.
 */
public class MainView extends JFrame implements CharacterDieObservable
{
    // Constants
    public static final int PADDING = 5;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 320;
    public static final int NUM_BUTTONS = 5;

    // Menu
    private CharacterStatsPanel playerStats;
    private InventoryPanel playerInv;
    private JButton shopBtn;
    private JButton changeNameBtn;
    private JButton invBtn;
    private JButton battleBtn;
    private JButton exitBtn;

    // Other views
    private NamePromptView namePromptView;
    private InventoryView inventoryView;
    private ShopView shopView;
    private BattleView battleView;

    // Controllers
    PlayerController playerCon;
    BattleController battleCon;

    /**
     * Constructor.
     * @param playerInitial
     * @param inNamePrompt
     * @param inInventoryView
     * @param inBattleView
     * @param inShopView
     */
    public MainView(PlayerController inPlayerController,
                    NamePromptView inNamePrompt,
                    InventoryView inInventoryView,
                    ShopView inShopView,
                    BattleView inBattleView,
                    BattleController inBattleController)
    {
        super("Main Menu");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Views
        namePromptView = inNamePrompt;
        inventoryView = inInventoryView; 
        shopView = inShopView;
        battleView = inBattleView;

        playerCon = inPlayerController;
        battleCon = inBattleController;

        // Widgets
        playerStats = new CharacterStatsPanel(playerCon.getPlayer());
        playerInv = new InventoryPanel(playerCon.getInventory(), playerCon.getPlayer());
        shopBtn = new JButton("Shop");
        changeNameBtn = new JButton("Change Name");
        invBtn = new JButton("Equip Items");
        battleBtn = new JButton("Start Battle");
        exitBtn = new JButton("Exit Game");

        //
        // LAYOUT
        //

        // Initialise master pane
        JPanel masterPane = new JPanel();
        masterPane.setLayout(new BoxLayout(masterPane, BoxLayout.Y_AXIS));
        masterPane.setBorder(
            BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)
        );

        // Main Menu pane
        JPanel menuPane = new JPanel(new GridLayout(NUM_BUTTONS, 1));
        menuPane.add(shopBtn);
        menuPane.add(changeNameBtn);
        menuPane.add(invBtn);
        menuPane.add(battleBtn);
        menuPane.add(exitBtn);

        // Player stats pane
        JPanel playerPane = new JPanel(new GridLayout(1, 2));

        CharacterStatsPanel playerStatsPane = playerStats;
        InventoryPanel playerInventoryPane = playerInv;

        playerPane.add(playerStatsPane);
        playerPane.add(playerInventoryPane);
        
        // Bring it all together
        masterPane.add(playerPane);
        masterPane.add(menuPane);
        this.add(masterPane);
        
        //
        // ACTION LISTENERS
        //

        MainView view = this;

        // Shop button
        shopBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    view.setVisibility(false);
                    shopView.setVisible(true);
                }
            }
        );

        // Name prompt button
        changeNameBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    view.setVisibility(false);
                    namePromptView.setVisible(true);
                }
            }
        );

        // Inventory button
        invBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    view.setVisibility(false);
                    inventoryView.setVisible(true);
                }
            }
        );

        // Start Battle button
        battleBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Generate new enemy and start observing it
                    battleCon.newBattle();
                    battleCon.getEnemy().addDieObserver(view);

                    // Setup view
                    view.setVisibility(false); // turn other views off
                    view.setVisible(false);
                    battleView.setVisible(true);
                    battleView.startBattle();
                }
            }
        );

        // Exit button
        exitBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    view.closeAll(); // close
                }
            }
        );

        playerCon.getPlayer().addDieObserver(this);

        // Fit Window
        pack();
    }

    /**
     * Close everything.
     * Credit:
     * stackoverflow.com/questions/258099/
     * how-to-close-a-java-swing-application-from-the-code
     */
    private void closeAll()
    {
        super.processWindowEvent(
            new WindowEvent(
                this, WindowEvent.WINDOW_CLOSING
            )
        );
    }

    /**
     * Hides/Shows all the views depending on the value of b.
     * @param b Visibility
     */
    public void setVisibility(boolean b)
    { 
        namePromptView.setVisible(b);
        inventoryView.setVisible(b);
        shopView.setVisible(b);
        battleView.setVisible(b);
    }

    @Override
    public void characterDead()
    {
        EnemyCharacter enemy = battleCon.getEnemy();

        battleView.clear();
        battleView.setVisible(false);

        if (playerCon.getPlayer().getHealth() == 0)
        {
            this.closeAll();
        }
        else
        {
            this.setVisible(true);

            JOptionPane.showMessageDialog(
                null, 
                "You defeated the " + enemy.getName() + "!\n" + 
                "You loot " + enemy.getGold() + " GOLD from it's corpse, " +
                "and regain " + "[todo]" + " Health"
            ); 
        }
    }
}