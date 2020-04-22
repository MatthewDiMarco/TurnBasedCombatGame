package model.items;
import java.util.*;

public abstract class Enchantment extends DamageItem
{
    protected Item next;

    public Enchantment(DamageItem inNext, String inName, int inCost, 
                       int inMinEff, int inMaxEff, 
                       String inDmgType)
    {
        super(inName, inCost, inMinEff, inMaxEff, inDmgType);
        next = inNext;
    }

    @Override
    public int getEffect(Random randGenerator)
    {
        return next.getEffect(randGenerator);
    }   
    
    @Override
    public int getCost()
    {
        return next.getCost() + super.getCost();
    }

    @Override
    public String toString()
    {
        return next.toString() + ", " + this.getName() + " ECH";
    } 
}