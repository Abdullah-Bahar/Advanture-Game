package bahar.com.entity.model.armor;

import bahar.com.entity.core.thing.Armor;

public class Light extends Armor
{
    public static String NAME = "Hafif Zırh";
    public static int ID = 1;
    public static int PRICE = 15;
    public static int BLOCK = 1;

    public Light()
    {
        super(NAME, PRICE, ID, BLOCK);
    }
}
