package view;
import javax.swing.*;
import java.awt.*;

public abstract class ViewPanel extends JPanel
{
    public static final int PADDING = Window.PADDING;

    public ViewPanel(String inTitle, int borderPad)
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

    public abstract void init();
}