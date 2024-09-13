package bahar.com.entity.model.location;

import bahar.com.entity.core.location.EnemyLocation;

public class Forest extends EnemyLocation
{
    public static final String NAME = "Orman";
    public static final String PRIZE = "Odun";
    public static final String CREATE_MONSTER_NAME = "Vampir";
    public static final int ID = 3;
    public Forest() {
        super(NAME, ID, PRIZE);
    }
}
