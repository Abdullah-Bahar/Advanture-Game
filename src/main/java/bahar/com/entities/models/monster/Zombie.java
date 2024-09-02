package bahar.com.entities.models.monster;

import bahar.com.entities.core.character.Monster;

public class Zombie extends Monster {
    public final static String NAME = "Zombi";
    public final static int ID = 1;
    public final static int DEMAGE = 3;
    public final static int HEALTH = 10;
    public final static int MONEY = 4;
    public Zombie()
    {
        super(NAME, ID, DEMAGE, HEALTH, MONEY);
    }
}
