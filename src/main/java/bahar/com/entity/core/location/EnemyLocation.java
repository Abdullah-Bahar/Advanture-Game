package bahar.com.entity.core.location;

import bahar.com.entity.core.IObstacle;
import bahar.com.entity.core.character.Monster;
import bahar.com.entity.model.monster.Bear;
import bahar.com.entity.model.monster.Vampire;
import bahar.com.entity.model.monster.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyLocation extends Location implements IObstacle
{
    private boolean isPrize;
    private String prize;  // Ödül adı

    public EnemyLocation(String name, int id, boolean isPrize, String prize)
    {
        super(name, id);
        this.isPrize = isPrize;
        this.prize = prize;
    }


    @Override
    public List<Monster> CreateMonster(int id)
    {
        // 1-3 arasında rastgele sayıda Monster oluşturur.
        Random r = new Random();
        int randomNumber = r.nextInt(3) + 1;

        // RandomMonster ile oluşturulan diziye nesneleri atanması
        List<Monster> monsters = new ArrayList<>();

        switch (id)
        {
            case Zombie.ID:
                for (int i = 0; i < randomNumber; i++)
                    monsters.add(new Zombie());
                break;
            case Vampire.ID:
                for (int i = 0; i < randomNumber; i++)
                    monsters.add(new Vampire());
                break;
            case Bear.ID:
                for (int i = 0; i < randomNumber; i++)
                    monsters.add(new Bear());
                break;
        }

        return monsters;
    }

    @Override
    public void WelcomTo(Monster monster)
    {
        String yildiz = "\n**********************************\n";
        String welcome = "\n" + this.getName() +" bölgesine hoş geldin\n" +
                "\n" +
                "Bu bölgede \"" + monster.getName()  + "\" görülmektedir\n" +
                (!this.isPrize() ? "Eğer içerideki tüm canavarları öldürürsen \"" + this.getPrize()  + "\" ödülünü alabilirsin."
                        : "Buradaki \"" + this.getPrize() + "\" ödülünü zaten almışsın") + "\n" +
                "Eğer sesleri dikkatli dinlersen içeride kaç tane " + monster.getName() + " olduğunu öğrenebilirsin\n";

        System.out.println(yildiz + welcome + yildiz);
    }

    @Override
    public void InfoMonster(Monster monster)
    {
        System.out.println("Canavar Bilgisi : ");

        // Tablo başlığını oluşturuyoruz
        System.out.format("+-------------+----------+\n");

        // Karakter bilgilerini tablo formatında yazdırıyoruz
        System.out.format("| %-11s | %-8d |\n", "ID", monster.getId());
        System.out.format("| %-11s | %-8s |\n", "AD", monster.getName());
        System.out.format("| %-11s | %-8d |\n", "CAN", monster.getHealth());
        System.out.format("| %-11s | %-8d |\n", "HASAR", monster.getDamage());
        System.out.format("| %-11s | %-8d |\n", "PARA", monster.getMoney());

        // Tablo alt çizgisini oluşturuyoruz
        System.out.format("+-------------+----------+\n");
    }

    @Override
    public void PrintNumberMonster(int num)
    {
        System.out.println("İçeriden gelen seslere bakacak olursak içeride " + num + " tane canavar olabilir\n" +
                "Dikkatli ol!");
    }


    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public boolean isPrize() {
        return isPrize;
    }

    public void setPrize(boolean prize) {
        isPrize = prize;
    }
}
