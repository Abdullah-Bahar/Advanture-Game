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

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isWood() {
        return wood;
    }

    public void setWood(boolean wood) {
        this.wood = wood;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
