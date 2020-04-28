package controller;
import model.characters.*;
import model.items.*;

public class BattleController 
{
    private EnemyCharacter opponent;
    private EnemyFactory spawner;
    private Dice dice;

    public BattleController(Dice inDice, EnemyFactory inFactory)
    {
        dice = inDice;
        spawner = inFactory;
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

    public void newBattle()
    {
        generateEnemy();
    }
}