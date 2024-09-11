package bahar.com.entity.model.weapon;

import bahar.com.entity.core.thing.Weapon;

public class Pistol extends Weapon {

    public final static String NAME = "Silah";
    public final static int ID = 1;
    public final static int DMAGE = 2;
    public final static int PRICE = 25;

    public Pistol()
    {
        super(NAME, PRICE, ID, DMAGE);
    }
}
