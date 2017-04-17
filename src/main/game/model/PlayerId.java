package game.model;

import java.util.Objects;

public class PlayerId {
    private int playerId;
    public PlayerId(int id) {
        this.playerId = id;
    }
    public int getPlayerIdNumber() {
        return playerId;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlayerId)) return false;
        PlayerId playerId = (PlayerId) object;
        return playerId.getPlayerIdNumber() == this.getPlayerIdNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPlayerIdNumber());
    }
}
