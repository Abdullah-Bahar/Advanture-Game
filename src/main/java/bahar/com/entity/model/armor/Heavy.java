package bahar.com.entity.model.armor;

import bahar.com.entity.core.thing.Armor;

public class Heavy extends Armor
{
    public static String NAME = "Ağır Zırh";
    public static int ID = 3;
    public static int PRICE = 40;
    public static int BLOCK = 5;

    public Heavy()
    {
        super(NAME, PRICE, ID, BLOCK);
    }
}
