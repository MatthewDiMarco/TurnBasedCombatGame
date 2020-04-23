package controller;
import model.characters.*;
import java.util.*;

public class BattleController 
{
    private EnemyCharacter opponent;

    public BattleController()
    {
        opponent = new GoblinEnemy(); //temp
    }

    public List<String> getEnemyStats()
    {
        List<String> stats = new ArrayList<String>();
        stats.add(opponent.getName());
        stats.add(opponent.getHealth() + " / " + opponent.getMaxHealth());
        stats.add(opponent.getGold() + " G");
        stats.add(opponent.getAttackRange());
        stats.add(opponent.getDefenceRange());

        return stats;
    }
}