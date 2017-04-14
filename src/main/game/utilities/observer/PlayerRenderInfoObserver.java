package game.utilities.observer;

import game.view.render.PlayerRenderInfo;

public interface PlayerRenderInfoObserver {
    void updatePlayerInfo(PlayerRenderInfo playerRenderInfo);
}
