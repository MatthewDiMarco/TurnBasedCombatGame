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

    public List<String> getPlayerStats()
    {
        List<String> stats = new ArrayList<String>();
        stats.add(player.getName());
        stats.add(player.getHealth() + " / " + player.getMaxHealth());
        stats.add(player.getGold() + " G");
        stats.add(inv.getCurrAttackRange());
        stats.add(inv.getCurrDefenceRange());

        return stats;
    }

    public Vector<String> getInventory()
    {
        //temp
        Vector<String> items = new Vector<String>();
        items.add("Short Sword");
        items.add("Iron thong");
        items.add("Potion of Healing");

        return items;
    }

    public void changePlayerName(String newName)
    {
        player.setName(newName);
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