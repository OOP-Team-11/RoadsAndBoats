package game.model;

public class PlayerId {


    private static int currentPlayerId = 1;

    private int playerId;
    public PlayerId(int id) {
        this.playerId = id;
    }
    public int getPlayerIdNumber() {
        return playerId;
    }
}
