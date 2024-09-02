package bahar.com.entities.models.armor;

import bahar.com.entities.core.thing.Armor;

public class Medium extends Armor
{
    public static String NAME = "Orta Zırh";
    public static int ID = 2;
    public static int PRICE = 25;
    public static int BLOCK = 3;

    public Medium()
    {
        super(NAME, PRICE, ID, BLOCK);
    }
}
