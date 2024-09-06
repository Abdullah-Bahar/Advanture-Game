package bahar.com.entity.model.character;

import bahar.com.entity.core.character.Player;

public class Archer extends Player {

    // Okçu Başlangıç Paketi
    public final static String NAME = "Okçu";
    public final static int ID = 2;
    public final static int DEMAGE = 7;
    public final static int HEALTH = 18;
    public final static int MONEY = 20;

    public Archer()
    {
        super(NAME, ID, DEMAGE, HEALTH, MONEY);
    }
}
