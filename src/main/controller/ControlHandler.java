package controller;

import java.util.ArrayList;
import java.util.HashMap;

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
    private ArrayList<CursorObserver> cursorObservers;          //Hold CursorObservers who've registered for Cursor updates
    private ArrayList<TileSelectObserver> tileSelectObservers;  //Hold TileSelectObservers who've registered for TileSelect updates
    HashMap<ArrayList,Boolean> observerUpdateFlags;  //Will flag a need to update one or both sets of observers when notifyObservers() is called

    private Map gameMap;
    private Tile protoTile;
    private Location protoTileLocation;

    // mapMakerView is given as an observer that the map will use to notify
    // tileSelectorView is given as an observer that ControlHandler will notify
    public ControlHandler(Map gameMap, MapMakerView mapMakerView, TileSelectorView tileSelectorView){
        cursorObservers = new ArrayList<>();
        tileSelectObservers = new ArrayList<>();
        observerUpdateFlags = new HashMap<>();

        this.gameMap = gameMap;
        registerCursorObserver(mapMakerView);
        registerTileSelectObserver(tileSelectorView);
    }

     @Override
     public void notifyObservers() {
        if(observerUpdateFlags.get(cursorObservers)){
            notifyCursorObservers();
        }

        if(observerUpdateFlags.get(tileSelectObservers)){
            notifyTileSelectObservers();
        }
     }

     public void notifyCursorObservers(){
        for(int i = 0; i < cursorObservers.size(); i++){
            //Notify cursorObservers.get(i) of changes
        }
     }

     public void notifyTileSelectObservers(){
         for(int i = 0; i < tileSelectObservers.size(); i++){
             //Notify tileSelectObservers.get(i) of changes
         }
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
