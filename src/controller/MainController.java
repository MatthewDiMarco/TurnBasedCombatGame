package controller;
import model.characters.GameCharacter;

public class MainController 
{
    GameCharacter player;

    public MainController(GameCharacter inPlayer)
    {
        player = inPlayer;
    }

    public GameCharacter getPlayer()
    {
        return player;
    }
}