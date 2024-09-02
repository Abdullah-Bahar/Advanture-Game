package bahar.com.entities.models.location;

import bahar.com.entities.core.IObstacleLocation;
import bahar.com.entities.core.character.Monster;
import bahar.com.entities.core.location.EnemyLocation;
import bahar.com.entities.models.monster.Vampire;

public class Forest extends EnemyLocation implements IObstacleLocation
{
    public static final String NAME = "Orman";
    public static final int ID = 3;
    public Forest() {
        super(NAME, ID);
    }

    @Override
    public Monster[] CreateMonster()
    {
        // RandomMonster ile oluşturulan diziye nesneleri atanması
        Monster[] monsters = super.RandomMonster();
        for (int i = 0; i < monsters.length; i++)
        {
            monsters[i] = new Vampire();
        }

        return monsters;
    }
}
