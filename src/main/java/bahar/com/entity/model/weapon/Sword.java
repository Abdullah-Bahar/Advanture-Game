package bahar.com.entity.model.weapon;

import bahar.com.entity.core.thing.Weapon;

public class Sword extends Weapon
{
    public static String NAME = "Kılıç";
    public static int ID = 2;
    public static int DMAGE = 3;
    public static int PRICE = 35;

    public Sword()
    {
        super(NAME, PRICE, ID, DMAGE);
    }
}
