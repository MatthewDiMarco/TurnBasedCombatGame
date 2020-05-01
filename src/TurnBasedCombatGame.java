import view.*;
import controller.*;
import model.characters.GameCharacter;
import model.items.*;
import javax.swing.SwingUtilities;

/**
 * [description] ... [credit]
 * Sources: build.xml and initial swing code from prac x etc.
 * @author Matthew Di Marco
 */
public class TurnBasedCombatGame
{
    // Player Default Attributes
    public static final int MAX_HEALTH = 30;
    public static final int GOLD = 100;

    public static void main(String[] args)
    {
        // Initialise Player
        CharacterInventory inventory = new CharacterInventory();

        try
        {

            inventory.addItem(
                new WeaponItem(
                    "Short Sword", 12,
                    2, 4, "Slashing", "Blade"
                )
            );
            inventory.addItem(
                new DefenceItem(
                    "Chest Plate", 20,
                    3, 5, "Iron"
                )
            );

            //TEMP

            inventory.addItem(
                new WeaponItem(
                    "Long Sword", 15,
                    20, 35, "Pierceing", "Blade"
                )
            );
            inventory.addItem(
                new DefenceItem(
                    "Helm", 5,
                    1, 3, "Iron"
                )
            );
            inventory.addItem(
                new HealingPotion(
                    "Medicine", 2,
                    4, 8
                )
            );
            inventory.addItem(
                new DamagePotion(
                    "Wild Fire", 35,
                    20, 30
                )
            );
        }
        catch (InventoryException e)
        {
            System.out.println(e.getMessage());
        }

        // END TEMP

        GameCharacter player = new GameCharacter(
            "No Name", GOLD, MAX_HEALTH, 
            0, 0, 0, 0, 
            inventory
        );

        try
        {
            inventory.getItem(0).interactWith(player);
            inventory.getItem(1).interactWith(player);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        MainController controller = new MainController(player);
        ShopController shopCon = new ShopController(new Inventory());
        shopCon.loadShop("resources/shopdata.csv");
        PlayerController playerCon = new PlayerController(controller);
        BattleController battleCon = new BattleController(new Dice(), new EnemyFactory());

        // Views
        ViewShop shopView = new ViewShop(shopCon, playerCon);
        shopView.init();
        ViewEquip equipView = new ViewEquip(playerCon);
        equipView.init();
        ViewNamePrompt nameView = new ViewNamePrompt(playerCon);
        nameView.init();
        ViewBattle battleView = new ViewBattle(battleCon, playerCon);
        battleView.init();

        // Run
        SwingUtilities.invokeLater(
            new Runnable()
            {
                public void run()
                {
                    Window game = new Window(controller);
                    game.start(shopView, equipView, nameView, battleView);
                    game.setVisible(true);
                }
            }
        );

        /*

        // Initialise Player
        CharacterInventory inventory = new CharacterInventory();

        // todo: pass inventory to shop to get the cheap items

        inventory.addItem(
            new WeaponItem(
                "Short Sword", 12,
                2, 4, "Slashing", "Blade"
            )
        );
        inventory.addItem(
            new DefenceItem(
                "Chest Plate", 20,
                3, 5, "Iron"
            )
        );

        GameCharacter player = new GameCharacter(
            "No Name", GOLD, MAX_HEALTH, 
            0, 0, 0, 0, 
            inventory
        );

        inventory.equip(0);
        inventory.equip(1);

        // Create Controllers
        PlayerController playerController = new PlayerController(player, inventory);
        BattleController battleController = new BattleController(new Dice(), new EnemyFactory());
        ShopController shopController = new ShopController(new Inventory());
        shopController.loadShop("resources/shopdata.csv");

        // Create Views
        NamePromptView namePromptView = new NamePromptView(playerController);
        InventoryView inventoryView = new InventoryView(playerController);
        BattleView battleView = new BattleView(battleController, playerController, inventoryView);
        ShopView shopView = new ShopView(shopController, playerController);

        // Run Main Menu
        SwingUtilities.invokeLater(
            new Runnable()
            {
                public void run()
                {
                    MainView game = new MainView(
                        playerController,
                        namePromptView,
                        inventoryView,
                        shopView,
                        battleView,
                        battleController
                    );

                    game.setVisible(true);
                }
            }
        );

        */




        /*
        CharacterInventory inv1 = new CharacterInventory();
        CharacterInventory inv2 = new CharacterInventory();
        GameCharacter player1 = new GameCharacter("John", GOLD, MAX_HEALTH, inv1);
        GameCharacter player2 = new GameCharacter("Sam", GOLD, MAX_HEALTH, inv2);

        System.out.println(player1.toString() + "\nVS");
        System.out.println(player2.toString() + "\n");

        DamageItem weapon = new WeaponItem(
            "Short Sword", 12,
            2, 4, "Slashing", "Blade"
        );

        DefenceItem armour = new DefenceItem(
            "Chest Plate", 20,
            3, 5, "Iron"
        );

        ConsumableItem potion = new ConsumableItem(
            "Potion of Healing", 5,
            5, 8, 'H'
        );

        System.out.println(weapon.toString());
        System.out.println(armour.toString());
        System.out.println(potion.toString());

        inv1.addItem(weapon);
        inv1.addItem(armour);
        inv1.addItem(potion);
        inv1.equipWeapon(0);
        inv1.equipArmour(1);
        inv2.addItem(weapon);
        inv2.addItem(armour);
        inv2.addItem(potion);
        inv2.equipWeapon(0);
        inv2.equipArmour(1);

        // Battle
        int oldHP;
        int dmg;
        while (!player1.isDead() && !player2.isDead())
        {
            System.out.println(player1.getName() + ", " + 
                               player1.getHealth() + "/" + player1.getMaxHealth() + 
                               "\n" + 
                               player2.getName() + ", " + 
                               player2.getHealth() + "/" + player2.getMaxHealth() + "\n");

            System.out.println(player1.getName() + " Attacks: " + (dmg = player1.attack()));        
            oldHP = player2.getHealth();
            player2.defend(dmg);
            System.out.println(player2.getName() + " -" + (oldHP - player2.getHealth()) + " Health Points\n");

            System.out.println(player2.getName() + " Attacks: " + (dmg = player2.attack()));        
            oldHP = player1.getHealth();
            player1.defend(dmg);
            System.out.println(player1.getName() + " -" + (oldHP - player1.getHealth()) + " Health Points\n");
        }

        String name = player1.getName();
        if (player1.isDead())
        {
            name = player2.getName();
        }
        System.out.println(name + " WINS!");
        */
    }
}