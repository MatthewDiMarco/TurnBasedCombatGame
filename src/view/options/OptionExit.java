package view.options;
import view.*;

public class OptionExit implements Option
{
    public void doOption(MainView view)
    {
        view.closeAll();
    }
}