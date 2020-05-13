package model.items;
import model.characters.GameCharacter;

/**
 * Healing
 */
public class HealingPotion extends Item
{
    /**
     * Constructor.
     * @param inName The Potion's name
     * @param inCost The Potion's cost
     * @param inMinEff Minimum effect
     * @param inMinEff Maximum effect
     */
    public HealingPotion(String inName, int inCost, 
                          int inMinEff, int inMaxEff)
    {
        super(inName, inCost, inMinEff, inMaxEff);
    }

    @Override
    public void interactWith(GameCharacter character) throws ItemInteractionException
    {
        try
        {
            if (!character.isFighting())
            {
                throw new ItemInteractionException("Potions must be used in battle");
            }
            else
            {
                character.getInventory().setHealingPotion(this);
                character.getInventory().removeItem(this);
            }
        }
        catch (InventoryException e)
        {
            throw new ItemInteractionException(e.getMessage());
        }
    }
}