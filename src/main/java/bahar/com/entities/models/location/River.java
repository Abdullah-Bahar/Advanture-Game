package bahar.com.entities.models.location;

import bahar.com.entities.core.IObstacleLocation;
import bahar.com.entities.core.character.Monster;
import bahar.com.entities.core.location.EnemyLocation;
import bahar.com.entities.models.monster.Bear;

public class River extends EnemyLocation implements IObstacleLocation
{
    public final static String NAME = "Nehir";
    public final static int ID = 1;
    public River() {
        super(NAME, ID);
    }

    @Override
    public Monster[] CreateMonster()
    {
        // RandomMonster ile oluşturulan diziye nesneleri atanması
        Monster[] monsters = super.RandomMonster();
        for (int i = 0; i < monsters.length; i++)
        {
            monsters[i] = new Bear();
        }

        return monsters;
    }
}
