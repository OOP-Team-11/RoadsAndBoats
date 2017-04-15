package game.view.render;

import game.model.Player;

public class PlayerRenderInfo {

    private Player player;
    public PlayerRenderInfo(Player player) {
        this.player = player;
    }

    // PlayerId is a POD, so LoD doesn't apply here
    public String getName() {
        return "Player " + player.getPlayerId().getPlayerIdNumber();
    }
}
