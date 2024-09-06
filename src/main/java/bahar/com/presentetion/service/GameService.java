package bahar.com.presentetion.service;

import bahar.com.business.service.AdventureService;
import bahar.com.entity.model.character.Archer;
import bahar.com.entity.model.character.Knight;
import bahar.com.entity.model.character.Samurai;
import bahar.com.entity.model.location.Cave;
import bahar.com.entity.model.location.Forest;
import bahar.com.entity.model.location.River;
import bahar.com.entity.model.location.SafeHouse;
import bahar.com.entity.model.monster.Zombie;
import bahar.com.presentetion.core.IGame;

import java.util.Scanner;

public class GameService implements IGame
{
    private AdventureService adventureService;
    private Scanner input;

    public GameService() {
        this.adventureService = new AdventureService();
        input = new Scanner(System.in);
    }

    @Override
    public void GameStart()
    {
        // karakter seçimi ve isim girilmesi
        InitializeStart();

        // Location değişimi gerçekleşiyor
        ToWhere();
    }

    public void InitializeStart() {
        String name;
        int nameLength = 15; // Girilen name'nin max uzunkuu ne olmalı
        int characterId;

        // Oyuna giriş
        adventureService.Intro();

        // Name Kontrolü
        while (true)
        {
            System.out.println("\nLütfen bir kullanıcı adı girin : (Max 15 karakter)\n");
            name = input.nextLine();

            if (ValidInputName(name, nameLength))
                break;

            System.out.println("\nLütfen tekrar deneyin. Karakter sınırını aştınız!\n");
            NewLine();
        }
        NewLine();

        while (true)
        {
            System.out.println("Lütfen bir karakter seçimi yapınız : (Karakte ID'lerine göre tuş tuşlayın) \n");
            PrintCharecterInfo();

            characterId = input.nextInt();
            if (ValidInputId(characterId, Samurai.ID, Archer.ID, Knight.ID))
                break;

            System.out.println("\nLütfen tekrar deneyin. Karakter sınırını aştınız!\n");
            NewLine();
        }
        NewLine();

        // Oyuncu değer atamaları
        adventureService.Initialize(name, characterId);

        // Oyun Hikayesi
        adventureService.History();
        NewLine();
    }

    private void ToWhere()
    {
        // Location Haritası : Şuan nerede ve nerelere gidebilir.
        PrintLocation();

        int locationID = adventureService.getLocation().getId(); // Nerede olduğunu gösterir
        int locationDoGoId;
        String soru = "\nLütfen gitmek istediğin yerin ID'sini girer misin : (Gitmek istediğiniz yerin ID'sini tuşlayın)\n";
        String hata = "\nLütfen tekrar deneyiniz. Geçersiz sayı tuşlandı!\n";

        while (true)
        {
            System.out.print(soru);
            locationDoGoId = input.nextInt();

            if (ValidInputId(locationDoGoId, SafeHouse.ID, Cave.ID, River.ID, Forest.ID) && locationDoGoId != locationID)
                break;

            adventureService.LocationChange(locationDoGoId);
        }
        NewLine();
    }


    /************ Print işlemleri **************/

    // Location - Print
    private void PrintLocation()
    {
        PrintCurrentLocation();

        // Güvenli evde değilse -> diğer düşman alanları ve güvenli ev gözükecek
        if (adventureService.getLocation().getId() != SafeHouse.ID)
            PrintSafeLocationInfo(adventureService.getLocation().getId());

        // Güvenli evdeyse sadece düşman alanları gözükecek
        PrintEnemyLocationInfo(adventureService.getLocation().getId());
    }

    // Mevcut konum
    private void PrintCurrentLocation()
    {
        String cizgi = "\n-----------------------------------\n";
        String info =
                cizgi +
                        "Bulunduğun Yer : \n" +
                        "ID = " + adventureService.getEnemyLocation().getId() + "\n" +
                        "NAME = " + adventureService.getEnemyLocation().getName() + "\n" +
                        cizgi;

        System.out.println(info);
    }

    // Gidilecek yerler
    private void PrintSafeLocationInfo(int locationId)
    {
        if (locationId != SafeHouse.ID)
        {
            CreateSafeLocationInfo(SafeHouse.ID, SafeHouse.NAME);
            NewLine();
        }
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
        if (locationId != Cave.ID)
        {
            CreateEnemyLocationInfo(Cave.ID, Cave.NAME, Cave.CREATE_MONSTER_NAME, Cave.PRIZE, Cave.prize);
            NewLine();
        }
        if (locationId != Forest.ID)
        {
            CreateEnemyLocationInfo(Forest.ID, Forest.NAME, Forest.CREATE_MONSTER_NAME, Forest.PRIZE, Forest.prize);
            NewLine();
        }
        if (locationId != River.ID)
        {
            CreateEnemyLocationInfo(River.ID, River.NAME, River.CREATE_MONSTER_NAME, River.PRIZE, River.prize);
            NewLine();
        }

    }

    private void CreateEnemyLocationInfo(int id, String name, String monsterName, String prizeName, boolean kosul)
    {
        String cizgi = "\n-----------------------------------\n";
        String info =
                cizgi +
                        "ID = " + id + "\n" +
                        "NAME = " + name + "\n" +
                        "ÇIKACAK CANAVAR = " + monsterName + "\n" +
                        "ÖDÜL = " + prizeName + "\n" + (kosul ? "(ALINDI)" : "") +
                        cizgi;

        System.out.println(info);
    }

    // Character - Print
    private void PrintCharecterInfo()
    {
        CreateCharecterInfo(Samurai.ID, Samurai.NAME, Samurai.HEALTH, Samurai.DEMAGE, Samurai.MONEY);
        NewLine();

        CreateCharecterInfo(Archer.ID, Archer.NAME, Archer.HEALTH, Archer.DEMAGE, Archer.MONEY);
        NewLine();

        CreateCharecterInfo(Knight.ID, Knight.NAME, Knight.HEALTH, Knight.DEMAGE, Knight.MONEY);
        NewLine();
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


    /*************  VALID İŞLEMLERİ  ***************/

    // Girilen NAME'nin uzunluğunu kontrol eder
    private boolean ValidInputName(String name, int length)
    {
        return name.length() <= length;
    }

    // Girilen ID'nin doğruluğunu konstrol eder
    private boolean ValidInputId(int inputId, int... validId)
    {
        for (int i = 0; i < validId.length; i++)
            if (inputId == validId[i])
                return true;
        return false;
    }



    /*************  DİĞER İŞLEMLER  ***************/

    private void NewLine()
    {
        System.out.println("\n");
    }
}
