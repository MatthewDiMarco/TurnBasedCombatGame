package controller;
import model.characters.*;
import model.items.*;
import java.util.*;

/**
 * This class is responsible for constructing game entities (e.g. Characters, 
 * Enchantments, etc).
 */
public class Factory
{
    // Enemy spawn probabilities
    private double goblinProb;
    private double ogreProb;
    private double dragonProb;

    public Factory()
    {
        goblinProb  = 0.30;
        ogreProb    = 0.20;
        dragonProb  = 0.0;
        // remainder = slime prob
    }

    /**
     * This function adds enchantments to weapons.
     * @param weapon The weapon to enchant
     * @param type The enchantments descirption: "gold:name:effect", e.g. "5:DAMAGE:+2"
     * @return The enchanted weapon
     */
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

    /**
     * Constructs the player, starting them off with the cheapest weapon and
     * armour from the shop.
     * @param shop The shop
     * @return The player
     */
    public GameCharacter makePlayer(Inventory shop)
    {
        final int HEALTH = 30;
        final int GOLD = 100;

        CharacterInventory inventory = new CharacterInventory();

        // Calc cheapest shop weapon & armour
        List<Item> items = shop.getItems();
        DamageItem cWeapon = null;
        DefenceItem cArmour = null;

        int ii = 0;
        while ((cWeapon == null || cArmour == null) && ii < items.size())
        {
            try
                {
                    cWeapon = (DamageItem)items.get(ii);
                }
                catch (ClassCastException e) {}

                try
                {
                    cArmour = (DefenceItem)items.get(ii);
                }
                catch (ClassCastException e) {}

            ii++;
        }

        for (Item i : items)
        {
            if (i.getCost() < cWeapon.getCost() || 
                i.getCost() < cArmour.getCost())
            {
                try
                {
                    cWeapon = (DamageItem)i;
                }
                catch (ClassCastException e) {}

                try
                {
                    cArmour = (DefenceItem)i;
                }
                catch (ClassCastException e) {}
            } 
        }

        // Equip
        try
        {
            inventory.addItem(cWeapon);
            inventory.addItem(cArmour);
            inventory.setWeapon(cWeapon);
            inventory.setArmour(cArmour);
        }
        catch (InventoryException e) {}

        // Create the player
        GameCharacter player = new GameCharacter(
            "No Name", GOLD, HEALTH, 
            0, 0, 0, 0, 
            inventory
        );

        return player; 
    }

    /**
     * Construct a shop loader appropriate for the provided source.
     * @param source Data location (e.g. "*.csv" or "www.*"" etc)
     * @return The ShopLoader
     */
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

    /**
     * Constructs an enemy object, the subclass of which is determined by the
     * random probabilities.
     * @param dice Used for random number generation
     * @return The enemy
     */
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

    /**
     * Alter the probabilities like so: any non-dragons are decreased by 5% and
     * the dragon is increased accordingly (15%). Non-dragons may not drop below
     * 5%, and the dragon's max is 85%
     */
    public void mutateProbabilities()
    {
        if (dragonProb < 0.85)
        {
            double sum = 0.05;
            if ((ogreProb - 0.05) > 0.05)
            {
                ogreProb -= 0.05;
                sum += 0.05;
            }

            if ((goblinProb - 0.05) > 0.05)
            {
                goblinProb -= 0.05;
                sum += 0.05;
            }

            dragonProb += sum;
        }
    }
}