package controller;
import model.characters.*;
import model.items.CharacterInventory;
import model.items.Dice;

public class EnemyFactory 
{
    // Enemy spawn probabilities
    private double goblinProb;
    private double ogreProb;
    private double dragonProb;

    /**
     * Constructor.
     */
    public EnemyFactory()
    {
        goblinProb  = 0.30;
        ogreProb    = 0.20;
        dragonProb  = 00;
        // what's left = slime prob
    }

    public EnemyCharacter makeEnemy(Dice dice)
    {
        EnemyCharacter enemy = null;
        double result = dice.roll(1, 100);
        result = result / 100;

        double currProb = dragonProb;
        if (result < currProb)
        {
            enemy = new DragonEnemy(new CharacterInventory());
        }
        else if (result < (currProb += ogreProb))
        {
            enemy = new OgreEnemy(new CharacterInventory());
        }
        else if (result < (currProb += goblinProb))
        {
            enemy = new GoblinEnemy(new CharacterInventory());
        }
        else
        {
            enemy = new SlimeEnemy(new CharacterInventory());
        }

        return enemy;
    }

    public void mutateProbabilities()
    {
        if (dragonProb < 1)
        {
            dragonProb += 0.15;

            if (ogreProb != 0)
            {
                ogreProb -= 0.05;
            }

            if (goblinProb != 0)
            {
                ogreProb -= 0.05;
            }
        }
    }
}