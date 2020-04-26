package controller;
import model.items.Inventory;

public interface ShopLoader 
{
    public Inventory load(String fileName);
}