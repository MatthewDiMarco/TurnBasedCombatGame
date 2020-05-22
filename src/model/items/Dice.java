package model.items;
import java.util.Random;

/**
 * Used for generating random numbers.
 */
public class Dice 
{
    private Random generator;

    public Dice()
    {
        generator = new Random();
    }

    public int roll(int min, int max)
    {
        int roll = generator.nextInt(max + 1 - min) + min;
        return roll;
    }

    public void setGenerator(Random inRand)
    {
        if (inRand != null)
        {
            generator = inRand;
        }
    }
}