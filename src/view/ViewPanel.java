package view;
import javax.swing.*;
import java.awt.LayoutManager;

public class ViewPanel extends JPanel
{
    public static final int PADDING = Window.PADDING;

    public ViewPanel(LayoutManager layout, String inTitle, int borderPad)
    {
        super(layout);
        if (inTitle.isEmpty())
        {
            this.setBorder(
                BorderFactory.createEmptyBorder(
                    borderPad, borderPad, borderPad, borderPad
                )
            );
        }
        else
        {
            this.setBorder(
                BorderFactory.createTitledBorder(
                    BorderFactory.createEmptyBorder(
                        borderPad, borderPad, borderPad, borderPad
                    ), inTitle
                )
            );
        }
    }
}