package utilities.Observer.TileSelectObserver;

import utilities.Observer.ObserverSubject;

public interface TileSelectObserverSubject extends ObserverSubject {
    void registerTileSelectObserver(TileSelectObserver o);
    void removeTileSelectObserver(TileSelectObserver o);
}
