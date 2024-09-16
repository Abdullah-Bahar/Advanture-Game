package bahar.com.business.core;

import bahar.com.entity.core.character.Monster;
import bahar.com.entity.core.character.Player;

public interface IAdventure
{
    void Intro();
    void InitializePlayer(String name, int id);
    void History();
    void CreatePlayer(int id);
    void LocationChange(int id);
    void War(Monster monster, Player player);
    void IsGameWin();
    void WinWar();
    void MonsterAttack(Player player, Monster monster);
    void PlayerAttack(Player player, Monster monster);
    int FirstHit();
    void WinGame();
    void GameOver();

    // Print
    void PrintCurrentLocation();
    void PrintSafeLocationInfo(int locationId);
    void PrintEnemyLocationInfo(int locationId);
    void PrintCharacterInfo();

    void PressEnter();
    void NewLine();
}
