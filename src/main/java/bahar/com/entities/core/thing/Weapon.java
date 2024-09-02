package bahar.com.entities.core.thing;

public class Weapon extends Thing
{
    private int dmage;

    public Weapon(String name, int price, int id, int dmage) {
        super(name, price, id);
        this.dmage = dmage;
    }

    public int getDmage() {
        return dmage;
    }

    public void setDmage(int dmage) {
        this.dmage = dmage;
    }
}
