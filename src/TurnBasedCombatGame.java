import model.*;

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
        System.out.println("Starting... ");

        GameCharacter player = new PlayerCharacter("Matt", new InventoryList());
        GameCharacter slime = new SlimeEnemy();

        System.out.println(player.toString());
        System.out.println(slime.toString());
    }
}