package view;
import controller.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * The main application view from which other views stem from.
 * Contains the main menu and displays the player's stats at all times.
 */
public class MainView extends JFrame
{
    // Constants
    public static final int PADDING = 5;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 320;
    public static final int NUM_BUTTONS = 5;

    // Menu
    private JButton shopBtn;
    private JButton changeNameBtn;
    private JButton invBtn;
    private JButton battleBtn;
    private JButton exitBtn;

    // Other views
    private NamePromptView namePromptView;
    private InventoryView inventoryView;
    private BattleView battleView;
    private ShopView shopView;

    // Controllers
    private PlayerController playerCon;
    private BattleController battleCon;

    /**
     * Constructor.
     * @param inNamePrompt
     * @param inInventoryView
     * @param inBattleView
     * @param inShopView
     */
    public MainView(PlayerController inPlayerController,
                    NamePromptView inNamePrompt,
                    InventoryView inInventoryView,
                    BattleView inBattleView,
                    ShopView inShopView)
    {
        super("Main Menu");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Views
        namePromptView = inNamePrompt;
        inventoryView = inInventoryView; 
        battleView = inBattleView;
        shopView = inShopView;

        // Controllers
        playerCon = inPlayerController;

        // Buttons
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
        masterPane.setLayout(new BoxLayout(masterPane, BoxLayout.X_AXIS));
        masterPane.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        // Main Menu pane
        JPanel menuPane = new JPanel(new GridLayout(NUM_BUTTONS, 1));
        menuPane.add(shopBtn);
        menuPane.add(changeNameBtn);
        menuPane.add(invBtn);
        menuPane.add(battleBtn);
        menuPane.add(exitBtn);

        // Player stats pane
        CharacterStatsPanel playerPane = new CharacterStatsPanel(
            playerCon.getPlayerStats()
        );
        
        // Bring it all together
        masterPane.add(menuPane);
        masterPane.add(playerPane);
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
                    view.setVisibility(false);
                    view.setVisible(false);
                    battleView.setVisible(true);
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

        // Fit Window
        pack();
    }

    /**
     * Close everything.
     * 
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
    private void setVisibility(boolean b)
    { 
        namePromptView.setVisible(b);
        inventoryView.setVisible(b);
        shopView.setVisible(b);
        battleView.setVisible(b);
    }
}