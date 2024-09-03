package bahar.com.entities.models.location;

import bahar.com.entities.core.character.Monster;
import bahar.com.entities.core.location.EnemyLocation;
import bahar.com.entities.models.monster.Bear;

public class River extends EnemyLocation
{
    public final static String NAME = "Nehir";
    public final static int ID = 3;
    public River() {
        super(NAME, ID);
    }
}
