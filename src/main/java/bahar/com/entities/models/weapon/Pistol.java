package bahar.com.entities.models.weapon;

import bahar.com.entities.core.thing.Weapon;

public class Pistol extends Weapon {

    public static String NAME = "Silah";
    public static int ID = 1;
    public static int DMAGE = 2;
    public static int PRICE = 25;

    public Pistol()
    {
        super(NAME, PRICE, ID, DMAGE);
    }
}
