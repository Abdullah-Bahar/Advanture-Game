package bahar.com.entities.core.location;

public abstract class Location
{
    private String name;
    private int id;

    public Location(String name, int id)
    {
        this.name = name;
        this.id = id;
    }
}
