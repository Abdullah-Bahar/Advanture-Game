package bahar.com.entities.core.location;

import bahar.com.entities.core.IObstacle;
import bahar.com.entities.core.character.Monster;

import java.util.Random;

public class EnemyLocation extends Location implements IObstacle
{
    public EnemyLocation(String name, int id)
    {
        super(name, id);
    }

    @Override
    public Monster[] RandomMonster()
    {
        // 1-3 arasında Monster class dizisi oluştururur.
        Random r = new Random();
        int randomNumber = r.nextInt(3) + 1;

        return new Monster[randomNumber];
    }
}
