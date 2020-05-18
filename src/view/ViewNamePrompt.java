package view;
import controller.PlayerController;
import model.items.GameStateException;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class ViewNamePrompt extends ViewPanel
{
    public static final int PADDING = Window.PADDING;

    // Widgets
    private JTextField nameWidget;
    private JButton applyBtn;

    // Controllers
    private PlayerController playerCon;

    /**
     * Constructor
     * @param inPlayerCon
     */
    public ViewNamePrompt(PlayerController inPlayerCon)
    {
        super(new FlowLayout(), "NAME CHARACTER", Window.PADDING);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        playerCon = inPlayerCon;

        // Initialise Widgets
        nameWidget = new JTextField("Enter name here"); // record new name here
        applyBtn = new JButton("Apply");
    }

    public void init()
    {
        JPanel masterPane = new JPanel(new GridLayout(20, 1));

        // Button pane
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(applyBtn);

        // Bring it all together
        masterPane.add(toolbar);
        masterPane.add(nameWidget);

        this.add(Box.createRigidArea(new Dimension(PADDING, PADDING)));
        this.add(masterPane);
        
        //
        // ACTION LISTENERS
        //

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
    }
}