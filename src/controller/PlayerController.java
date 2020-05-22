package controller;
import model.characters.CharacterException;
import model.characters.GameCharacter;
import model.items.*;

/**
 * Responsible for manipulating the player model
 */
public class PlayerController 
{
    MainController controller;

    public PlayerController(MainController inController)
    {
        controller = inController;
    }

    public GameCharacter getPlayer()
    {
        return controller.getPlayer();
    }

    public void changePlayerName(String newName) throws GameStateException
    {
        try
        {
            controller.getPlayer().setName(newName);
        }
        catch (CharacterException e)
        {
            throw new GameStateException(e.getMessage());
        }
    }

    public void interact(int index) throws GameStateException
    {
        Item item = controller.getPlayer().getInventory().getItem(index);
        try
        {
            item.interactWith(controller.getPlayer());
        }
        catch (ItemInteractionException e)
        {
            throw new GameStateException(e.getMessage());
        }
    }
}