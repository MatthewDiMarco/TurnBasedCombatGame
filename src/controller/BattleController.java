package controller;
import model.characters.*;
import model.items.*;

public class BattleController 
{
    private GameCharacter player;
    private EnemyCharacter opponent;
    private EnemyFactory spawner;
    private MainController controller;
    private Dice dice;

    public BattleController(Dice inDice, 
                            EnemyFactory inFactory, 
                            GameCharacter inPlayer,
                            MainController inController)
    {
        dice = inDice;
        spawner = inFactory;
        player = inPlayer;
        controller = inController; 
        generateEnemy();
    }

    public void generateEnemy()
    {
        opponent = spawner.makeEnemy(dice);
    }

    public EnemyCharacter getEnemy()
    {
        return opponent;
    }

    public void endTurn()
    {
        int effect;

        // Player's move
        effect = player.getInventory().useHealingPotion(dice);
        if (effect > 0)
        {
            player.setHealth(player.getHealth() + effect);
            player.notifyActionObservers(player.getName() + " HEALED " +
                                         effect + " points of Health");
        }
        else
        {
            effect = player.attack(dice);
            opponent.defend(effect, dice);
        }

        // Enemy's move
        effect = opponent.attack(dice);
        player.defend(effect, dice);

        // Check for deceased
        if (player.getHealth() == 0)
        {
            controller.gameOver();
        }
        else if (opponent.getHealth() == 0)
        {
            controller.playerVictory();
            player.setFighting(false);

            try
            {
                player.setGold(player.getGold() + opponent.getGold());
            }
            catch (CharacterException e) {}

            // Make harder for next battle
            spawner.mutateProbabilities();
        }
    }

    public void newBattle()
    {
        generateEnemy();
        opponent.setFighting(true);
        player.setFighting(true);
    }
}