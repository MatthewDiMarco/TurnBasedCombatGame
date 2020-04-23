package view.options;
import view.*;

public class OptionInventory implements Option
{
    public void doOption(MainView view)
    {
        view.setVisibility(false);
        view.getInventoryView().inBattle(false);
        view.getInventoryView().setVisible(true);
    }
}