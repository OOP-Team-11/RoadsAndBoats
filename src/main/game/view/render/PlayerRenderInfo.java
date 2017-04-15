package game.view.render;

import game.model.Player;

public class PlayerRenderInfo {

    private Player player;
    public PlayerRenderInfo(Player player) {
        this.player = player;
    }

    public String getName() {
        return player.getName();
    }
}
