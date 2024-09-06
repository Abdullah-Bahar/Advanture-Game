package bahar.com.entity.model.monster;

import bahar.com.entity.core.character.Monster;

public class Bear extends Monster
{
    public final static String NAME = "Ayı";
    public final static int ID = 3;
    public final static int DEMAGE = 7;
    public final static int HEALTH = 20;
    public final static int MONEY = 12;
    public Bear()
    {
        super(NAME, ID, DEMAGE, HEALTH, MONEY);
    }
}
