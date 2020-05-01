package controller;
import model.characters.CharacterException;
import model.characters.GameCharacter;
import model.items.GameStateException;
import model.items.Inventory;
import model.items.InventoryException;
import model.items.Item;
import java.util.*;

public class ShopController 
{
    private Inventory shopItems;
    private Vector<String> enchantments;

    public ShopController(Inventory inShopItems)
    {
        shopItems = inShopItems;
        enchantments = new Vector<String>();
        enchantments.add("5G  Damage +2");
        enchantments.add("10G  Damage +5");
        enchantments.add("20G  Fire Damage");
        enchantments.add("10G  Power-Up");
    }

    public void loadShop(String fileName)
    {
        ShopLoader loader = ShopFactory.makeShopLoader(fileName);
        loader.load(shopItems, fileName);
    }

    public List<Item> getShopItems()
    {
        return shopItems.getItems();
    }

    public Vector<String> getEnchantments()
    {
        return enchantments;
    }

    public void sellItem(GameCharacter character, int index) throws GameStateException
    {
        Item item = character.getInventory().getItem(index);
        try
        {
            character.getInventory().removeItem(index);
            character.setGold(character.getGold() + ((int)(item.getCost() / 2)));
        }
        catch (InventoryException e)
        {
            throw new GameStateException(e.getMessage());
        }
    }

    public void buyItem(GameCharacter character, int index) throws GameStateException
    {
        int oldGold = character.getGold();
        try
        {
            // Get the desired item
            Item item = shopItems.getItem(index);

            // Try pay up 
            character.setGold(character.getGold() - item.getCost());

            // Try add to player's inventory
            character.getInventory().addItem(item);
        }
        catch (InventoryException e)
        {
            character.setGold(oldGold); // Refund
            throw new GameStateException(e.getMessage());
        }
        catch (CharacterException e)
        {
            throw new GameStateException(e.getMessage());
        }
    }
}