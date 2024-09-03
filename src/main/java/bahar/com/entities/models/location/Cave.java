package bahar.com.entities.models.location;

import bahar.com.entities.core.character.Monster;
import bahar.com.entities.core.location.EnemyLocation;
import bahar.com.entities.models.monster.Zombie;

public class Cave extends EnemyLocation //implements IObstacleLocation
{
    public static final String NAME = "Mağara";
    public static final int ID = 5;

    public Cave() {
        super(NAME, ID);
    }
}
