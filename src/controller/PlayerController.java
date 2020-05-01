package controller;
import model.characters.GameCharacter;
import model.items.*;

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

    public void changePlayerName(String newName)
    {
        controller.getPlayer().setName(newName);
    }

    public void equipItem(int index)
    {
        //
    }

    public void sellItem(int index)
    {
        //
    }

    public void prepPotion(int index)
    {
        //
    }
}