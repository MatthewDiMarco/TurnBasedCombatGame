package model.items;

public abstract class EquipItem extends Item
{
    private EquipType equipType;

    public enum EquipType
    {
        ARMOUR, WEAPON
    }

    public EquipItem(String inName, int inCost, 
                     int inMin, int inMax, EquipType inType)
    {
        super(inName, inCost, inMin, inMax);
        equipType = inType;   
    }

    public EquipType getEquipType()
    {
        return equipType;
    }
}