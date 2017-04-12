package game.utilities.observer;

import game.view.render.WallRenderInfo;

public interface WallRenderInfoObserver {
    void updateWallInfo(WallRenderInfo wallRenderInfo);
}
