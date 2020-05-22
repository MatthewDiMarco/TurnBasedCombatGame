package controller;
import model.characters.*;
import model.items.*;

/**
 * This class is responsible for facilitating a battle between the player and
 * an enemy.
 */
public class BattleController 
{
    private GameCharacter player;
    private EnemyCharacter opponent;
    private Factory spawner;
    private MainController controller;
    private Dice dice;

    public BattleController(Dice inDice, 
                            Factory inFactory, 
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

    /**
     * Called by view to end the player's turn and make the characters attack
     * and defend, subtracting their health as necessary.
     * @param index The item to try use in the player's inventory. -1 means
     *              "no item", resulting in the player using their weapon.
     */
    public void endTurn(int index) throws GameStateException
    {
        int dmg = 0;

        // Player's move
        if (index <= -1)
        {
            dmg += player.attack(dice);
        }
        else
        {
            try
            {
                dmg += player.getInventory().getItem(index).interactWith(player);
            }
            catch (ItemInteractionException e)
            {
                throw new GameStateException(e.getMessage());
            }
        }

        if (dmg > 0)
        {
            opponent.defend(dmg, dice);   
        }

        // Enemy's move
        if (opponent.getHealth() != 0)
        {
            dmg = opponent.attack(dice);
            player.defend(dmg, dice);

            // Check for deceased
            if (player.getHealth() == 0)
            {
                controller.gameOver();
            }
        }
        else
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