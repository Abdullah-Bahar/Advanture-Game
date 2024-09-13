package bahar.com.entity.core;

import bahar.com.entity.core.character.Monster;

import java.util.List;

public interface IObstacle
{
    List<Monster> CreateMonster(int id);
    void WelcomTo(Monster monster);
    void InfoMonster(Monster monster);
    void PrintNumberMonster(int num);
}
