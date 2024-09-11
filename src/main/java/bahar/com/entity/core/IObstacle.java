package bahar.com.entity.core;

import bahar.com.entity.core.character.Monster;

public interface IObstacle
{
    Monster[] RandomMonster();
    Monster[] CreateMonster(int id);
    void WelcomTo(Monster monster);
}
