package bahar.com.business.core;

import bahar.com.entity.core.thing.Armor;
import bahar.com.entity.core.thing.Weapon;

public interface IStore
{
    void WelcomeToStore();
    void PrintCategories();
    Armor BuyArmor(int armorId);
    Weapon BuyWeepon(int weaponId);
    boolean CheckMoney(int money, int price);
    void PrintArmor();
    void PrintWeepon();
}
