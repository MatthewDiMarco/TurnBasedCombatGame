package view;
import controller.PlayerController;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

/**
 * Prompt for changin the player's name.
 */
public class NamePromptView extends JFrame
{
    // Constants
    private static final int COLS = 30;
    public static final int WIDTH = 320;
    public static final int HEIGHT = 80;

    // Widgets
    private JTextField nameWidget;
    private JButton applyBtn;
    private JButton closeBtn;

    // Controllers
    private PlayerController playerCon;

    /**
     * Constructor.
     * @param inPlayerController
     */
    public NamePromptView(PlayerController inPlayerController)
    {
        super("Choose Character Name");

        // Basics
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Controllers
        playerCon = inPlayerController; 

        // Initialise Widgets
        nameWidget = new JTextField(COLS); // record new name here
        applyBtn = new JButton("Apply");
        closeBtn = new JButton("Close");

        //
        // LAYOUT
        //

        // Initialise master pane
        JPanel masterPane = new JPanel();
        masterPane.setLayout(new BoxLayout(masterPane, BoxLayout.Y_AXIS));
        masterPane.setBorder(
            BorderFactory.createEmptyBorder(
                MainView.PADDING, MainView.PADDING, MainView.PADDING, MainView.PADDING
            )
        );

        // Button pane
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(applyBtn);
        buttonPane.add(Box.createRigidArea(new Dimension(MainView.PADDING, 0)));
        buttonPane.add(closeBtn);

        // Bring it all together
        masterPane.add(nameWidget);
        masterPane.add(Box.createRigidArea(new Dimension(0, MainView.PADDING)));
        masterPane.add(buttonPane);
        this.add(masterPane);

        //
        // ACTION LISTENERS
        //

        NamePromptView npv = this;

        // Apply Button
        applyBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String newName = nameWidget.getText();
                    try
                    {
                        playerCon.changePlayerName(newName);
                        npv.dispose();
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

        // Close Button
        closeBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    npv.dispose();
                }
            }
        );

        // Fit window
        pack();
    }
}