package view;
import controller.BattleController;
import controller.PlayerController;
import model.characters.CharacterActionObservable;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;

/**
 * This view is responsible for displaying player and opponent stats, showing
 * battle messages as the fight continues, and letting the player make choices.
 */
public class BattleView extends JFrame implements CharacterActionObservable
{
    // Constants
    public static final int WIDTH = 640;
    public static final int HEIGHT = 320;

    // Widgets
    private CharacterStatsPanel playerStats;
    private CharacterStatsPanel enemyStats;
    private JList<String> messages;
    private JButton attackBtn;
    private JButton usePotionBtn;

    // Views
    InventoryView inventoryView;

    // Controllers
    private BattleController battleCon;
    private PlayerController playerCon;

    /**
     * Constructor.
     * @param inBattleController
     * @param inPlayerController
     */
    public BattleView(BattleController inBattleController, 
                      PlayerController inPlayerController,
                      InventoryView inInventoryView) 
    {
        super("Battle");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Views
        inventoryView = inInventoryView;

        // Controllers
        battleCon = inBattleController;
        playerCon = inPlayerController;

        // Initialise Widgets
        playerStats = new CharacterStatsPanel(playerCon.getPlayer());
        enemyStats = new CharacterStatsPanel(battleCon.getEnemy());
        messages = new JList<String>(new DefaultListModel<String>());
        messages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attackBtn = new JButton("Attack");
        usePotionBtn = new JButton("Use Potion");

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

        // Enemy vs Player panel
        // Displays player/enemy stats side by side
        JPanel vsPane = new JPanel(new GridLayout(1, 2));
        vsPane.add(playerStats);
        vsPane.add(enemyStats);

        // Button panel
        JPanel buttonsPane = new JPanel();
        buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.X_AXIS));
        buttonsPane.add(Box.createHorizontalGlue());
        buttonsPane.add(attackBtn);
        buttonsPane.add(Box.createRigidArea(new Dimension(MainView.PADDING, 0)));
        buttonsPane.add(usePotionBtn);

        // Message feed
        // Game messages are appended here (e.g. player healed/attacked etc.)
        JPanel masterFeedPane = new JPanel();
        masterFeedPane.setLayout(new BoxLayout(masterFeedPane, BoxLayout.Y_AXIS));
        JScrollPane messageFeedPane = new JScrollPane(messages);
        messageFeedPane.setPreferredSize(new Dimension());

        // Connect Feed and Button panels
        masterFeedPane.add(messageFeedPane);
        masterFeedPane.add(buttonsPane);

        // Bring it all together
        masterPane.add(vsPane);             // player vs enemy
        masterPane.add(masterFeedPane);     // Buttons | Messages
        this.add(masterPane);

        //
        // ACTIONS LISTENERS
        //

        // Attack button
        attackBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Talk to controller
                }
            }
        );

        // Use a potion button
        usePotionBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    inventoryView.setVisible(true);
                }
            }
        );

        playerCon.getPlayer().addActionObserver(this);
        battleCon.getEnemy().addActionObserver(this);

        // Fit window
        pack();
    }

    /**
     * Set up a new encounter.
     */
    public void startBattle()
    {
        clear();
        enemyStats.updateCharacter(battleCon.getEnemy());
        battleCon.getEnemy().addUpdateObserver(enemyStats);
        JOptionPane.showMessageDialog(
            null, 
            "A wild " + battleCon.getEnemy().getName() + " has appeared!"
        );
    }

    public void clear()
    {
        messages = new JList<String>(new DefaultListModel<String>()); // reset
    }

    /**
     * Add a new message.
     */
    public void addMessage(String msg)
    {
        ((DefaultListModel<String>)messages.getModel()).addElement(msg);
    }

    @Override
    public void updateBattle(String msg) 
    {
        addMessage(msg);
    }
}