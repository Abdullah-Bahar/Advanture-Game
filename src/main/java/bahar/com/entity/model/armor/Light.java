package bahar.com.entity.model.armor;

import bahar.com.entity.core.thing.Armor;

public class Light extends Armor
{
    public final static String NAME = "Hafif Zırh";
    public final static int ID = 1;
    public final static int PRICE = 15;
    public final static int BLOCK = 1;

    public Light()
    {
        super(NAME, PRICE, ID, BLOCK);
    }
}
