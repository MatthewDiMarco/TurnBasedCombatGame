package controller;
import model.items.*;
import java.io.*;

public class CSVFileLoader implements ShopLoader
{
    /**
     * Append items in the csv file to an existing shop.
     */
    @Override
    public Inventory load(Inventory shop, String fileName) throws ShopLoaderException
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line, tokens[];
        int lineNum;

        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            //Check file emptiness
            line = bufRdr.readLine();
            if(line == null)
            {
                throw new ShopLoaderException("'" + fileName + "' is empty");
            }

            //Start reading
            boolean valid = true;
            lineNum = 0;
            while (line != null)
            {
                lineNum++;
                
                // Process line
                try
                {
                    Item item; 
                    tokens = line.split(",");
                    String type = tokens[0];

                    // Try process the Item
                    if (type.equals("W"))
                    {
                        item = processWeapon(tokens);
                    }
                    else if (type.equals("A"))
                    {
                        item = processArmour(tokens); 
                    }
                    else if (type.equals("P"))
                    {
                        item = processPotion(tokens); 
                    }  
                    else // Type invalid
                    {
                        throw new IllegalArgumentException(
                            "Item type is undefined or does not exist. " +
                            "Please choose: W, A, or P"
                        );
                    }     
                    
                    // If no errors were thrown, then add the item
                    shop.addItem(item);
                }
                catch (IllegalArgumentException | InventoryException e)
                {
                    valid = false;
                    throw new ShopLoaderException(
                        "Problem at line " + lineNum +
                        ": " + e.getMessage()
                    );
                }

                // Next Item
                line = bufRdr.readLine();
            }

            // Retrun null if any number of errors are found
            if (!valid)
            {
                shop = null;
            }
        }
        catch (IOException e)
        {
            shop = null;
            throw new ShopLoaderException(
                "There was an error processing '" + fileName + "'\n" +
                "Failed to load shop"
            );
        }
        finally
        {
            // Clean up
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2) {}
            }
        }

        return shop;
    }

    /**
     * Build and return a WeaponItem given the line arguments
     */
    private static WeaponItem processWeapon(String[] args)
    {
        WeaponItem item;

        // Check all the arguments are there
        if (args.length < 7)
        {
            throw new IllegalArgumentException(
                "Weapon Item is missing arguments: " +
                "A,name,minDmg,maxDmg,cost,dmgType,weaponType"
            );
        }

        // Try constructing a weapon
        try
        {
            item = new WeaponItem(
                args[1],                    // name
                Integer.parseInt(args[4]),  // cost 
                Integer.parseInt(args[2]),  // min dmg
                Integer.parseInt(args[3]),  // max dmg
                args[5],                    // dmg type
                args[6]                     // wpn type
            );
        }
        catch (IllegalArgumentException e) //todo: will catch custom ones here
        {
            throw new IllegalArgumentException(e.getMessage());
        }

        return item;
    }

    /**
     * Build and return a DefenceItem given the line arguments
     */
    private static DefenceItem processArmour(String[] args)
    {
        DefenceItem item;

        // Check all the arguments are there
        if (args.length < 6)
        {
            throw new IllegalArgumentException(
                "Armour Item is missing arguments: " +
                "A,name,minDef,maxDef,cost,material"
            );
        }

        // Try constructing Armour
        try
        {
            item = new DefenceItem(
                args[1],                    // name
                Integer.parseInt(args[4]),  // cost 
                Integer.parseInt(args[2]),  // min def
                Integer.parseInt(args[3]),  // max def
                args[5]                     // material
            );
        }
        catch (IllegalArgumentException e) //todo: will catch custom ones here
        {
            throw new IllegalArgumentException(e.getMessage());
        }

        return item;
    }

    /**
     * Build and return a PotionItem given the line arguments
     */
    private static Item processPotion(String[] args)
    {
        Item item;

        // Check all the arguments are there
        if (args.length < 6)
        {
            throw new IllegalArgumentException(
                "Potion Item is missing arguments: " +
                "P,name,minEffect,maxEffect,cost,H or D (for healing/damage)"
            );
        }

        // Try constructing a Potion
        try
        {
            switch (args[5].toCharArray()[0])
            {
                case 'H':
                    item = new HealingPotion(
                        args[1],                    // name
                        Integer.parseInt(args[4]),  // cost 
                        Integer.parseInt(args[2]),  // min eff
                        Integer.parseInt(args[3])   // max eff
                    );
                    break;
                case 'D':
                    item = new DamagePotion(
                        args[1],                    // name
                        Integer.parseInt(args[4]),  // cost 
                        Integer.parseInt(args[2]),  // min eff
                        Integer.parseInt(args[3])   // max eff
                    );
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Must specify H or D for potion"
                    );
            }
        }
        catch (IllegalArgumentException e) //todo: will catch custom ones here
        {
            throw new IllegalArgumentException(e.getMessage());
        }

        return item;
    }
}