package bahar.com.entity.model.location;

import bahar.com.entity.core.location.EnemyLocation;

public class River extends EnemyLocation
{
    public final static String NAME = "Nehir";
    public static final String PRIZE = "Su";
    public static final String CREATE_MONSTER_NAME = "Ayı";
    public final static int ID = 2;
    public River() {
        super(NAME, ID, PRIZE);
    }
}
