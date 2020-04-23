package view;
import controller.PlayerController;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

public class NamePromptView extends JFrame
{
    // Constants
    private static final int PADDING = 10;
    private static final int COLS = 30;
    public static final int WIDTH = 480;
    public static final int HEIGHT = 120;

    // Widgets
    private JTextField nameWidget;
    private JButton applyBtn;
    private JButton closeBtn;

    // Controllers
    private PlayerController playerCon;

    /**
     * 
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
        nameWidget = new JTextField(COLS);
        applyBtn = new JButton("Apply");
        closeBtn = new JButton("Close");

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());
        panel.add(applyBtn);
        panel.add(Box.createRigidArea(new Dimension(PADDING, 0)));
        panel.add(closeBtn);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        contentPane.add(nameWidget);
        contentPane.add(Box.createRigidArea(new Dimension(0, PADDING)));
        contentPane.add(panel);

        getRootPane().setContentPane(contentPane);

        // Apply Button
        applyBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // controller stuff
                    System.out.println("*Name Changed*");
                    JOptionPane.showMessageDialog(null, "Name Changed");
                }
            }
        );

        // Close Button
        closeBtn.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // controller stuff
                    System.out.println("*Closing*");
                    setVisible(false);
                }
            }
        );

        // Fit window
        pack();
    }
}