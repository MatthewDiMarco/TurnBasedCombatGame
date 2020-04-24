package controller;
import model.characters.*;
import model.items.*;
import java.util.*;

public class BattleController 
{
    // Enemy Inventory's.
    // These are the inventories we'll inject into
    private CharacterInventory slimeInv;
    private CharacterInventory goblinInv;
    private CharacterInventory ogreInv;
    private CharacterInventory dragonInv;

    private EnemyCharacter opponent;

    public BattleController()
    {
        // Initialise Slime Inventory
        slimeInv = new CharacterInventory();
        WeaponItem slimeAttack = new WeaponItem(
            "Slime Attack", 1,
            SlimeEnemy.MIN_ATTACK, SlimeEnemy.MAX_ATTACK, "n/a", "n/a"
        );
        DefenceItem slimeDefence = new DefenceItem(
            "Slime Defence", 1,
            SlimeEnemy.MIN_DEF, SlimeEnemy.MAX_DEF, "n/a"
        );
        slimeInv.addItem(slimeAttack);
        slimeInv.addItem(slimeDefence);
        slimeInv.equipWeapon(0);
        slimeInv.equipArmour(1);

        // Initialise Goblin Inventory
        goblinInv = new CharacterInventory();
        WeaponItem goblinAttack = new WeaponItem(
            "Goblin Attack", 1,
            GoblinEnemy.MIN_ATTACK, GoblinEnemy.MAX_ATTACK, "n/a", "n/a"
        );
        DefenceItem goblinDefence = new DefenceItem(
            "Goblin Defence", 1,
            GoblinEnemy.MIN_DEF, GoblinEnemy.MAX_DEF, "n/a"
        );
        slimeInv.addItem(goblinAttack);
        slimeInv.addItem(goblinDefence);
        slimeInv.equipWeapon(0);
        slimeInv.equipArmour(1);

        // Initialise Ogre Inventory
        ogreInv = new CharacterInventory();
        WeaponItem ogreAttack = new WeaponItem(
            "Ogre Attack", 1,
            OgreEnemy.MIN_ATTACK, OgreEnemy.MAX_ATTACK, "n/a", "n/a"
        );
        DefenceItem ogreDefence = new DefenceItem(
            "Ogre Defence", 1,
            OgreEnemy.MIN_DEF, OgreEnemy.MAX_DEF, "n/a"
        );
        slimeInv.addItem(ogreAttack);
        slimeInv.addItem(ogreDefence);
        slimeInv.equipWeapon(0);
        slimeInv.equipArmour(1);

        // Initialise Dragon Inventory
        dragonInv = new CharacterInventory();
        WeaponItem dragonAttack = new WeaponItem(
            "Dragon Attack", 1,
            DragonEnemy.MIN_ATTACK, DragonEnemy.MAX_ATTACK, "n/a", "n/a"
        );
        DefenceItem dragonDefence = new DefenceItem(
            "Dragon Defence", 1,
            DragonEnemy.MIN_DEF, DragonEnemy.MAX_DEF, "n/a"
        );
        slimeInv.addItem(dragonAttack);
        slimeInv.addItem(dragonDefence);
        slimeInv.equipWeapon(0);
        slimeInv.equipArmour(1);

        // Example:
        opponent = new SlimeEnemy(slimeInv);
    }

    public List<String> getEnemyStats()
    {
        List<String> stats = new ArrayList<String>();
        stats.add(opponent.getName());
        stats.add(opponent.getHealth() + " / " + opponent.getMaxHealth());
        stats.add(opponent.getGold() + " G");
        stats.add(opponent.getInventory().getCurrAttackRange());
        stats.add(opponent.getInventory().getCurrDefenceRange());

        return stats;
    }

    public Vector<String> getMessages()
    {
        return new Vector<String>();
    }
}