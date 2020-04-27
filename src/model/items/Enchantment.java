package model.items;
import java.util.*;

public abstract class Enchantment extends DamageItem
{
    protected Item next;

    public Enchantment(DamageItem inNext, String inName, int inCost, 
                       int inMinEff, int inMaxEff)
    {
        super(inName, inCost, inMinEff, inMaxEff, inNext.getDamageType());
        next = inNext;
    }

    @Override
    public int getEffect(Random randGenerator)
    {
        return next.getEffect(randGenerator);
    } 

    @Override
    public String getEffectRange()
    {
        return this.getMin() + " - " + this.getMax() + " " + 
               dmgType + " Damage";
    }

    @Override
    protected int getMin()
    {
        return next.getMin() + minEffect;
    }

    @Override
    protected int getMax()
    {
        return next.getMax() + maxEffect;
    }
    
    @Override
    public int getCost()
    {
        return next.getCost() + super.getCost();
    }

    @Override
    public String toString()
    {
        return next.toString() + SPACING + this.name;
    } 
}