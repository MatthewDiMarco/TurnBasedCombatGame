package view.options;
import view.*;

public class OptionChangeName implements Option
{
    public void doOption(MainView view)
    {
        view.setVisibility(false);
        view.getNamePrompt().setVisible(true);
    }
}