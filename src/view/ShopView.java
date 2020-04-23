package view;
import controller.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

public class ShopView extends JFrame
{
    // Constants
    private static final int PADDING = 20;
    public static final int WIDTH = 480;
    public static final int HEIGHT = 240;

    // Widgets
    private JList<String> items;
    private JButton buyBtn;
    private JButton closeBtn;

    // Controllers
    private PlayerController playerCon;
    private ShopController shopCon;

    /**
     * 
     * @param inPlayerController
     * @param inShopController
     */
    public ShopView(PlayerController inPlayerController,
                    ShopController inShopController)
    {
        super("Shop");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Controllers
        playerCon = inPlayerController; 
        shopCon = inShopController; 

        // Initialise Widgets
        items = new JList<String>();
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buyBtn = new JButton("Buy");
        closeBtn = new JButton("Close");

        // Layout
        JScrollPane scrollPane = new JScrollPane(items);
        JToolBar toolbar = new JToolBar();

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(toolbar, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        toolbar.add(buyBtn);
        toolbar.addSeparator(new Dimension(PADDING, PADDING));
        toolbar.add(closeBtn);

        getRootPane().setContentPane(contentPane);

        // Buy Button
        buyBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int itemIndex = items.getSelectedIndex();
                    if (itemIndex != -1)
                    {
                        //do something
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
        showItems();

        // Fit window
        pack();
    }

    public void showItems()
    {
        items.setListData(shopCon.getItems());
    }
}