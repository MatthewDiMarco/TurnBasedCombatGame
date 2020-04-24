package controller;
import java.util.*;

public class ShopController 
{
    public ShopController()
    {
        //
    }

    public Vector<String> getShopItems()
    {
        //temp
        Vector<String> items = new Vector<String>();
        items.add("1");
        items.add("2");
        items.add("3");

        return items;
    }

    public Vector<String> getEnchantments()
    {
        //temp
        Vector<String> enchantments = new Vector<String>();
        enchantments.add("+2");
        enchantments.add("+5");
        enchantments.add("fire or some bullshit");

        return enchantments;
    }
}