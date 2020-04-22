import model.characters.*;
import model.items.*;
import java.util.*;

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

        inv.addWeapon(weapon);
        inv.addArmour(armour);
        inv.equipWeapon(0);
        inv.equipArmour(0);

        EnemyCharacter enemy = new SlimeEnemy();
        for (int ii = 0; ii < 10; ii++)
        {
            System.out.println(player.toString() + "\n" + enemy.toString() + "\n");

            int dmg;
            System.out.println("Player Attacks: " + (dmg = player.attack()));        
            enemy.defend(dmg);
            System.out.println("Enemy Attacks: " + (dmg = enemy.attack()));        
            player.defend(dmg);
        }
    }
}