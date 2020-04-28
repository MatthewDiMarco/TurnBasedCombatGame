package controller;
import model.items.Inventory;
import model.items.Item;
import model.items.Enchantment;
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

    public void sellItem()
    {
        //check: selling item is not currently equiped
    }
}