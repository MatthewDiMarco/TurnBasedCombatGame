package model.items;
import model.characters.CharacterException;
import model.characters.GameCharacter;

/**
 * Healing
 */
public class DamagePotion extends Item
{
    /**
     * Constructor.
     * @param inName The Potion's name
     * @param inCost The Potion's cost
     * @param inMinEff Minimum effect
     * @param inMinEff Maximum effect
     */
    public DamagePotion(String inName, int inCost, 
                          int inMinEff, int inMaxEff)
    {
        super(inName, inCost, inMinEff, inMaxEff);
    }

    @Override
    public void interactWith(GameCharacter character) throws ItemInteractionException
    {
        try
        {
            character.setPotion(this);
            character.getInventory().removeItem(this);
        }
        catch (CharacterException | InventoryException e)
        {
            throw new ItemInteractionException(e.getMessage());
        }
    }
}