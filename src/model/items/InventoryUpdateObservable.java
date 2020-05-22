package model.items;

/**
 * Inventory Update Observers are notified when the Inventory changes.
 */
public interface InventoryUpdateObservable 
{
    public void updateInventory(CharacterInventory inventory);
}