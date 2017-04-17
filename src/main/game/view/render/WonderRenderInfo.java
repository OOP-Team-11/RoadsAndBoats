package game.view.render;

import game.model.Player;
import game.model.PlayerId;
import game.model.wonder.PlayerBrick;
import game.model.wonder.WonderBrick;
import game.model.wonder.WonderManager;

import java.util.ArrayList;
import java.util.List;

public class WonderRenderInfo {

    private WonderManager wonderManager;
    public WonderRenderInfo(WonderManager wonderManager) {
        this.wonderManager = wonderManager;
    }

    public int getWealthPoints(PlayerId playerId) {
        return wonderManager.getWealthPoints(playerId);
    }

    public List<WonderBrickRenderInfo> getWonderBrickRenderInfo() {
        List<WonderBrickRenderInfo> wonderBricks = new ArrayList<>();
        for (WonderBrick brick : this.wonderManager.getOrderedWonderBricks()) {
            if (brick.isNeutral()) wonderBricks.add(new WonderBrickRenderInfo(null));
            else {
                PlayerBrick playerBrick = (PlayerBrick) brick;
                wonderBricks.add(new WonderBrickRenderInfo(playerBrick.getPlayerId()));
            }
        }
        return wonderBricks;
    }
}
