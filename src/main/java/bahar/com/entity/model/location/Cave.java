package bahar.com.entity.model.location;

import bahar.com.entity.core.location.EnemyLocation;

public class Cave extends EnemyLocation
{
    public static final int ID = 4;
    public static final String NAME = "Mağara";
    public static final String PRIZE = "Yemek";
    public static boolean IS_PRIZE_FOOD = false;
    public static final String CREATE_MONSTER_NAME = "Zombi";

    public Cave() {
        super(NAME, ID, IS_PRIZE_FOOD, PRIZE);
    }
}
