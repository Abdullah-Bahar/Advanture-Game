package bahar.com.entity.model.character;

import bahar.com.entity.core.character.Player;

public class Knight extends Player
{
    // Şovalye başlangıç paketi
    public final static String NAME = "Şovalye";
    public final static int ID = 3;
    public final static int DEMAGE = 5;
    public final static int HEALTH = 21;
    public final static int MONEY = 15;

    public Knight()
    {
        super(NAME, ID, DEMAGE, HEALTH, MONEY);
    }
}
