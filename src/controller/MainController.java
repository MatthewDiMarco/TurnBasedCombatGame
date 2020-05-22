package controller;
import model.characters.GameCharacter;
import view.Window;

/**
 * This class controls the views and main game window.
 */
public class MainController 
{
    GameCharacter player;
    Window view;

    public MainController(GameCharacter inPlayer)
    {
        player = inPlayer;
        view = null;
    }

    public GameCharacter getPlayer()
    {
        return player;
    }

    public void setView(Window inView)
    {
        view = inView;
    }

    public void setMenuButtons(Boolean b)
    {
        view.setButtons(b);
    }

    public void gameOver()
    {
        view.dispose();
    }

    public void playerVictory()
    {
        view.setView("SHOP");
        view.setButtons(true);
    }
}