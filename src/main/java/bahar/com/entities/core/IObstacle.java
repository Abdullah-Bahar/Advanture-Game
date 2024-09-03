package bahar.com.entities.core;

import bahar.com.entities.core.character.Monster;

public interface IObstacle
{
    Monster[] RandomMonster();
    Monster[] CreateMonster(int id);
}
