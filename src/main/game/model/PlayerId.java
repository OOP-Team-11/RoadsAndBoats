package game.model;

public class PlayerId {

    private static int currentPlayerId = 1;

    private int playerId;
    PlayerId() {
        this.playerId = currentPlayerId;
        currentPlayerId++;
    }
    public PlayerId(int num){

    }
    public int getPlayerIdNumber() {
        return playerId;
    }
}
