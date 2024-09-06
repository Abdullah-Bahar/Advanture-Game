package bahar.com.entity.model.monster;

import bahar.com.entity.core.character.Monster;

public class Vampire extends Monster {
    public final static String NAME = "Vampir";
    public final static int ID = 2;
    public final static int DEMAGE = 4;
    public final static int HEALTH = 14;
    public final static int MONEY = 7;
    public Vampire()
    {
        super(NAME, ID, DEMAGE, HEALTH, MONEY);
    }
}
