package controller;
import model.items.Inventory;
import model.items.Item;
import model.items.WeaponItem;
import model.items.DefenceItem;
import model.items.ConsumableItem;
import java.io.*;

public class CSVFileLoader implements ShopLoader
{
    @Override
    public Inventory load(String fileName)
    {
        Inventory shop = new Inventory();
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
                throw new IOException("'" + fileName + "' is empty");
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
                catch (IllegalArgumentException e)
                {
                    valid = false;
                    System.out.println("Error at line " + lineNum +
                                       ": " + e.getMessage());
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
            System.out.println("There was an error processing '" + fileName + 
                               "'\nFailed to load shop");
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
                catch(IOException ex2) {} //Can't do much
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

        // Try constructing a weapon
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
    private static ConsumableItem processPotion(String[] args)
    {
        ConsumableItem item;

        // Check all the arguments are there
        if (args.length < 6)
        {
            throw new IllegalArgumentException(
                "Potion Item is missing arguments: " +
                "P,name,minEffect,maxEffect,cost,H or D (for healing/damage)"
            );
        }

        // Try constructing a weapon
        try
        {
            item = new ConsumableItem(
                args[1],                    // name
                Integer.parseInt(args[4]),  // cost 
                Integer.parseInt(args[2]),  // min eff
                Integer.parseInt(args[3]),  // max eff
                args[5].toCharArray()[0]    // healing/damage type
            );
        }
        catch (IllegalArgumentException e) //todo: will catch custom ones here
        {
            throw new IllegalArgumentException(e.getMessage());
        }

        return item;
    }
}