package bahar.com.business.service;

import bahar.com.business.core.IAdventure;
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

    @Override // TAMAMM
    public void Intro() {
        System.out.println("""
                Merhabalar...\s
                Macera oyunumuza hoş geldiniz!\s
                Adadan kurtulmak için canavarların olduğu bölgelerden gereken eşyaları yoplamalısın.\s
                Tehlikeli bölgelerden eşyaları toplayabilmen için tüm canavarları temizlemelisin.\s
                Eğer canavarları temizlerken ölürsen oyunu kaybedersin.\s
                Tekrar baştan almak istemiyorsan dikkatli olmalısın. Unutma ki hayatta ikinci şanslar asla olmaz.\s
                Bu oyunu kazanmak istiyorsan gerçek hayatta olduğunu unutmamalısın.\s
                Eğer başlamaya hazırsan enter tuşuna basarak devam edebilirsin.\s
                İyi oyunlar... Hayatta kalmayı unutma!\s
                """);
    }

    @Override // TAMAMM
    public void Initialize(String userName, int id)
    {
        // Oyuncu Adı Ataması
        setUserName(userName);

        // Oyuncu karakter ataması
        CreatePlayer(id);

        // Oyun başlangıcıdında güvenli ev'den doğacağı için direkt olarak location atmasını orya yapıyoruz
        LocationChange(SafeHouse.ID);
    }

    @Override // TAMAMM
    public void History()
    {
        String name = DynamicAccessToStaticVariableName(getPlayer());

        System.out.println(
                "İmparatorluk, giderek artan düşman tehditleriyle zor günler yaşıyordu. İmparator, imparatorluğun en cesur savaşçıları olan Okçu , Şövalye ve Samuray'dan oluşan bir ekibi; \n" +
                        " gizemli bir görev için uzak diyarlara gönderdi. Onları taşıyan gemi, fırtınalı bir gece aniden şiddetli bir kasırgaya yakalandı. Dalga dalga gelen saldırılar, \n" +
                        "geminin direklerini kırdı, gövdesini paramparça etti. Geminin güvertesi suya gömülürken, savaşçılar hayatta kalma mücadelesi vermekteydi. \n" +
                        "\n" +
                        "Fırtına nihayet dindiğinde, güneş yeniden doğarken, " + name + " " + getUserName() + " gözlerini bilinmeyen bir adada açtı. \n" +
                        "Kendini yorgun ve yalnız hissediyordu. Arkadaşlarından hiçbir iz yoktu \n" +
                        "Birkaç adım attıktan sonra geminin kalıntılarını ve etrafa saçılmış birkaç eşyayı gördü. Zihninde tek bir şey yankılanıyordu: \n" +
                        "\"İmparatorluğuma geri dönmeliyim.\"\n" +
                        "Ancak bilmesi gerekn bir şey vardı. Bu ada canavarlarla dolu bir adaydı ve buradan kurtulmak için onlarla savaşıp 3 temel eşyayı toplaması gerekiyordu \n" +
                        "Odun, Yemek, Su... \n");
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

    /*
    private void IlaEyneAfterWar(int eLocId)
    {
        Scanner input = new Scanner(System.in);
        int nereye;
        String s;

        s = "Nereye Gitmek Istersin : (Gitmek istediğin numaraya tıkla) \n";
        s += SafeHouse.ID + ")" + SafeHouse.NAME + "'e gider\n";
        if (River.ID != eLocId) {
            s += River.ID + ")" + River.NAME + "'e gider\n";
        }
        if (Forest.ID != eLocId) {
            s += Forest.ID + ")" + Forest.NAME + "'a gider\n";
        }
        if (Cave.ID != eLocId) {
            s +=  Cave.ID + ")" + Cave.NAME + "ya gider\n";
        }
    }
     */

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

    // Tamamlandı
    public void PressEnter()
    {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner s = new Scanner(System.in); // Scanner nesnesi oluşturuyoruz.
        s.nextLine();
    }

    // Tamamlandı
    public void NewLine()
    {
        System.out.println("\n\n");
    }


    // -------- Getter & Setter -------------
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
    //---------------------------
}
