package bahar.com.entity.model.location;

import bahar.com.entity.core.location.EnemyLocation;

public class River extends EnemyLocation
{
    public final static int ID = 2;
    public final static String NAME = "Nehir";
    public static final String PRIZE = "Su";
    public static boolean IS_PRIZE_WATER = false;
    public static final String CREATE_MONSTER_NAME = "Ayı";
    public River() {
        super(NAME, ID, IS_PRIZE_WATER, PRIZE);
    }
}
