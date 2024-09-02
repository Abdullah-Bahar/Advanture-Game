package bahar.com.entities.models.armor;

import bahar.com.entities.core.thing.Armor;

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
