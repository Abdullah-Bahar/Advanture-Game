package bahar.com.entity.core.location;

import bahar.com.entity.core.IObstacle;
import bahar.com.entity.core.character.Monster;
import bahar.com.entity.model.monster.Bear;
import bahar.com.entity.model.monster.Vampire;
import bahar.com.entity.model.monster.Zombie;

import java.util.Random;

public class EnemyLocation extends Location implements IObstacle
{
    public static boolean prize;

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

    @Override
    public Monster[] CreateMonster(int id)
    {
        // RandomMonster ile oluşturulan diziye nesneleri atanması
        Monster[] monsters = this.RandomMonster();

        switch (id)
        {
            case Zombie.ID:
                for (int i = 0; i < monsters.length; i++)
                    monsters[i] = new Zombie();
                break;
            case Vampire.ID:
                for (int i = 0; i < monsters.length; i++)
                    monsters[i] = new Vampire();
                break;
            case Bear.ID:
                for (int i = 0; i < monsters.length; i++)
                    monsters[i] = new Bear();
                break;
        }

        return monsters;
    }
}
