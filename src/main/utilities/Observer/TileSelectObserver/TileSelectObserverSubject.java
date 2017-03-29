package utilities.Observer.TileSelectObserver;


import view.render.TileSelectorRenderInfo;

public interface TileSelectObserverSubject{
    void registerTileSelectObserver(TileSelectObserver o);
    void removeTileSelectObserver(TileSelectObserver o);
    void notifyTileSelectObservers(TileSelectorRenderInfo tileSelectorRenderInfo);
}
