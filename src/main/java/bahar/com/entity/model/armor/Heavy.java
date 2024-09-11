package bahar.com.entity.model.armor;

import bahar.com.entity.core.thing.Armor;

public class Heavy extends Armor
{
    public final static String NAME = "Ağır Zırh";
    public final static int ID = 3;
    public final static int PRICE = 40;
    public final static int BLOCK = 5;

    public Heavy()
    {
        super(NAME, PRICE, ID, BLOCK);
    }
}
