package game.view.render;

import game.model.PlayerId;

public class WonderBrickRenderInfo {

    private PlayerId playerId;
    public WonderBrickRenderInfo(PlayerId playerId) {
        this.playerId = playerId;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }
}
