package controller;
import model.characters.*;

public class BattleController 
{
    private EnemyCharacter opponent;

    public BattleController()
    {
        generateEnemy();
    }

    public void generateEnemy()
    {
        opponent = EnemyCharacter.makeEnemy("DRAGON");
    }

    public EnemyCharacter getEnemy()
    {
        return opponent;
    }

    public void newBattle()
    {
        
    }
}