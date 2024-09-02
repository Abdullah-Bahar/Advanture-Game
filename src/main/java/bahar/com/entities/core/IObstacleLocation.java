package bahar.com.entities.core;

import bahar.com.entities.core.character.Monster;

public interface IObstacleLocation extends IObstacle
{
    Monster[] CreateMonster();
}
