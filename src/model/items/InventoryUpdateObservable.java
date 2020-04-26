package model.items;
import java.util.List;

public interface InventoryUpdateObservable 
{
    public void updateInventory(List<Item> inventory);
}