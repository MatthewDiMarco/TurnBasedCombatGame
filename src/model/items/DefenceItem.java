package model.items;

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

    /**
     * A simple string representation of the Armour
     * @return A String containing the attributes
     */
    @Override
    public String toString()
    {
        return super.toString() + ", " + 
               minEffect + "-" + maxEffect + " Defence, " +
               "made from " + material + ", COST: " + getCost() + "G";
    }
}