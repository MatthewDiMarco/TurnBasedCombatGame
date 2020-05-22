package controller;
import model.characters.CharacterException;
import model.characters.GameCharacter;
import model.items.GameStateException;
import model.items.Inventory;
import model.items.InventoryException;
import model.items.Item;
import model.items.DamageItem;
import java.util.*;

/**
 * Responsible for storing shop data and facilitating shop/player interactions.
 */
public class ShopController 
{
    private Factory factory;
    private Inventory shopItems;
    private HashMap<Integer, String> enchantments;

    public ShopController(Factory inFactory, Inventory inShop)
    {
        factory = inFactory;
        shopItems = inShop;
        enchantments = new HashMap<Integer, String>();

        // Format: gold:name:effect
        enchantments.put(0, "5:DAMAGE:+2");
        enchantments.put(1, "10:DAMAGE:+5");
        enchantments.put(2, "20:FIRE DAMAGE:+5-10");
        enchantments.put(3, "10:POWER-UP:x1.1");
    }

    public void loadShop(String source) throws ShopLoaderException
    {
        ShopLoader loader = factory.makeShopLoader(source);
        if (loader == null)
        {
            throw new ShopLoaderException(
                "The shop source '" + source + "' could not be loaded"
            );
        }
        else
        {
            loader.load(shopItems, source);
        }
    }

    public List<Item> getShopItems()
    {
        return shopItems.getItems();
    }

    public Vector<String> getEnchantments()
    {
        Vector<String> list = new Vector<String>();

        for (String s : enchantments.values())
        {
            String tokens[] = s.split(":");
            String cost = tokens[0];
            String name = tokens[1];
            String eff = tokens[2];

            list.add(cost + " G   " + name + "   " + eff);
        }

        return list;
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
            throw new GameStateException(
                "You don't have enough gold to make this purchase"
            );
        }
    }

    public void buyEnchantment(GameCharacter character, int index) throws GameStateException
    {
        int oldGold = character.getGold();
        int echCost;
        try
        {
            echCost = Integer.parseInt(enchantments.get(index).split(":")[0]);
        }
        catch (NumberFormatException e)
        {
            throw new GameStateException(e.getMessage());
        }
     
        // Try making purchase
        try
        {
            character.setGold(character.getGold() - echCost);

            DamageItem oldW = character.getInventory().getWeapon();
            DamageItem newW = factory.addEnchantment(oldW, enchantments.get(index));    

            character.getInventory().addItem(newW);
            character.setWeapon(newW);
            character.getInventory().removeItem(oldW);
        }
        catch (InventoryException e) 
        {
            character.setGold(oldGold); // Refund
            throw new GameStateException(e.getMessage());
        }
        catch (CharacterException e)
        {
            throw new GameStateException(
                "You don't have enough gold to make this purchase"
            );
        }
    }
}