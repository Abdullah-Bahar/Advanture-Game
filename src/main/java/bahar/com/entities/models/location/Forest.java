package bahar.com.entities.models.location;

import bahar.com.entities.core.character.Monster;
import bahar.com.entities.core.location.EnemyLocation;
import bahar.com.entities.models.monster.Vampire;

public class Forest extends EnemyLocation
{
    public static final String NAME = "Orman";
    public static final int ID = 4;
    public Forest() {
        super(NAME, ID);
    }
}
