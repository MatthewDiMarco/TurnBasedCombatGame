package view;
import view.options.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

public class MainView extends JFrame
{
    // Constants
    public static final int WIDTH = 320;
    public static final int HEIGHT = 180;

    // Menu
    private ArrayList<JButton> buttons;
    private ArrayList<Option> options;

    // Other views
    private NamePromptView namePromptView;
    private InventoryView inventoryView;
    private ShopView shopView;
    private BattleView battleView;

    /**
     * 
     * @param inPlayerView
     * @param inNamePromptView
     * @param inInventoryView
     */
    public MainView(PlayerView inPlayerView, 
                    NamePromptView inNamePromptView,
                    InventoryView inInventoryView,
                    ShopView inShopView,
                    BattleView inBattleView)
    {
        super("Main Menu");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Views
        namePromptView = inNamePromptView;
        inventoryView = inInventoryView;
        shopView = inShopView;
        battleView = inBattleView;

        // Map operations to their respective buttons
        buttons = new ArrayList<JButton>();
        options = new ArrayList<Option>();
        buttons.add(new JButton("Go to Shop")); 
        options.add(new OptionGoShop());
        buttons.add(new JButton("Choose Character Name")); 
        options.add(new OptionChangeName());
        buttons.add(new JButton("Sell or Equip Items")); 
        options.add(new OptionInventory());
        buttons.add(new JButton("Start Battle")); 
        options.add(new OptionBattle());
        buttons.add(new JButton("Exit Game")); 
        options.add(new OptionExit());

        // Layout
        JPanel contentPane = new JPanel(new BorderLayout());
        JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
        contentPane.add(toolbar, BorderLayout.NORTH);
        getRootPane().setContentPane(contentPane);

        // Give each button their doOption() method through an Action Listener
        MainView view = this;
        for (int ii = 0; ii < buttons.size(); ii++)
        {
            JButton btn = buttons.get(ii);
            Option op = options.get(ii);

            toolbar.add(btn);

            btn.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        op.doOption(view); // Open/Close some other views
                    }
                }
            );
        }

        // Fit Window
        pack();
    }

    /**
     * Disable/Enables the visibility of the other views.
     */
    public void setVisibility(boolean set)
    {
        namePromptView.setVisible(set);
        inventoryView.setVisible(set);
        shopView.setVisible(set);
    }

    /**
     * Close everything.
     * https://stackoverflow.com/questions/258099/how-to-close-a-java-swing-application-from-the-code
     */
    public void closeAll()
    {
        super.processWindowEvent(
            new WindowEvent(
                this, WindowEvent.WINDOW_CLOSING
            )
        );
    }

    /**
     * Accessor for the name promp
     */
    public NamePromptView getNamePrompt()
    {
        return namePromptView;
    }

    /**
     * Accessor for the inventory view.
     */
    public InventoryView getInventoryView()
    {
        return inventoryView;
    }

    /**
     * Accessor for the inventory view.
     */
    public ShopView getShopView()
    {
        return shopView;
    }

    /**
     * Accessor for the battle view.
     */
    public BattleView getBattleView()
    {
        return battleView;
    }

    /*
    public void restoreMainMenu() // event
    {
        this.toggleButtons(); // make interactive again
        this.namePromptView.setVisibl(false);
        // same w/ rest of views...
    }
    */
}