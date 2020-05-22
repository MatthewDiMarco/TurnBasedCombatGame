package model.items;
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

    /**
     * Activates the potion returning the generated damage value, and removing
     * this item from the player's inventory.
     * @param character
     */
    @Override
    public int interactWith(GameCharacter character) throws ItemInteractionException
    {
        int dmg;
        try
        {
            if (!character.isFighting())
            {
                throw new ItemInteractionException("Potions must be used in battle");
            }
            else 
            {
                dmg = this.getEffect(new Dice()); //temp - remove new!
                character.getInventory().removeItem(this);
                character.notifyActionObservers(character.getName() + " USED " +
                                                this.name + " (+" + dmg + 
                                                " DAMAGE)");
            }
        }
        catch (InventoryException e)
        {
            throw new ItemInteractionException(e.getMessage());
        }

        return dmg;
    }
}