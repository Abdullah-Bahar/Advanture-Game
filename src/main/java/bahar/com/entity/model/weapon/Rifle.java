package bahar.com.entity.model.weapon;

import bahar.com.entity.core.thing.Weapon;

public class Rifle extends Weapon
{
    public final static String NAME = "Tüfek";
    public final static int ID = 3;
    public final static int DMAGE = 7;
    public final static int PRICE = 45;

    public Rifle()
    {
        super(NAME, PRICE, ID, DMAGE);
    }
}
