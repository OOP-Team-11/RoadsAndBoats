package mapMaker.utilities.Observer.TileSelectObserver;


import mapMaker.view.render.TileSelectorRenderInfo;

public interface TileSelectObserverSubject{
    void registerTileSelectObserver(TileSelectObserver o);
    void removeTileSelectObserver(TileSelectObserver o);
    void notifyTileSelectObservers(TileSelectorRenderInfo tileSelectorRenderInfo);
}
