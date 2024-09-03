package bahar.com.business.core;

import bahar.com.entities.core.character.Monster;
import bahar.com.entities.core.character.Player;

public interface IGame
{
    void Intro();
    void Initialize(String name, int id);
    void History();
    void CreatePlayer(int id);
    void LocationChange(int id);
    void initializeWar();
    void War(Player player, Monster monster);
    int FirstHit();
    void GameOver();
    void Araf();

}
