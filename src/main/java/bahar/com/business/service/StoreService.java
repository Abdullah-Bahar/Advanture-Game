package bahar.com.business.service;

import bahar.com.business.core.IStore;
import bahar.com.entity.core.thing.Armor;
import bahar.com.entity.core.thing.Weapon;
import bahar.com.entity.model.armor.Heavy;
import bahar.com.entity.model.armor.Light;
import bahar.com.entity.model.armor.Medium;
import bahar.com.entity.model.weapon.Pistol;
import bahar.com.entity.model.weapon.Rifle;
import bahar.com.entity.model.weapon.Sword;

public class StoreService implements IStore
{

    @Override
    public void WelcomeToStore() {
        String yildiz = "\n**********************************\n";
        String welcome = "\nMağazaya hoş geldin.\n" +
                "Alışveriş yapmaya hazır mısın ?\n" +
                "Eğer hazır değilsen şunu bilmelisin ki bu adadan kurtulup hayatta kalmak için\n" +
                "Kendini güçlendireceğin bi şeyler almalısın. \n";

        System.out.println(yildiz + welcome + yildiz);
    }

    @Override
    public void PrintCategories() {
        System.out.println("Kategoriler : ");

        // Tablo başlığını oluşturuyoruz
        System.out.format("+-----+---------------+\n");
        System.out.format("| ID  | CATEGORY NAME |\n");
        System.out.format("+-----+---------------+\n");

        // Kategori bilgilerini tablo formatında yazdırıyoruz
        System.out.format("| %-5d | %-11s |\n", 1, "Zırh");
        System.out.format("| %-5d | %-11s |\n", 2, "Silah");

        // Tablo alt çizgisini oluşturuyoruz
        System.out.format("+-----+---------------+\n");
    }

    @Override
    public void PrintArmor() {
        System.out.println("Zırhlar : ");

        // Tablo başlığını oluşturuyoruz
        System.out.format("+-----+-------------+-------+-------+\n");
        System.out.format("| ID  |   NAME      | BLOCK | PRICE |\n");
        System.out.format("+-----+-------------+-------+-------+\n");

        // Kategori bilgilerini tablo formatında yazdırıyoruz
        CreateArmorInfo(Light.ID, Light.NAME, Light.BLOCK, Light.PRICE);
        CreateArmorInfo(Medium.ID, Medium.NAME, Medium.BLOCK, Medium.PRICE);
        CreateArmorInfo(Heavy.ID, Heavy.NAME, Heavy.BLOCK, Heavy.PRICE);

        // Tablo alt çizgisini oluşturuyoruz
        System.out.format("+-----+-------------+-------+-------+\n");
    }

    private void CreateArmorInfo(int id, String name, int block, int price) {
        System.out.format("| %-3d | %-11s | %-5s | %-5s |\n", id, name, block, price);
    }

    @Override
    public void PrintWeepon() {
        System.out.println("Zırhlar : ");

        // Tablo başlığını oluşturuyoruz
        System.out.format("+-----+-------------+-------+-------+\n");
        System.out.format("| ID  |   NAME      | DMAGE | PRICE |\n");
        System.out.format("+-----+-------------+-------+-------+\n");

        // Kategori bilgilerini tablo formatında yazdırıyoruz
        CreateWeepomInfo(Pistol.ID, Pistol.NAME, Pistol.DMAGE, Pistol.PRICE);
        CreateWeepomInfo(Sword.ID, Sword.NAME, Sword.DMAGE, Sword.PRICE);
        CreateWeepomInfo(Rifle.ID, Rifle.NAME, Rifle.DMAGE, Rifle.PRICE);

        // Tablo alt çizgisini oluşturuyoruz
        System.out.format("+-----+-------------+-------+-------+\n");
    }

    private void CreateWeepomInfo(int id, String name, int dmage, int price) {
        System.out.format("| %-3d | %-11s | %-5s | %-5s |\n", id, name, dmage, price);
    }

    @Override
    public Armor BuyArmor(int armorId) {
        Armor armor = null;
        switch (armorId)
        {
            case Light.ID:
                armor = new Light();
                break;
            case Heavy.ID:
                armor = new Heavy();
                break;
            case Medium.ID:
                armor = new Medium();
                break;
            default:
                System.out.println("İşlemininiz Gerçekleşemedi.");
        }

        return armor;
    }

    @Override
    public Weapon BuyWeepon(int weaponId) {
        Weapon weapon = null;
        switch (weaponId)
        {
            case Pistol.ID:
                weapon = new Pistol();
                break;
            case Rifle.ID:
                weapon = new Rifle();
                break;
            case Sword.ID:
                weapon = new Sword();
                break;
            default:
                System.out.println("İşlemininiz Gerçekleşemedi.");
        }

        return weapon;
    }

    @Override
    public boolean CheckMoney(int money, int price) {
        return price <= money;
    }
}
