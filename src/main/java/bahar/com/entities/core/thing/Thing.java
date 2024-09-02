package bahar.com.entities.core.thing;

public abstract class Thing
{
    private String name;
    private int price;
    private int id;

    public Thing(String name, int price, int id)
    {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
