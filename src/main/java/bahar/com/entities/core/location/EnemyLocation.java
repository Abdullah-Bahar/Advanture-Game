package bahar.com.entities.core.location;

import bahar.com.entities.core.IObstacle;
import bahar.com.entities.core.character.Monster;
import bahar.com.entities.models.monster.Bear;
import bahar.com.entities.models.monster.Vampire;
import bahar.com.entities.models.monster.Zombie;

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
