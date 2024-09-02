package bahar.com.entities.models.location;

import bahar.com.entities.core.IObstacleLocation;
import bahar.com.entities.core.character.Monster;
import bahar.com.entities.core.location.EnemyLocation;
import bahar.com.entities.models.monster.Zombie;

public class Cave extends EnemyLocation implements IObstacleLocation
{
    public static final String NAME = "Mağara";
    public static final int ID = 2;

    public Cave() {
        super(NAME, ID);
    }

    @Override
    public Monster[] CreateMonster()
    {
        // RandomMonster ile oluşturulan diziye nesneleri atanması
        Monster[] monsters = super.RandomMonster();
        for (int i = 0; i < monsters.length; i++)
        {
            monsters[i] = new Zombie();
        }

        return monsters;
    }
}
