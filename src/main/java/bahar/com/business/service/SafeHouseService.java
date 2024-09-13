package bahar.com.business.service;

import bahar.com.business.core.ISafeHouse;
import bahar.com.entity.core.character.Player;

public class SafeHouseService implements ISafeHouse
{
    private Player player;

    public SafeHouseService(Player player) {
        this.player = player;
    }

    @Override
    public void WelcomeToHouse()
    {
        String yildiz = "\n**********************************\n";
        String welcome = "\nGüvenli eve hoş geldin.\n" +
                "\n" +
                "Burada endişelenmene gerek yok. Güvendesin merak etme.\n" +
                "Canın azaldığında buraya gelip canını yenileyebilirsin.\n" +
                "Ayrıca mağazaya gidip seni güçlendirecek itemler alabilirsin. \n";

        System.out.println(yildiz + welcome + yildiz);
    }

    @Override
    public void PrintCharacterStatus() {
        System.out.println("Karakterinin Durumu : ");

        // Tablo başlığını oluşturuyoruz
        System.out.format("+-------------------+--------------+\n");

        // Karakter bilgilerini tablo formatında yazdırıyoruz
        System.out.format("| %-17s | %-12d |\n", "KARAKTER ID", player.getId());
        System.out.format("| %-17s | %-12s |\n", "KARAKTER AD", player.getName());
        System.out.format("| %-17s | %-12d |\n", "KARAKTER CAN", player.getHealth());
        System.out.format("| %-17s | %-12d |\n", "KARAKTER HASAR", player.getDamage());
        System.out.format("| %-17s | %-12d |\n", "KARAKTER BLOK", player.getBlok());
        System.out.format("| %-17s | %-12d |\n", "KARAKTER PARA", player.getMoney());

        // Tablo alt çizgisini oluşturuyoruz
        System.out.format("+-------------------+--------------+\n");
    }

    @Override
    public void ShowEnvanter() {
        System.out.println("Mevcut Envanter:");

        // Tablo başlığını oluşturuyoruz
        System.out.format("+-----------+---------------+\n");

        // Envanter bilgilerini tablo formatında yazdırıyoruz
        System.out.format("| %-9s | %-13s |\n", "ZIRH", player.getEnvanter().getArmor() == null ? "Mevcut Değil" : player.getEnvanter().getArmor().getName());
        System.out.format("| %-9s | %-13s |\n", "SİLAH", player.getEnvanter().getWeapon() == null ? "Mevcut Değil" : player.getEnvanter().getWeapon().getName());
        System.out.format("| %-9s | %-13s |\n", "YEMEK", player.getEnvanter().isFood() ? "Mevcut" : "Mevcut Değil");
        System.out.format("| %-9s | %-13s |\n", "SU", player.getEnvanter().isWater() ? "Mevcut" : "Mevcut Değil");
        System.out.format("| %-9s | %-13s |\n", "ODUN", player.getEnvanter().isWood() ? "Mevcut" : "Mevcut Değil");

        // Tablo alt çizgisini oluşturuyoruz
        System.out.format("+-----------+---------------+\n");
    }

    @Override
    public void HealthRenewal()
    {
        player.setHealth(getPlayer().getMaxHealth());
        System.out.println("Canın Yenileniyor...");
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
