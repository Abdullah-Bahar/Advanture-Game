package bahar.com.entity.model.armor;

import bahar.com.entity.core.thing.Armor;

public class Medium extends Armor
{
    public final static String NAME = "Orta Zırh";
    public final static int ID = 2;
    public final static int PRICE = 25;
    public final static int BLOCK = 3;

    public Medium()
    {
        super(NAME, PRICE, ID, BLOCK);
    }
}
