package model.items;
import model.characters.GameCharacter;

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

    /**
     * Heals the character and removes the potion from their inventory
     * @param character
     */
    @Override
    public int interactWith(GameCharacter character) throws ItemInteractionException
    {
        try
        {
            if (!character.isFighting())
            {
                throw new ItemInteractionException("Potions must be used in battle");
            }
            else
            {
                character.getInventory().removeItem(this);

                int healing = this.getEffect(new Dice());
                character.setHealth(character.getHealth() + healing);
                character.notifyActionObservers(character.getName() + " USED " +
                                                this.name + " (+" + healing + 
                                                " HEALTH)");
            }
        }
        catch (InventoryException e)
        {
            throw new ItemInteractionException(e.getMessage());
        }

        return 0;
    }
}