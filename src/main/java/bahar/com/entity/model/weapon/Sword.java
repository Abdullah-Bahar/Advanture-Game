package bahar.com.entity.model.weapon;

import bahar.com.entity.core.thing.Weapon;

public class Sword extends Weapon
{
    public final static String NAME = "Kılıç";
    public final static int ID = 2;
    public final static int DMAGE = 3;
    public final static int PRICE = 35;

    public Sword()
    {
        super(NAME, PRICE, ID, DMAGE);
    }
}
