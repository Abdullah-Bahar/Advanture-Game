package bahar.com.presentetion.core;

import bahar.com.entity.core.character.Monster;

public interface IGame
{
    void GameStart();
    void InitializeStart();
    void ToWhereLocation();
    void GoToDoLocationNumber(int locationDoGoId);

    /** Düşman Bölgesine Gidildiğinde **/
    void GoToDoCave();
    void GoToDoForest();
    void GoToDoRiver();
    void EntryEnemyLocation(Monster monster);
    void SelectedOptionEnemyLocationId();
    void OptionsEnemyLocation(int selectecId);
    void War();
    void PrintMenuEnemyLocation();
    void HowManyMonster();

    /** Güvenli Bölgeye Gidildiğinde **/
    void GoToDoHouse();
    void SelectedOptionHouseId();
    void OptionsHouse(int Id);
    void PrintOptionsHouse();

    /** Mağazaya Gidildiğinde **/
    void GoToDoStore();
    void SelectedCategoriesStoreId();
    void SelectedWeaponStoreId();
    void SelectedArmorStoreId();
    void BuyWeapoonById(int id);
    void BuyArmorById(int id);
    int getSelectedId(Runnable printAction, int... validIds);
}
