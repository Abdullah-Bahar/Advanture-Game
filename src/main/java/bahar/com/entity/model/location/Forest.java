package bahar.com.entity.model.location;

import bahar.com.entity.core.location.EnemyLocation;

public class Forest extends EnemyLocation
{
    public static final int ID = 3;
    public static final String NAME = "Orman";
    public static final String PRIZE = "Odun";
    public static boolean IS_PRIZE_WOOD = false;
    public static final String CREATE_MONSTER_NAME = "Vampir";
    public Forest() {
        super(NAME, ID, IS_PRIZE_WOOD, PRIZE);
    }
}
