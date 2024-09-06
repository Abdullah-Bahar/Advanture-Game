package bahar.com.entity.core.character;

public class CoreEntitiy
{
    private String name;
    private final int id;
    private int damage;
    private int health;
    private int money;

    public CoreEntitiy(String name, int id, int damage, int health, int money)
    {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.money = money;
    }


    public int getDamage()
    {
        return damage;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        if (health < 0)
            this.health = 0;
        else
            this.health = health;
    }

    public int getMoney()
    {
        return money;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
