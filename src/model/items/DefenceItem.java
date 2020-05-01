package model.items;
import model.characters.CharacterException;
import model.characters.GameCharacter;

/**
 * Armour is used to defend it's host from blows.
 */
public class DefenceItem extends Item
{
    private String material;

    /**
     * Constructor.
     * @param inName The Armour's name
     * @param inCost The Armour's cost
     * @param inMinEff Minimum defence
     * @param inMinEff Maximum defence
     * @param inMat The Armour's material
     */
    public DefenceItem(String inName, int inCost, int inMinEff, int inMaxEff, 
                       String inMat)
    {
        super(inName, inCost, inMinEff, inMaxEff);
        if (inMat.isEmpty())
        {
            throw new IllegalArgumentException(
                "Must define Armour's material (e.g. leather, chain)"
            );
        }

        material = inMat;
    }

    @Override
    public String toString()
    {
        return name + " (" + material + ")";
    }

    @Override
    public String getEffectRange()
    {
        return super.getEffectRange() + " Defence";
    }

    @Override
    public void interactWith(GameCharacter character) throws ItemInteractionException
    {
        try
        {
            character.setArmour(this);
        }
        catch (CharacterException e)
        {
            throw new ItemInteractionException(e.getMessage());
        }
    }
}