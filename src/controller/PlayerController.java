package controller;
import model.characters.GameCharacter;
import model.items.*;
import java.util.*;

public class PlayerController 
{
    GameCharacter player;
    CharacterInventory inv;

    public PlayerController(GameCharacter inPlayer, CharacterInventory inInventory)
    {
        player = inPlayer;
        inv = inInventory;        
    }

    public GameCharacter getPlayer()
    {
        return player;
    }

    public CharacterInventory getInventory()
    {
        return player.getInventory();
    }

    public void changePlayerName(String newName)
    {
        player.setName(newName);
        player.attack();
        player.defend(10);
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