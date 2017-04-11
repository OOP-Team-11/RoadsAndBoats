package mapMaker.utilities.Observer.TileSelectObserver;


import mapMaker.view.render.mmTileSelectorRenderInfo;

public interface mmTileSelectObserverSubject {
    void registerTileSelectObserver(mmTileSelectObserver o);
    void removeTileSelectObserver(mmTileSelectObserver o);
    void notifyTileSelectObservers(mmTileSelectorRenderInfo mmTileSelectorRenderInfo);
}
