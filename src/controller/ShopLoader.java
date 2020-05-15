package controller;
import model.items.Inventory;

public interface ShopLoader 
{
    public Inventory load(Inventory shop, String fileName) throws ShopLoaderException;
}