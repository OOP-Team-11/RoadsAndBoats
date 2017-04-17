package game.view.render;

import game.model.Player;
import game.model.PlayerId;

public class PlayerRenderInfo {

    private Player player;
    public PlayerRenderInfo(Player player) {
        this.player = player;
    }

    public String getName() {
        return player.getName();
    }
    public PlayerId getPlayerID(){
        return player.getPlayerId();
    }

}
