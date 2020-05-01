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
            character.getInventory().removeItem(this);
            character.setHealth(character.getHealth() + this.getEffect(new Dice()));
        }
        catch (InventoryException e)
        {
            throw new ItemInteractionException(e.getMessage());
        }
    }
}