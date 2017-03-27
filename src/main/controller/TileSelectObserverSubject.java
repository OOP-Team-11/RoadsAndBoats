package controller;

public interface TileSelectObserverSubject extends ObserverSubject{
    void registerTileSelectObserver();
    void removeTileSelectObserver();
}
