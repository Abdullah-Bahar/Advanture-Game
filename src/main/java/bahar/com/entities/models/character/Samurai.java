package bahar.com.entities.models.character;

import bahar.com.entities.core.character.Player;

public class Samurai extends Player
{
    // Samuray başlangıç paketi
    public final static String NAME = "Samuray";
    public final static int ID = 1;
    public final static int DEMAGE = 5;
    public final static int HEALTH = 21;
    public final static int MONEY = 15;

    public Samurai()
    {
        super(NAME, ID, DEMAGE, HEALTH, MONEY);
    }
}
