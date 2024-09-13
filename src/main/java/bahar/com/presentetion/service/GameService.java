package bahar.com.presentetion.service;

import bahar.com.business.service.AdventureService;
import bahar.com.business.service.SafeHouseService;
import bahar.com.business.service.StoreService;
import bahar.com.entity.core.character.Monster;
import bahar.com.entity.core.thing.Armor;
import bahar.com.entity.core.thing.Weapon;
import bahar.com.entity.model.armor.Heavy;
import bahar.com.entity.model.armor.Light;
import bahar.com.entity.model.armor.Medium;
import bahar.com.entity.model.character.Archer;
import bahar.com.entity.model.character.Knight;
import bahar.com.entity.model.character.Samurai;
import bahar.com.entity.model.location.Cave;
import bahar.com.entity.model.location.Forest;
import bahar.com.entity.model.location.River;
import bahar.com.entity.model.location.SafeHouse;
import bahar.com.entity.model.monster.Bear;
import bahar.com.entity.model.monster.Vampire;
import bahar.com.entity.model.monster.Zombie;
import bahar.com.entity.model.weapon.Pistol;
import bahar.com.entity.model.weapon.Rifle;
import bahar.com.entity.model.weapon.Sword;
import bahar.com.exception.InValidIdException;
import bahar.com.presentetion.core.IGame;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameService implements IGame
{
    private AdventureService adventureService;
    private SafeHouseService safeHouseService;
    private StoreService storeService;
    private Scanner input;

    public GameService() {
        this.adventureService = new AdventureService();
        this.storeService = new StoreService();
        input = new Scanner(System.in);
    }

    @Override
    public void GameStart()
    {
        InitializeStart();
    }

    public void InitializeStart()
    {
        String name;
        int nameLength = 15; // Girilen name'nin max uzunkuu ne olmalı
        int characterId = 0;

        // Oyuna giriş
        adventureService.Intro();
        adventureService.PressEnter();


        // Name Kontrolü
        while (true)
        {
            System.out.print("\nLütfen bir kullanıcı adı girin. (Max 15 karakter / Space içermemeli)");
            System.out.print("\nKullanıcı Adı : ");
            name = input.nextLine();

            if (ValidInputName(name, nameLength))
                break;

            System.out.println("HATA : Lütfen tekrar deneyin. Karakter sınırını aştınız veya yanlış giriş yaptınız.");
        }
        adventureService.NewLine();

        // Karakter Kontrolü
        while (true)
        {
            System.out.print("Lütfen bir karakter seçimi yapınız. (Karakter ID'lerine göre tuş tuşlayın) \n");
            //PrintCharecterInfo(); -> presentetiondaki alt alta yazan print methodu
            adventureService.PrintCharacterInfo();
            System.out.print("Seçmek istediğiniz karatkterin ID'si :  ");
            try
            {
                characterId = input.nextInt();
                if (!ValidInputId(characterId, Samurai.ID, Archer.ID, Knight.ID))
                    throw new InValidIdException("\nHATA : Geçersiz ID. Lütfen geçerli bir ID giriniz.\n");
                break; // Geçerli ID girilmemişse buraya gelmeden hata fırlatılacak. Eğer doğru girerse bir sorun olmadan loop'tan çıkacaktır
            }
            catch (InValidIdException e)
            {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nHATA : Geçersiz giriş. Lütfen bir sayı girin.\n");
                input.next(); // Hatalı girdiyi temizler. Eğer bu satırı silersen hatalı girişte döngüye girersin.
            }
        }
        adventureService.NewLine();

        // Oyuncu değer atamaları
        adventureService.InitializePlayer(name, characterId);

        /*
        Bu field'ın atmasını ctor'da yapamadım. Çünkü orada atama yaparken bu nesnenin parametre olarak ihtiyaç
        duyduğu nesneyi buraya attığımda null atmış olurum.
        Player nesnesinin null olmadığı andan initialize etmek için burası vardı
        */
        this.safeHouseService = new SafeHouseService(adventureService.getPlayer());

        // Oyun Hikayesi
        adventureService.History();
        adventureService.NewLine();
        adventureService.PressEnter();

        // oyun başladığında güvenli evden başlayacapı için location ataması yapıyoruz
        adventureService.LocationChange(SafeHouse.ID);
        GoToDoHouse();
    }

    public void ToWhereLocation()
    {
        // Location Haritası : Şuan nerede ve nerelere gidebilir.
        adventureService.PrintCurrentLocation();

        int locationID = adventureService.getLocation().getId(); // Nerede olduğunu gösterir
        int locationDoGoId;
        String soru = "\nLütfen gitmek istediğin yerin ID'sini girer misiniz : (Geri gelmek için 0'rı tuşlayın)\n";

        while (true)
        {
            System.out.print(soru);
            PrintTargetLocation();
            System.out.print("Gitmek istediğiniz yerin ID'si : ");
            try
            {
                locationDoGoId = input.nextInt();
                if (!ValidInputId(locationDoGoId, 0, SafeHouse.ID, Cave.ID, River.ID, Forest.ID) || locationDoGoId == locationID)
                    throw new InValidIdException("HATA : Geçersiz ID. Lütfen geçerli bir ID giriniz.");
                break;
            }
            catch (InValidIdException e)
            {
                System.out.println(e.getMessage());;
            }
            catch (InputMismatchException e)
            {
                System.out.println("HATA : Geçersiz giriş. Lütfen bir sayı girin.");
                input.next(); // Hatalı girdiyi temizler. Eğer bu satırı silersen hatalı girişte döngüye girersin.
            }
        }

        // Eğer sıfırı tuşlarsa olduğu yerde kalmaya devam edecek
        if (locationDoGoId != 0)
            adventureService.LocationChange(locationDoGoId);
        adventureService.NewLine();


        GoToDoLocationNumber(locationDoGoId);
    }

    private void GoToDoLocationNumber(int locationDoGoId)
    {
        switch (locationDoGoId)
        {
            case 0: // Bulunduğu lokasyonda menü - options sayfasına geri dönmek için
                if (adventureService.getLocation().getId() == SafeHouse.ID)
                    SelectedOptionHouseId();
                else
                    SelectedOptionEnemyLocationId();
                break;
            case 1: // Güvenli ev
                adventureService.setSafeLocation(new SafeHouse());
                GoToDoHouse();
                break;
            case 2: // Nehir
                adventureService.setEnemyLocation(new River());
                GoToDoRiver();
                break;
            case 3: // Orman
                adventureService.setEnemyLocation(new Forest());
                GoToDoForest();
                break;
            case 4: // Mağara
                adventureService.setEnemyLocation(new Cave());
                GoToDoCave();
                break;
            default:
                System.out.println("\nLokasyon Değişiminde Bir Hata Oluştu!\n");
        }
    }
    /***********************************/


    /**** LOKASYONA GİDİLDİĞİNE YAPILACAKLAR ****/

    /*********** RIVER *************/
    private void GoToDoCave()
    {
        EntryEnemyLocation(new Zombie());
    }

    /*********** RIVER *************/
    private void GoToDoForest()
    {
        EntryEnemyLocation(new Vampire());
    }

    /*********** RIVER *************/
    private void GoToDoRiver()
    {
        EntryEnemyLocation(new Bear());
    }

    private void EntryEnemyLocation(Monster monster)
    {
        adventureService.getEnemyLocation().WelcomTo(monster);
        adventureService.NewLine();
        adventureService.PressEnter();

        adventureService.setListMonster(adventureService.getEnemyLocation().CreateMonster(monster.getId()));

        adventureService.getEnemyLocation().PrintNumberMonster(adventureService.getListMonster().size());
        adventureService.NewLine();

        SelectedOptionEnemyLocationId();
    }

    private void SelectedOptionEnemyLocationId()
    {
        int selectecId;

        while (true)
        {
            PrintMenuEnemyLocation();
            System.out.print("Seçeneğiniz : ");
            try
            {
                selectecId = input.nextInt();
                if (!(ValidInputId(selectecId, 1,2,3,4)))
                    throw new InValidIdException("\nHATA : Geçersiz Seçenek. Lütfen Tekrar Deneyin!\n");
                break;
            }
            catch (InValidIdException e)
            {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nHATA : Geçersiz giriş. Lütfen bir sayı girin.\n");
                input.next(); // Hatalı girdiyi temizler. Eğer bu satırı silersen hatalı girişte döngüye girersin.
            }
        }
        adventureService.NewLine();
        OptionsEnemyLocation(selectecId);
    }

    private void OptionsEnemyLocation(int selectecId)
    {
        switch (selectecId)
        {
            case 1 : // Savaşma
                War();
                break;
            case 2 : // Kaç canavar kaldı
                if (!adventureService.getListMonster().isEmpty())
                    HowManyMonster();
                else
                    System.out.println("\nCanavarların hepsi öldü\n");
                SelectedOptionEnemyLocationId();
                break;
            case 3 : // Canavar Info
                if (!adventureService.getListMonster().isEmpty())
                    adventureService.getEnemyLocation().InfoMonster(adventureService.getListMonster().get(0));
                else
                    System.out.println("\nÖlen canavarların nesini görmeyi planlıyorsun\n");
                SelectedOptionEnemyLocationId();
                break;
            case 4 : // Harita
                ToWhereLocation();
                break;
        }
    }

    private void War()
    {
        int monsterIndex = adventureService.getListMonster().size() - 1;

        if (monsterIndex < 0)
        {
            System.out.println("Buradaki tüm canavarları öldürdün");
            SelectedOptionEnemyLocationId();
        }
        else
        {

        System.out.println("*********************");
        System.out.println("\n" +
                        "Savaş Başladı..." +
                        "\n");
        System.out.println("*********************");
        adventureService.NewLine();
        adventureService.PressEnter();

        // Savaş başlar
        adventureService.War(adventureService.getListMonster().get(monsterIndex), adventureService.getPlayer());
        // Yendiği canavar listeden kaldırırlır. Yenemez ise oyun biter.
        adventureService.getListMonster().remove(monsterIndex);

        // Tüm canavarları yendiyse ödülünü kazanır.
        if (adventureService.getListMonster().isEmpty())
            adventureService.WinWar();
            adventureService.NewLine();
            adventureService.PressEnter();
        }

        SelectedOptionEnemyLocationId();
    }

    private void PrintMenuEnemyLocation()
    {
        adventureService.NewLine();
        System.out.println("Menü : ");
        String menu =
                " 1) - İçeri Gir ve Savaş\n" +
                        " 2) - Kaç canavar kaldı\n" +
                        " 3) - Canavar Info\n" +
                        " 4) - Harita\n";
        System.out.println(menu);
    }

    private void HowManyMonster()
    {
        adventureService.NewLine();

        if (adventureService.getListMonster().isEmpty())
            System.out.println("Bu bölgede kimse kalmadı.");

        String info =
                "\""+ adventureService.getEnemyLocation().getName() + "\" bölgesinde kalan " +
                        adventureService.getListMonster().get(0).getName() +" : " + adventureService.getListMonster().size();

        System.out.println(info);
    }
    /***********************************/



    /*********** SAFE HOUSE *************/
    private void GoToDoHouse()
    {
        // Safe House'ye geliş mesajı
        safeHouseService.WelcomeToHouse();
        adventureService.PressEnter();

        // Oyun kazanıp kazanmadığının konstrolü
        adventureService.IsGameWin();

        // Canı azsa doldururuluyor
        if (adventureService.getPlayer().getHealth() != adventureService.getPlayer().getMaxHealth()) {
            safeHouseService.HealthRenewal();
            adventureService.PressEnter();
        }

        SelectedOptionHouseId();
    }

    private void SelectedOptionHouseId()
    {
        int selectecId;

        while (true)
        {
            PrintOptionsHouse();
            System.out.print("Seçeneğiniz : ");
            try
            {
                selectecId = input.nextInt();
                if (!(ValidInputId(selectecId, 1,2,3,4)))
                    throw new InValidIdException("HATA : Geçersiz Seçenek. Lütfen Tekrar Deneyin!");
                break;
            }
            catch (InValidIdException e)
            {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e)
            {
                System.out.println("HATA : Geçersiz giriş. Lütfen bir sayı girin.");
                input.next(); // Hatalı girdiyi temizler. Eğer bu satırı silersen hatalı girişte döngüye girersin.
            }
        }
        adventureService.NewLine();
        OptionsHouse(selectecId);
    }

    private void OptionsHouse(int Id)
    {
        switch (Id)
        {
            case 1: // Karakter
                safeHouseService.PrintCharacterStatus();
                SelectedOptionHouseId();
                break;
            case 2: // Encanter
                safeHouseService.ShowEnvanter();
                SelectedOptionHouseId();
                break;
            case 3: // Mağaza
                GoToDoStore();
                break;
            case 4: // Harita
                ToWhereLocation();
                break;
        }
    }

    private void PrintOptionsHouse()
    {
        adventureService.NewLine();
        System.out.println("Menü : ");
        String options =
                " 1) - Karakter\n" +
                        " 2) - Envanter\n" +
                        " 3) - Mağaza\n" +
                        " 4) - Harita\n";
        System.out.println(options);
    }
    /***********************************/


    /*********** STORE *************/
    private void GoToDoStore() {
        storeService.WelcomeToStore();
        adventureService.PressEnter();

        SelectedCategoriesStoreId();
    }

    // Kategori sayfası menüsü için
    private void SelectedCategoriesStoreId()
    {
        int selectedId;

        selectedId = getSelectedId(storeService::PrintCategories,0,1,2);

        switch (selectedId)
        {
            case 0: // Bir önceki sayfaya döner
                GoToDoHouse();
                break;
            case 1: // Zırhlar içim
                SelectedArmorStoreId();
                break;
            case 2: // Silahlar için
                SelectedWeaponStoreId();
                break;
        }
    }

    // Kategori olan Weapon ürünleri sayfası
    private void SelectedWeaponStoreId()
    {
        int selectedId;

        selectedId = getSelectedId(storeService::PrintWeepon,0, Pistol.ID, Rifle.ID, Sword.ID);
        switch (selectedId)
        {
            case 0: // Bir önceki sayfaya döner
                SelectedCategoriesStoreId();
                break;
            case Pistol.ID: // Zırhlar içim
                BuyWeapoonById(Pistol.ID);
                break;
            case Rifle.ID: // Silahlar için
                BuyWeapoonById(Rifle.ID);
                break;
            case Sword.ID: // Silahlar için
                BuyWeapoonById(Sword.ID);
                break;
        }

        // İşlemlerden sonra aynı sayfada kalması için
        SelectedWeaponStoreId();
    }

    // Kategori olan Armor ürünleri sayfası
    private void SelectedArmorStoreId()
    {
        int selectedId;

        selectedId = getSelectedId(storeService::PrintArmor,0, Heavy.ID, Light.ID, Medium.ID);
        switch (selectedId)
        {
            case 0: // Bir önceki sayfaya döner
                SelectedCategoriesStoreId();
                break;
            case Heavy.ID: // Zırhlar içim
                BuyArmorById(Heavy.ID);
                break;
            case Medium.ID: // Silahlar için
                BuyArmorById(Medium.ID);
                break;
            case Light.ID: // Silahlar için
                BuyArmorById(Light.ID);
                break;
        }

        // İşlemlerden sonra aynı sayfada kalması için
        SelectedArmorStoreId();
    }

    private void BuyWeapoonById(int id) {
        Weapon weapon = storeService.BuyWeepon(id);
        boolean doYouHaveMoney = storeService.CheckMoney(adventureService.getPlayer().getMoney(), weapon.getPrice());

        // Parası çıkışmaz ise
        if (!doYouHaveMoney)
        {
            System.out.println("HATA : Satın alma işleminiz gerçekleşemedi. Yetersiz Bakiye.");
            System.out.println("Mevcut Bakiyeniz : " + adventureService.getPlayer().getMoney());
            System.out.println("Almak istediğiniz Ürün Fiyatı : " + weapon.getPrice());
            adventureService.NewLine();
            return;
        }

        // Ürün zaten mevcut ise
        if (!(adventureService.getPlayer().getEnvanter().getWeapon() == null) &&
                adventureService.getPlayer().getEnvanter().getWeapon().getId() == weapon.getId())
        {
            System.out.println("HATA : Satın alma işleminiz gerçekleşemedi. Ürün zaten mevcut.");
            System.out.println("Almak istediğiniz başka bir ürün seçebilirsiz");
            return;
        }

        // Oyuncunun parası eksildi
        adventureService.getPlayer().setMoney(adventureService.getPlayer().getMoney() - weapon.getPrice());
        // Oyuncunun envanterine armor eklendi
        adventureService.getPlayer().getEnvanter().setWeapon(weapon);
        // Oyuncu saldırı güncellendi
        adventureService.getPlayer().setDamage(adventureService.getPlayer().getDamage() + weapon.getDmage());

        // Ürün hakkında ve kalan bakiye hakkında bilgilendirme
        System.out.println("Tebrikler... Satım alma işleminin gerçekleşti.");
        System.out.println("Aldığını ürün : " + adventureService.getPlayer().getEnvanter().getWeapon().getName());
        System.out.println("Mevcut Bakiyeniz : " + adventureService.getPlayer().getMoney());
        adventureService.NewLine();
    }

    private void BuyArmorById(int id) {

        Armor armor = storeService.BuyArmor(id);
        boolean doYouHaveMoney = storeService.CheckMoney(adventureService.getPlayer().getMoney(), armor.getPrice());

        // Parası çıkışmaz ise
        if (!doYouHaveMoney)
        {
            System.out.println("HATA : Satın alma işleminiz gerçekleşemedi. Yetersiz Bakiye.");
            System.out.println("Mevcut Bakiyeniz : " + adventureService.getPlayer().getMoney());
            System.out.println("Almak istediğiniz Ürün Fiyatı : " + armor.getPrice());
            adventureService.NewLine();
            return;
        }

        // Ürün zaten mevcut ise
        if (!(adventureService.getPlayer().getEnvanter().getArmor() == null)
                && adventureService.getPlayer().getEnvanter().getArmor().getId() == armor.getId())
        {
            System.out.println("HATA : Satın alma işleminiz gerçekleşemedi. Ürün zaten mevcut.");
            System.out.println("Almak istediğiniz başka bir ürün seçebilirsiz");
            return;
        }

        // Oyuncunun parası eksildi
        adventureService.getPlayer().setMoney(adventureService.getPlayer().getMoney() - armor.getPrice());
        // Oyuncunun envanterine armor eklendi
        adventureService.getPlayer().getEnvanter().setArmor(armor);
        // Oyuncu saldırı güncellendi
        adventureService.getPlayer().setBlok(armor.getBlock());

        // Ürün hakkında ve kalan bakiye hakkında bilgilendirme
        System.out.println("Tebrikler... Satım alma işleminin gerçekleşti.");
        System.out.println("Aldığını ürün : " + adventureService.getPlayer().getEnvanter().getArmor().getName());
        System.out.println("Mevcut Bakiyeniz : " + adventureService.getPlayer().getMoney());
        adventureService.NewLine();
    }

    // Menülerin listelenmesi - İlgili menüye gitmek için tıklanan tışun kontrolü
    private int getSelectedId(Runnable printAction, int... validIds) {
        int selectedId;
        while (true)
        {
            System.out.println("Lütfen Seçim Yapınız : (Geri Çıkmak İçin 0'ı tışlayınız)");
            printAction.run();
            System.out.print("Seçeneğiniz : ");
            try
            {
                selectedId = input.nextInt();
                if (!(ValidInputId(selectedId, validIds)))
                    throw new InValidIdException("\nHATA : Geçersiz Seçenek. Lütfen Tekrar Deneyin!\n");
                break;
            }
            catch (InValidIdException e)
            {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nHATA : Geçersiz giriş. Lütfen bir sayı girin.\n");
                input.next(); // Hatalı girdiyi temizler. Eğer bu satırı silersen hatalı girişte döngüye girersin.
            }
        }
        adventureService.NewLine();
        return selectedId;
    }
    /***********************************/



    /************ Print işlemleri **************/
    // Gidilecek yerler
    private void PrintTargetLocation()
    {
        // Güvenli evde değilse -> diğer düşman alanları ve güvenli ev gözükecek
        if (adventureService.getLocation().getId() != SafeHouse.ID)
            adventureService.PrintSafeLocationInfo(adventureService.getLocation().getId());

        // Güvenli evdeyse sadece düşman alanları gözükecek
        adventureService.PrintEnemyLocationInfo(adventureService.getLocation().getId());
    }

    // Burası offline
    /*
    // Safe - Enemy - Print
    private void PrintSafeLocationInfo(int locationId)
    {
        if (locationId != SafeHouse.ID)
            CreateSafeLocationInfo(SafeHouse.ID, SafeHouse.NAME);
    }

    private void CreateSafeLocationInfo(int id, String name)
    {
        String cizgi = "\n-----------------------------------\n";
        String info =
                cizgi +
                        "ID = " + id + "\n" +
                        "NAME = " + name + "\n" +
                        cizgi;

        System.out.println(info);
    }

    private void PrintEnemyLocationInfo(int locationId)
    {
        if (locationId != River.ID)
            CreateEnemyLocationInfo(River.ID, River.NAME, River.CREATE_MONSTER_NAME, River.PRIZE, River.prize);
        if (locationId != Forest.ID)
            CreateEnemyLocationInfo(Forest.ID, Forest.NAME, Forest.CREATE_MONSTER_NAME, Forest.PRIZE, Forest.prize);
        if (locationId != Cave.ID)
            CreateEnemyLocationInfo(Cave.ID, Cave.NAME, Cave.CREATE_MONSTER_NAME, Cave.PRIZE, Cave.prize);
    }

    private void CreateEnemyLocationInfo(int id, String name, String monsterName, String prizeName, boolean kosul)
    {
        String cizgi = "\n-----------------------------------\n";
        String info =
                cizgi +
                        "ID = " + id + "\n" +
                        "NAME = " + name + "\n" +
                        "ÇIKACAK CANAVAR = " + monsterName + "\n" +
                        "ÖDÜL = " + prizeName + " " + (kosul ? "(ALINDI)" : "") +
                        cizgi;

        System.out.println(info);
    }

    // Character - Print
    private void PrintCharecterInfo()
    {
        CreateCharecterInfo(Samurai.ID, Samurai.NAME, Samurai.HEALTH, Samurai.DEMAGE, Samurai.MONEY);
        CreateCharecterInfo(Archer.ID, Archer.NAME, Archer.HEALTH, Archer.DEMAGE, Archer.MONEY);
        CreateCharecterInfo(Knight.ID, Knight.NAME, Knight.HEALTH, Knight.DEMAGE, Knight.MONEY);
    }

    private void CreateCharecterInfo(int id, String name, int health, int damage, int money)
    {
        String cizgi = "\n-----------------------------------\n";
        String info =
                cizgi +
                "ID = " + id + "\n" +
                "NAME = " + name + "\n" +
                "CAN = " + health + "\n" +
                "HASAR = " + damage + "\n" +
                "PARA = " + money +
                cizgi;

        System.out.println(info);
    }

     */
    /***********************************/



    /*************  VALID İŞLEMLERİ  ***************/
    private boolean ValidInputName(String name, int length)
    {
        return name != null &&  // Girilen ismin null olup olmamasını kontrol eder. (hiçbir şey zmadan enter'a tıklarsa bunu engeller)
                !name.trim().isEmpty() &&   // Girilen kelimenin balındaki ve sonundaki boşluklaru kaldırır. geri kalan yer de boşsa geçersiz sayar.
                name.length() <= length &&  // Uzunluğunu kontrol eder
                !name.contains(" ");        // Kelime içerisinde boşluk olmamasını kontrol eder
    }

    // Girilen ID'nin geçerli ID'ler üzerinden olup olmadığını kontrol eder
    private boolean ValidInputId(int inputId, int... validId)
    {
        for (int i = 0; i < validId.length; i++)
            if (inputId == validId[i])
                return true;
        return false;
    }
    /***********************************/
}
