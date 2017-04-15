package game.utilities.observer;

import game.view.render.MapStructureRenderInfo;
import game.view.render.StructureRenderInfo;

public interface MapStructureRenderInfoObserver {
    void updateMapStructureInfo(MapStructureRenderInfo mapStructureRenderInfo);
}
