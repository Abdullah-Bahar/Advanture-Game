package bahar.com.entities.models.location;

import bahar.com.entities.core.location.SafeLocation;

public class SafeHouse extends SafeLocation
{
    public static final String NAME = "Güvenli Ev";
    public static final int ID  = 1;
    public SafeHouse() {
        super(NAME, ID);
    }

    public void HealthRenewal()
    {
        // oyuncu güvenli eve geldiğinde canını yenilicek
    }

}
