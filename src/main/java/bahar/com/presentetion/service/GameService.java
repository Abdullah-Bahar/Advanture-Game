package bahar.com.presentetion.service;

import bahar.com.business.service.AdventureService;
import bahar.com.business.service.SafeHouseService;
import bahar.com.entity.model.character.Archer;
import bahar.com.entity.model.character.Knight;
import bahar.com.entity.model.character.Samurai;
import bahar.com.entity.model.location.Cave;
import bahar.com.entity.model.location.Forest;
import bahar.com.entity.model.location.River;
import bahar.com.entity.model.location.SafeHouse;
import bahar.com.entity.model.monster.Zombie;
import bahar.com.exception.InvalidCharacterIdException;
import bahar.com.presentetion.core.IGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameService implements IGame
{
    private AdventureService adventureService;
    private SafeHouseService safeHouseService;
    private Scanner input;

    public GameService() {
        this.adventureService = new AdventureService();
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
                    throw new InvalidCharacterIdException("\nHATA : Geçersiz ID. Lütfen geçerli bir ID giriniz.\n");
                break; // Geçerli ID girilmemişse buraya gelmeden hata fırlatılacak. Eğer doğru girerse bir sorun olmadan loop'tan çıkacaktır
            }
            catch (InvalidCharacterIdException e)
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
        String soru = "\nLütfen gitmek istediğin yerin ID'sini girer misiniz : (Gitmek istediğiniz yerin ID'sini tuşlayın)\n";

        while (true)
        {
            System.out.print(soru);
            PrintTargetLocation();
            System.out.print("Gitmek istediğiniz yerin ID'si : ");
            try
            {
                locationDoGoId = input.nextInt();
                if (!(ValidInputId(locationDoGoId, SafeHouse.ID, Cave.ID, River.ID, Forest.ID) || locationDoGoId == locationID))
                    throw new InvalidCharacterIdException("HATA : Geçersiz ID. Lütfen geçerli bir ID giriniz.");
                break;
            }
            catch (InvalidCharacterIdException e)
            {
                System.out.println(e.getMessage());;
            }
            catch (InputMismatchException e)
            {
                System.out.println("HATA : Geçersiz giriş. Lütfen bir sayı girin.");
                input.next(); // Hatalı girdiyi temizler. Eğer bu satırı silersen hatalı girişte döngüye girersin.
            }
        }

        adventureService.LocationChange(locationDoGoId);
        adventureService.NewLine();


        GoToDoLocationNumber(locationDoGoId);
    }

    private void GoToDoLocationNumber(int locationDoGoId)
    {
        switch (locationDoGoId)
        {
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
    private void GoToDoCave() {
    }

    private void GoToDoForest() {
    }

    private void GoToDoRiver() {
    }


    /*********** SAFE HOUSE *************/
    private void GoToDoHouse()
    {
        // Safe House'ye geliş mesajı
        safeHouseService.WelcomeToHouse();

        // Canı azsa doldururuluyor
        if (adventureService.getPlayer().getHealth() != adventureService.getPlayer().getMaxHealth())
            safeHouseService.HealthRenewal();

        SelectedOptionId();
    }

    private void SelectedOptionId()
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
                    throw new InvalidCharacterIdException("HATA : Geçersiz Seçenek. Lütfen Tekrar Deneyin!");
                break;
            }
            catch (InvalidCharacterIdException e)
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
                SelectedOptionId();
            case 2: // Encanter
                safeHouseService.ShowEnvanter();
                SelectedOptionId();
            case 3: // Mağaza

            case 4: // Harita
                ToWhereLocation();
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



    /*************  DİĞER İŞLEMLER  ***************/
    /***********************************/

}
