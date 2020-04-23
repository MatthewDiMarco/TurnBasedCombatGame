package view.options;
import view.*;

public class OptionGoShop implements Option
{
    public void doOption(MainView view)
    {
        view.setVisibility(false);
        view.getShopView().setVisible(true);
    }
}