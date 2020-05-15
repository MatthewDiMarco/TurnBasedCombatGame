package controller;
import model.characters.*;
import model.items.*;

public class Factory
{
    // Enemy spawn probabilities
    private double goblinProb;
    private double ogreProb;
    private double dragonProb;

    /**
     * Constructor.
     */
    public Factory()
    {
        goblinProb  = 0.30;
        ogreProb    = 0.20;
        dragonProb  = 0.0;
        // remainder = slime prob
    }

    public DamageItem addEnchantment(DamageItem weapon, String type)
    {
        DamageItem enchantedWeapon = null;

        String tokens[] = type.split(":");
        String name = tokens[1].toUpperCase();
        int cost = 0; //todo: throw exception instead
        try
        {
            cost = Integer.parseInt(tokens[0]);
        }
        catch (NumberFormatException e) {}

        if (name.startsWith("DAMAGE")) //e.g. DAMAGE+5
        {   
            try
            {
                int add = Integer.parseInt(tokens[2]);
                enchantedWeapon = new EnchantmentAddDamage(weapon, add, cost);
            }
            catch (NumberFormatException e) {}
        }
        else if (name.startsWith("FIRE")) //e.g. FIRE+1-2
        {
            try
            {
                int min = Integer.parseInt(tokens[2].split("\\-")[0]);
                int max = Integer.parseInt(tokens[2].split("\\-")[1]);
                enchantedWeapon = new EnchantmentFireDamage(weapon, min, max, cost);
            }
            catch (NumberFormatException e) {} //temp
        }
        else if (name.startsWith("POW")) //e.g. POWx1.1
        {
            try
            {
                double factor = Double.parseDouble(tokens[2].split("x")[1]);
                enchantedWeapon = new EnchantmentPowerUp(weapon, factor, cost);
            }
            catch (NumberFormatException e) {}
        }

        return enchantedWeapon;
    }

    public ShopLoader makeShopLoader(String source)
    {
        ShopLoader loader = null;
        
        // Try loading source data as a csv/txt file. 
        // If errors occur, try another type of loader (e.g. database, web etc).
        try
        {
            loader = new CSVFileLoader();
            loader.load(new Inventory(), source);
        }
        catch (ShopLoaderException e) 
        {
            loader = null;
        }

        if (loader == null)
        {
            //
            // INSERT ALTERNATE SOURCES...
            //
        }

        return loader;
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