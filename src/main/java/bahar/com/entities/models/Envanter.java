package bahar.com.entities.models;

import bahar.com.entities.core.thing.Armor;
import bahar.com.entities.core.thing.Weapon;

public class Envanter
{
    // Adadan kurtulması için toplaması gereken şeyler
    private boolean food;
    private boolean water;
    private boolean wood;

    // Envanterinde bulanabilecek olan silah ve zırh
    private Weapon weapon;
    private Armor armor;


    public Envanter() {
        this.food = false;
        this.water = false;
        this.wood = false;
        this.weapon = null;
        this.armor = null;
    }
}
