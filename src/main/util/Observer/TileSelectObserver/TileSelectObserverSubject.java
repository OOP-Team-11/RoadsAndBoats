package util.Observer.TileSelectObserver;

import util.Observer.ObserverSubject;

public interface TileSelectObserverSubject extends ObserverSubject {
    void registerTileSelectObserver(TileSelectObserver o);
    void removeTileSelectObserver(TileSelectObserver o);
}
