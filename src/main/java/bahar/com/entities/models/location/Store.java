package bahar.com.entities.models.location;

import bahar.com.entities.core.location.SafeLocation;

public class Store extends SafeLocation
{
    public static final String NAME = "Mağaza";
    public static final int ID  = 2;
    public Store() {
        super(NAME, ID);
    }

    public void Buy()
    {
        // Para eksiltme işlemi
    }

    public void ShowStore()
    {
        // Mağazadaki eşyaları görme
    }
}
