package bahar.com.entity.core.thing;

public class Armor extends Thing
{
    private String name;
    private int block;
    public Armor(String name, int price, int id, int block)
    {
        super(name, price, id);
        this.block = block;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }
}
