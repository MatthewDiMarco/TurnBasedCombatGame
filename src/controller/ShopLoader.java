package controller;
import model.items.Inventory;

/**
 * Classes implementing this interface provide their own startegy for "loading"
 * some shop data (e.g. file, database, web, etc)
 */
public interface ShopLoader 
{
    public Inventory load(Inventory shop, String fileName) throws ShopLoaderException;
}