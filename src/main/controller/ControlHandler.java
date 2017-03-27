package controller;

import java.util.ArrayList;

import model.Map;
import model.tile.Location;
import model.tile.Tile;

import util.Observer.CursorObserver.CursorObserver;
import util.Observer.CursorObserver.CursorObserverSubject;
import util.Observer.TileSelectObserver.TileSelectObserver;
import util.Observer.TileSelectObserver.TileSelectObserverSubject;

import view.MapMakerView;
import view.TileSelectorView;


class ControlHandler implements CursorObserverSubject, TileSelectObserverSubject {
    private ArrayList<CursorObserver> cursorObservers;
    private ArrayList<TileSelectObserver> tileSelectObservers;

    private Map gameMap;
    private Tile protoTile;
    private Location protoTileLocation;

    // mapMakerView is given as an observer that the map will use to notify
    // tileSelectorView is given as an observer that ControlHandler will notify
    public ControlHandler(Map gameMap, MapMakerView mapMakerView, TileSelectorView tileSelectorView){
        this.gameMap = gameMap;
        registerCursorObserver(mapMakerView);
        registerTileSelectObserver(tileSelectorView);
    }

     @Override
     public void notifyObservers() {

     }

     @Override
     public void registerCursorObserver(CursorObserver o) {
        this.cursorObservers.add(o);
     }

     @Override
     public void removeCursorObserver(CursorObserver o) {
        this.cursorObservers.remove(o);
     }

     @Override
     public void registerTileSelectObserver(TileSelectObserver o) {
        this.tileSelectObservers.add(o);
     }

     @Override
     public void removeTileSelectObserver(TileSelectObserver o) {
        this.tileSelectObservers.remove(o);
     }
 }
