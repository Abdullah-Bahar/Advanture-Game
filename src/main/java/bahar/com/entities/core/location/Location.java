package bahar.com.entities.core.location;

public class Location
{
    private String name;
    private int id;

    public Location(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


