package controller;

import java.util.ArrayList;
import java.util.HashMap;

import direction.Angle;
import direction.TileEdgeDirection;

import model.Map;
import model.tile.*;

import utilities.Observer.CursorObserver.CursorObserver;
import utilities.Observer.CursorObserver.CursorObserverSubject;
import utilities.Observer.TileSelectObserver.TileSelectObserver;
import utilities.Observer.TileSelectObserver.TileSelectObserverSubject;

import view.MapMakerView;
import view.TileSelectorView;


public class ControlHandler implements CursorObserverSubject, TileSelectObserverSubject {
    private ArrayList<CursorObserver> cursorObservers;          //Hold CursorObservers who've registered for Cursor updates
    private ArrayList<TileSelectObserver> tileSelectObservers;  //Hold TileSelectObservers who've registered for TileSelect updates
    HashMap<ArrayList,Boolean> observerUpdateFlags;  //Will flag a need to update one or both sets of observers when notifyObservers() is called

    private Map gameMap;

    private Tile previousProtoTile;
    private Tile currentProtoTile;
    private Tile nextProtoTile;

    private Location protoTileLocation;

    //make class that encapsulates a list of possible river configurations for a given terrain type

    RiverConfiguration protoRiverConfig;

    // mapMakerView is given as an observer that the map will use to notify
    // tileSelectorView is given as an observer that ControlHandler will notify
    public ControlHandler(Map gameMap, MapMakerView mapMakerView, TileSelectorView tileSelectorView) throws InvalidLocationException {
        this.gameMap = gameMap;

        cursorObservers = new ArrayList<>();
        tileSelectObservers = new ArrayList<>();
        observerUpdateFlags = new HashMap<>();

        registerCursorObserver(mapMakerView);
        registerTileSelectObserver(tileSelectorView);

        protoTileLocation = new Location(0,0,0);    //Initialized to center spot initially.
        protoRiverConfig = RiverConfiguration.getNoRivers();

        previousProtoTile = new Tile(Terrain.PASTURE, protoRiverConfig);
        currentProtoTile = new Tile(Terrain.PASTURE,protoRiverConfig);                  //Initialized to "pasture" by default
        nextProtoTile = new Tile(Terrain.PASTURE,protoRiverConfig);

    }

    /* Returns the "previous" prototype tile in terms of river configuration*/
    public Tile getPreviousProtoTile() {
        return this.previousProtoTile;
    }

    /* Returns the prototype tile that would actually be placed*/
    public Tile getCurrentProtoTile() {
        return this.currentProtoTile;
    }

    /* Returns the "next" prototype tile in terms of river configuration*/
    public Tile getNextProtoTile() {
        return this.nextProtoTile;
    }

    public void nextRiverConfiguration(){
        this.previousProtoTile = this.currentProtoTile;     //Set the previous prototype to the current one
        this.currentProtoTile = this.nextProtoTile;         //Set the current prototype to the next one
//        this.nextProtoTile

    }

    public void previousRiverConfiguration(){

    }

    public boolean tryPlaceTile(){
        return this.gameMap.placeTile(protoTileLocation, currentProtoTile);
    }

    public void clearTile(){
        this.gameMap.removeTileAtLocation(getCursorLocation());
    }

    //TODO: This method
    public void moveCursor(TileEdgeDirection dir){
        //... move the cursor

        //then
        observerUpdateFlags.replace(cursorObservers,true);  //Mark the cursorObservers for notification
        notifyObservers();
    }

    public Location getCursorLocation(){
        return protoTileLocation;
    }

    public void rotateTileClockwise() {
        previousProtoTile.rotate(new Angle(60));    //Single-side rotation clockwise
        currentProtoTile.rotate(new Angle(60));    //Single-side rotation clockwise
        nextProtoTile.rotate(new Angle(60));    //Single-side rotation clockwise
    }

    public void rotateTileCounterClockwise() {
        previousProtoTile.rotate(new Angle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
        currentProtoTile.rotate(new Angle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
        nextProtoTile.rotate(new Angle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
    }

    public void setSeaTerrain(){
        previousProtoTile.setTerrain(Terrain.SEA);
        currentProtoTile.setTerrain(Terrain.SEA);
        nextProtoTile.setTerrain(Terrain.SEA);
    }
    public void setPastureTerrain(){
        previousProtoTile.setTerrain(Terrain.PASTURE);
        currentProtoTile.setTerrain(Terrain.PASTURE);
        nextProtoTile.setTerrain(Terrain.PASTURE);
    }
    public void setWoodsTerrain(){
        previousProtoTile.setTerrain(Terrain.WOODS);
        currentProtoTile.setTerrain(Terrain.WOODS);
        nextProtoTile.setTerrain(Terrain.WOODS);
    }
    public void setRockyTerrain(){
        previousProtoTile.setTerrain(Terrain.ROCK);
        currentProtoTile.setTerrain(Terrain.ROCK);
        nextProtoTile.setTerrain(Terrain.ROCK);
    }
    public void setMountainTerrain(){
        previousProtoTile.setTerrain(Terrain.MOUNTAIN);
        currentProtoTile.setTerrain(Terrain.MOUNTAIN);
        nextProtoTile.setTerrain(Terrain.MOUNTAIN);
    }
    public void setDesertTerrain(){
        previousProtoTile.setTerrain(Terrain.DESERT);
        currentProtoTile.setTerrain(Terrain.DESERT);
        nextProtoTile.setTerrain(Terrain.DESERT);
    }

     /* Observer stuff below  */

     @Override
     public void notifyObservers() {
        /* Notifies both sets of Observers depending on whether or not they're flagged for updating*/
         if(observerUpdateFlags.get(cursorObservers)){
             notifyCursorObservers();
             observerUpdateFlags.replace(cursorObservers,false);
         }

         if(observerUpdateFlags.get(tileSelectObservers)){
             notifyTileSelectObservers();
             observerUpdateFlags.replace(tileSelectObservers,false);
         }
     }

    //TODO: Figure out what will happen to each Observer
     public void notifyCursorObservers(){
        for(int i = 0; i < cursorObservers.size(); i++){
            //Notify cursorObservers.get(i) of changes
        }
     }

    //TODO: Figure out what will happen to each Observer
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
