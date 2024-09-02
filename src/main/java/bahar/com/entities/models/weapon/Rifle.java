package bahar.com.entities.models.weapon;

import bahar.com.entities.core.thing.Weapon;

public class Rifle extends Weapon
{
    public static String NAME = "Tüfek";
    public static int ID = 3;
    public static int DMAGE = 7;
    public static int PRICE = 45;

    public Rifle()
    {
        super(NAME, PRICE, ID, DMAGE);
    }
}
