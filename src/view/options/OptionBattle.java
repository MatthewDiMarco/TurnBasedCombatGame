package view.options;
import view.*;

public class OptionBattle implements Option
{
    public void doOption(MainView view)
    {
        view.setVisibility(false);
        view.getBattleView().setVisible(true);
    }
}