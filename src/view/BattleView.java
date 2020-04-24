package view;
import controller.BattleController;
import controller.PlayerController;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

/**
 * This view is responsible for displaying player and opponent stats, showing
 * battle messages as the fight continues, and letting the player make choices.
 */
public class BattleView extends JFrame
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

    // Controllers
    private BattleController battleCon;
    private PlayerController playerCon;

    /**
     * Constructor.
     * @param inBattleController
     * @param inPlayerController
     */
    public BattleView(BattleController inBattleController, 
                      PlayerController inPlayerController) 
    {
        super("Battle");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Controllers
        battleCon = inBattleController;
        playerCon = inPlayerController;

        // Initialise Widgets
        playerStats = new CharacterStatsPanel(playerCon.getPlayerStats());
        enemyStats = new CharacterStatsPanel(battleCon.getEnemyStats());
        messages = new JList<String>();
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
        JPanel buttonsPane = new JPanel(new GridLayout(2, 1));
        buttonsPane.add(attackBtn);     // Attack
        buttonsPane.add(usePotionBtn);  // Use Potion

        // Message feed
        // Game messages are appended here (e.g. player healed/attacked etc.)
        JPanel masterFeedPane = new JPanel(new GridLayout(1, 2));
        JScrollPane messageFeedPane = new JScrollPane(messages);
        messageFeedPane.setPreferredSize(new Dimension());

        // Connect Feed and Button panels
        masterFeedPane.add(buttonsPane);
        masterFeedPane.add(messageFeedPane);

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
        attackBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Talk to controller
                }
            }
        );

        // Show battle messages
        showMessages(battleCon.getMessages());

        // Fit window
        pack();
    }

    /**
     * Update the messages list
     */
    public void showMessages(Vector<String> msgs)
    {
        messages.setListData(msgs);
    }

    /**
     * Update the player stats
     */
    public void showPlayerStats(List<String> inPlayerStats)
    {
        playerStats.showCharacterStats(inPlayerStats);
    }

    /**
     * Update the enemy stats
     */
    public void showEnemyStats(List<String> inEnemyStats) 
    {
        enemyStats.showCharacterStats(inEnemyStats);
    }
}