import view.*;
import controller.*;
import model.characters.GameCharacter;
import model.items.*;
import javax.swing.SwingUtilities;

/**
 * [description] ... [credit]
 * Sources: build.xml and initial swing code from prac x etc.
 * @author Matthew Di Marco
 */
public class TurnBasedCombatGame
{
    public static void main(String[] args)
    {
        try
        {
            // Initialise Shop
            Factory factory = new Factory();
            Inventory shop = new Inventory();
            ShopController shopCon = new ShopController(factory, shop);
            shopCon.loadShop("resources/shopdata.csv"); 

            // Initialise Player
            GameCharacter player = factory.makePlayer(shop);

            // Initialise Controllers
            MainController controller = new MainController(player);
            PlayerController playerCon = new PlayerController(controller);
            BattleController battleCon = new BattleController(new Dice(), factory, player, controller);

            // Initialise Views
            ViewShop shopView = new ViewShop(shopCon, playerCon);
            shopView.init();
            ViewEquip equipView = new ViewEquip(playerCon);
            equipView.init();
            ViewNamePrompt nameView = new ViewNamePrompt(playerCon);
            nameView.init();
            ViewBattle battleView = new ViewBattle(battleCon, playerCon);
            battleView.init();

            // Run
            SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        Window game = new Window(controller);
                        controller.setView(game);
                        game.start(shopView, equipView, nameView, battleView);
                        game.setVisible(true);
                    }
                }
            );
        }
        catch (ShopLoaderException e)
        {   
            System.out.println("Error loading shop: " + e.getMessage());
        }
    }
}