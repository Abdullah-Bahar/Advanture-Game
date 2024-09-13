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


    // Buradan aşağısı düzenlenme gerek
    void War(Monster monster, Player player);
}
