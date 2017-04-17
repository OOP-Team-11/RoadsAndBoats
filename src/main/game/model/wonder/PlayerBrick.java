package game.model.wonder;
import game.model.PlayerId;

public class PlayerBrick extends WonderBrick {
    private PlayerId pid;

    @Override
    public boolean isNeutral() {
        return false;
    }

    public PlayerBrick(PlayerId playerId){
        this.pid = playerId;
    }

    public PlayerId getPlayerId() {
        return pid;
    }

}
