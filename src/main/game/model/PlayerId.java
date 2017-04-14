package game.model;

public class PlayerId {
    private static int currentPlayerId = 1;

    private int playerId;
    public void PlayerId() {
        this.playerId = currentPlayerId;
        currentPlayerId++;
    }

    public int getPlayerIdNumber() {
        return playerId;
    }
}
