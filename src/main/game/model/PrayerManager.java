package game.model;

import java.util.ArrayList;

public class PrayerManager
{
    private final ArrayList<PlayerId> playerIds;

    public PrayerManager(ArrayList<PlayerId> playerIds)
    {
        if(playerIds.size()<2)
        {
            throw new IllegalArgumentException("At least two players are required for a tinyGame.");
        }

        this.playerIds=playerIds;
    }

    public PlayerId getFirstPlayer()
    {
        return playerIds.get(0);
    }

    public void cashInPiety(PlayerId pid)
    {
        if(!playerIds.contains(pid))
        {
            throw new IllegalArgumentException("The specified player is not in the prayer line");
        }
        else
        {
            playerIds.remove(pid);
            playerIds.add(pid);
        }
    }
}
