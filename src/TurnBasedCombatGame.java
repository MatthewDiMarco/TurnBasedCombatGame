import view.*;
import controller.*;
import model.characters.PlayerCharacter;
import model.items.PlayerInventory;
import javax.swing.SwingUtilities;

/**
 * [description] ... [credit]
 * Sources: build.xml and initial swing code from prac x etc.
 *
 * @author Matthew Di Marco
 */
public class TurnBasedCombatGame
{
    public static void main(String[] args)
    {
        // Create Controllers and Enitites
        PlayerInventory inventory = new PlayerInventory();
        PlayerCharacter player = new PlayerCharacter("No Name", inventory);
        PlayerController playerController = new PlayerController(player, inventory);

        BattleController battleController = new BattleController();
        ShopController shopController = new ShopController();

        // Create Views
        PlayerView playerView = new PlayerView(playerController); 
        NamePromptView namePromptView = new NamePromptView(playerController);
        InventoryView inventoryView = new InventoryView(playerController, battleController, shopController);
        ShopView shopView = new ShopView(playerController, shopController);
        BattleView battleView = new BattleView(battleController);

        SwingUtilities.invokeLater(
            new Runnable()
            {
                public void run()
                {
                    MainView game = new MainView(
                        playerView,
                        namePromptView,
                        inventoryView,
                        shopView,
                        battleView
                    );

                    game.setVisible(true);
                    playerView.setVisible(true);
                }
            }
        );

        /*

        PlayerInventory inv = new PlayerInventory();
        GameCharacter player = new PlayerCharacter("Matt", inv);

        ArrayList<DamageItem> weapons = new ArrayList<DamageItem>();
        ArrayList<DefenceItem> armours = new ArrayList<DefenceItem>();
        ArrayList<ConsumableItem> potions = new ArrayList<ConsumableItem>();

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

        weapons.add(weapon);
        armours.add(armour);
        potions.add(potion);

        System.out.println(weapon.toString());
        System.out.println(armour.toString());
        System.out.println(potion.toString());

        // Battle
        System.out.println();

        inv.addItem(weapon);
        inv.addItem(armour);
        inv.equipWeapon(0);
        inv.equipArmour(1);

        EnemyCharacter enemy = new SlimeEnemy();
        while (!player.isDead() && !enemy.isDead())
        {
            System.out.println(player.toString() + "\n" + enemy.toString() + "\n");

            int dmg;
            System.out.println("Player Attacks: " + (dmg = player.attack()));        
            enemy.defend(dmg);
            System.out.println("Enemy Attacks: " + (dmg = enemy.attack()));        
            player.defend(dmg);
        }
        */
    }
}