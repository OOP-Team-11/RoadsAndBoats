package game.utilities.observer;

import game.view.render.TileResourceRenderInfo;

public interface TileResourceInfoObserver {

    void onTileResourcesUpdated(TileResourceRenderInfo tileResourceRenderInfo);
}
