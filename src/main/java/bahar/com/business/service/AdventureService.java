package bahar.com.business.service;

import bahar.com.business.core.IAdventure;
import bahar.com.business.core.ISafeHouse;
import bahar.com.entity.core.character.Monster;
import bahar.com.entity.core.character.Player;
import bahar.com.entity.core.location.EnemyLocation;
import bahar.com.entity.core.location.Location;
import bahar.com.entity.core.location.SafeLocation;
import bahar.com.entity.model.character.Archer;
import bahar.com.entity.model.character.Knight;
import bahar.com.entity.model.character.Samurai;
import bahar.com.entity.model.location.*;
import bahar.com.entity.model.monster.Bear;
import bahar.com.entity.model.monster.Vampire;
import bahar.com.entity.model.monster.Zombie;

import java.util.Random;
import java.util.Scanner;


public class AdventureService implements IAdventure
{
    private String userName;
    private Player player;
    private SafeLocation safeLocation;
    private EnemyLocation enemyLocation;
    private Location location;

    // Ctor
    public AdventureService()
    {
        userName = "";
        player = null;
        safeLocation = null;
        enemyLocation = null;
    }


    /*********** IMPLEMENT ADVANTURE ************/
    @Override
    public void Intro() {
        System.out.print("""
                \s
                Merhabalar...\s
                Macera oyunumuza hoş geldiniz!\s
                \s
                Adadan kurtulmak için canavarların olduğu bölgelerden gereken eşyaları yoplamalısın.\s
                Tehlikeli bölgelerden eşyaları toplayabilmen için tüm canavarları temizlemelisin.\s
                Eğer canavarları temizlerken ölürsen oyunu kaybedersin.\s
                Tekrar baştan almak istemiyorsan dikkatli olmalısın. Unutma ki hayatta ikinci şanslar asla olmaz.\s
                Bu oyunu kazanmak istiyorsan gerçek hayatta olduğunu unutmamalısın.\s
                Eğer başlamaya hazırsan enter tuşuna basarak devam edebilirsin.\s
                İyi oyunlar... Hayatta kalmayı unutma!\s
                \s
                """);
    }

    @Override
    public void InitializePlayer(String userName, int id)
    {
        // Oyuncu Adı Ataması
        setUserName(userName);

        // Oyuncu karakter ataması
        CreatePlayer(id);
    }

    @Override
    public void History()
    {
        String name = DynamicAccessToStaticVariableName(getPlayer());

        System.out.print(
                "\n" +
                        "İmparatorluk, giderek artan düşman tehditleriyle zor günler yaşıyordu. İmparator, imparatorluğun en cesur savaşçıları olan Okçu, Şövalye ve Samuray'dan oluşan bir ekibi; \n" +
                        "gizemli bir görev için uzak diyarlara gönderdi. Onları taşıyan gemi, fırtınalı bir gece aniden şiddetli bir kasırgaya yakalandı. Dalga dalga gelen saldırılar, \n" +
                        "geminin direklerini kırdı, gövdesini paramparça etti. Geminin güvertesi suya gömülürken, savaşçılar hayatta kalma mücadelesi vermekteydi. \n" +
                        "\n" +
                        "Fırtına nihayet dindiğinde, güneş yeniden doğarken, " + name + " " + getUserName() + " gözlerini bilinmeyen bir adada açtı. \n" +
                        "Kendini yorgun ve yalnız hissediyordu. Arkadaşlarından hiçbir iz yoktu \n" +
                        "Birkaç adım attıktan sonra geminin kalıntılarını ve etrafa saçılmış birkaç eşyayı gördü. Zihninde tek bir şey yankılanıyordu: \n" +
                        "\"İmparatorluğuma geri dönmeliyim.\"\n" +
                        "Ancak bilmesi gerekn bir şey vardı. Bu ada canavarlarla dolu bir adaydı ve buradan kurtulmak için onlarla savaşıp 3 temel eşyayı toplaması gerekiyordu \n" +
                        "Odun, Yemek, Su... \n" +
                        "\n");
    }

    // Oyuncu Karakter Ataması
    @Override
    public void CreatePlayer(int id)
    {
        switch (id)
        {
            case 1: // Samuray
                setPlayer(new Samurai());
                break;
            case 2: // Okçu
                setPlayer(new Archer());
                break;
            case 3: // Şovalye
                setPlayer(new Knight());
                break;
            default:
                System.out.println("Karakter Seçiminde Bir Hata Oluştu!\n");
        }
    }

    @Override
    public void LocationChange(int id)
    {
        switch (id)
        {
            case 1: // Güvenli ev
                setSafeLocation(new SafeHouse());
                break;
            case 2: // Nehir
                setEnemyLocation(new River());
                break;
            case 3: // Orman
                setEnemyLocation(new Forest());
                break;
            case 4: // Mağara
                setEnemyLocation(new Cave());
                break;
            default:
                LocationChange(SafeHouse.ID);
                System.out.println("Lokasyon Değişiminde Bir Hata Oluştu!\n");
        }

        if (id == 1)
        {
            setEnemyLocation(null);
            location = safeLocation;
        }
        else
        {
            setSafeLocation(null);
            location = enemyLocation;
        }
    }




    /************* WAR **************/
    @Override
    public void initializeWar()
    {
        int eLocationId = DynamicAccessToStaticVariableId(enemyLocation);
        int monsterId = WitchMonster(eLocationId);
        Monster[] monsters = enemyLocation.CreateMonster(monsterId);

        for (int i = 0; i < monsters.length; i++)
        {
            this.War(this.player, monsters[i]);
            if (this.player.getHealth() <= 0)
            {
                GameOver();
                break;
            }
            this.player.setMoney(this.player.getMoney() + monsters[i].getMoney());
        }
        IsWinWar(monsterId);
        IsGameWin();
    }

    private int WitchMonster(int eLocationId)
    {
        return switch (eLocationId) {
            case Cave.ID -> Zombie.ID;
            case Forest.ID -> Vampire.ID;
            case River.ID -> Bear.ID;
            default -> 0;
        };
    }

    private void IsGameWin()
    {
        if (this.player.getEnvanter().isFood() &&
                this.player.getEnvanter().isWater() &&
                this.player.getEnvanter().isWood())
        {
            System.out.println("Oyunu kazandın!!!!");
            System.exit(0);
        }
    }

    private void IsWinWar(int monsterId)
    {
        switch (monsterId)
        {
            case Zombie.ID:
                this.player.getEnvanter().setFood(true);
                break;
            case Vampire.ID:
                this.player.getEnvanter().setWood(true);
                break;
            case Bear.ID:
                this.player.getEnvanter().setWater(true);
                break;
            default:
                System.out.println("Ödül kazanılamadı!");
        }
    }

    @Override
    public void War(Player player, Monster monster)
    {
        if (FirstHit() == 1)
        {
            while(player.getHealth() <= 0 || monster.getHealth() <= 0)
            {
                PlayerAttack(this.player, monster);
                if (monster.getHealth() > 0)
                    MonsterAttack(this.player, monster);
            }
        }
        else // FirstHit == 0
        {
            while(player.getHealth() <= 0 || monster.getHealth() <= 0)
            {
                MonsterAttack(this.player, monster);
                if (this.player.getHealth() > 0)
                    PlayerAttack(this.player, monster);
            }
        }
    }

    private void MonsterAttack(Player player, Monster monster)
    {
        int hasar = monster.getDamage() - player.getBlok();

        player.setHealth(hasar);
    }

    private void PlayerAttack(Player player, Monster monster)
    {
        int hasar = player.getDamage();

        monster.setHealth(hasar);
    }

    @Override
    public int FirstHit()
    {
        Random r = new Random();

        return r.nextInt(2);
    }

    @Override
    public void GameOver()
    {
        System.out.println("Malesef Oyunu Kaybettin");
        System.exit(0);
    }

    @Override
    public void Araf()
    {
        // Daha donraki sürümlerde
    }
    /***********************************/



    /********** IMPEMENT SAFEHOUSE ************/
    // Buradaki implamentasyonlar SafeHouseService SInıfında implemente edilmiştir
    /*
    private void PrintHealingAnimation()
    {
        // PrintHealingAnimation() fonksiyonunu çağıracak olan base fonksiyon
        {
                if (player.getHealth() != player.getMaxHealth())
            for (int i = 0; i < 2; i++)
                PrintHealingAnimation();
        }



        String baseText = "Canın yenileniyor";
        StringBuilder animation = new StringBuilder(baseText);

        for (int j = 0; j < 3; j++) // 3 aşama için döngü: '.', '..', '...'
        {
            try
            {
                System.out.print("\r" + animation); // \r ile aynı satırı günceller
                Thread.sleep(1000); // 1 saniye bekle
                animation.append("."); // bir nokta ekle
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        // Yeniden başa dönmek için kısa bir bekleme
        try
        {
            Thread.sleep(1000); // Yarım saniye bekle
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    */
    /***************************************/


    /********** PRINT İŞLEMLERİ ************/
    public void PrintCurrentLocation() // Mevcut konum
    {
        System.out.println("Karakterinin Durumu : ");

        // Tablo başlığını oluşturuyoruz
        System.out.format("+-------+--------------+\n");

        // Karakter bilgilerini tablo formatında yazdırıyoruz
        System.out.format("| %-5s | %-12d |\n", "ID", location.getId());
        System.out.format("| %-5s | %-12s |\n", "NAME", location.getName());

        // Tablo alt çizgisini oluşturuyoruz
        System.out.format("+-------+--------------+\n");
    }

    // Safe bölgelerini yazdırma
    public void PrintSafeLocationInfo(int locationId)
    {
        System.out.format("+-----+------------+\n");
        System.out.format("| ID  | SAFE NAME  |\n");
        System.out.format("+-----+------------+\n");

        if (locationId != SafeHouse.ID)
            CreateSafeLocationInfo(SafeHouse.ID, SafeHouse.NAME);

        System.out.format("+-----+------------+\n");
    }

    public void CreateSafeLocationInfo(int id, String name)
    {
        System.out.format("| %-3d | %-10s |\n", id, name);
    }

    // Enmey bölgelerini yazdırma
    public void PrintEnemyLocationInfo(int locationId)
    {
        System.out.format("+-----+------------+--------------+-------+\n");
        System.out.format("| ID  | NAME       | MONSTER NAME | PRIZE |\n");
        System.out.format("+-----+------------+--------------+-------+\n");

        if (locationId != River.ID)
            CreateEnemyLocationInfo(River.ID, River.NAME, River.CREATE_MONSTER_NAME, River.PRIZE, River.prize);
        if (locationId != Forest.ID)
            CreateEnemyLocationInfo(Forest.ID, Forest.NAME, Forest.CREATE_MONSTER_NAME, Forest.PRIZE, Forest.prize);
        if (locationId != Cave.ID)
            CreateEnemyLocationInfo(Cave.ID, Cave.NAME, Cave.CREATE_MONSTER_NAME, Cave.PRIZE, Cave.prize);

        System.out.format("+-----+------------+--------------+-------+\n");
    }

    private void CreateEnemyLocationInfo(int id, String name, String monsterName, String prizeName, boolean condition) {
        System.out.format("| %-3d | %-10s | %-12s | %-5s |\n", id, name, monsterName, prizeName + (condition ? " (ALINDI)" : ""));
    }

    // Print Character
    public void PrintCharacterInfo()
    {
        System.out.format("+-----+------------+------+-------+------+\n");
        System.out.format("| ID  | NAME       | CAN  | HASAR | PARA |\n");
        System.out.format("+-----+------------+------+-------+------+\n");

        CreateCharacterInfo(Samurai.ID, Samurai.NAME, Samurai.HEALTH, Samurai.DEMAGE, Samurai.MONEY);
        CreateCharacterInfo(Archer.ID, Archer.NAME, Archer.HEALTH, Archer.DEMAGE, Archer.MONEY);
        CreateCharacterInfo(Knight.ID, Knight.NAME, Knight.HEALTH, Knight.DEMAGE, Knight.MONEY);

        System.out.format("+-----+------------+------+-------+------+\n");
    }

    public void CreateCharacterInfo(int id, String name, int health, int damage, int money)
    {
        System.out.format("| %-3d | %-10s | %-4d | %-5d | %-4d |\n", id, name, health, damage, money);
    }

    /***************************************/



    /********** DİĞER İŞLEMLER ************/
    // Class'ların NAME static değişkenlerine dinamik erişim sağlamak için kullanılan bir method
    private String DynamicAccessToStaticVariableName(Object object)
    {
        String name;

        // Object nesnesinin çalışma zamanındaki sınıfını temsil eden class nesnesi döndürülür
        Class<?> objectClass = object.getClass();
        try
        {
            name = (String) objectClass.getDeclaredField("NAME") // Elde edilem nesnenin NAME adında static değişkeni olup olmadığı kontrol edilir.
                    .get(null); // Static değişkenin değerini elde ederiz. Static değişkenlere erişim için 'null' kullanılır
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

        return name;
    }

    // Class'ların ID static değişkenlerine dinamik erişim sağlamak için kullanılan bir method
    private int DynamicAccessToStaticVariableId(Object object)
    {
        int id;

        // Object nesnesinin çalışma zamanındaki sınıfını temsil eden class nesnesi döndürülür
        Class<?> objectClass = object.getClass();
        try
        {
            id = (int) objectClass.getDeclaredField("ID") // Elde edilem nesnenin NAME adında static değişkeni olup olmadığı kontrol edilir.
                    .get(null); // Static değişkenin değerini elde ederiz. Static değişkenlere erişim için 'null' kullanılır
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

        return id;
    }

    public void PressEnter()
    {
        Scanner s = new Scanner(System.in); // Scanner nesnesi oluşturuyoruz.
        System.out.println("Press \"ENTER\" to continue...");
        s.nextLine();
    }

    public void NewLine()
    {
        System.out.print("\n");
    }
    /***************************************/



    /********** Getter & Setter ************/
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public EnemyLocation getEnemyLocation() {
        return enemyLocation;
    }

    public void setEnemyLocation(EnemyLocation enemyLocation) {
        this.enemyLocation = enemyLocation;
    }

    public SafeLocation getSafeLocation() {
        return safeLocation;
    }

    public void setSafeLocation(SafeLocation safeLocation) {
        this.safeLocation = safeLocation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    /*********************************/
}
