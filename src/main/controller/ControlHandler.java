package controller;

import java.util.ArrayList;
import java.util.HashMap;

import direction.Angle;
import direction.TileEdgeDirection;

import model.Map;
import model.tile.*;

import model.tile.riverConfiguration.RiverConfiguration;
import model.tile.riverConfiguration.RiverConfigurationCycler;

import utilities.Observer.CursorObserver.CursorObserver;
import utilities.Observer.CursorObserver.CursorObserverSubject;
import utilities.Observer.TileSelectObserver.TileSelectObserver;
import utilities.Observer.TileSelectObserver.TileSelectObserverSubject;

import view.MapMakerView;
import view.TileSelectorView;


public class ControlHandler implements CursorObserverSubject, TileSelectObserverSubject {
    //TODO: Go through each and every method and figure out whether they should notify either observer
    private ArrayList<CursorObserver> cursorObservers;          //Hold CursorObservers who've registered for Cursor updates
    private ArrayList<TileSelectObserver> tileSelectObservers;  //Hold TileSelectObservers who've registered for TileSelect updates
    private HashMap<ArrayList,Boolean> observerUpdateFlags;  //Will flag a need to update one or both sets of observers when notifyObservers() is called

    private Map gameMap;

    private Tile previousProtoTile;
    private Tile currentProtoTile;
    private Tile nextProtoTile;

    private Location protoTileLocation;

    private RiverConfigurationCycler riverConfigList;

    // mapMakerView is given as an observer that the map will use to notify
    // tileSelectorView is given as an observer that ControlHandler will notify
    public ControlHandler(Map gameMap, MapMakerView mapMakerView, TileSelectorView tileSelectorView) {
        this.gameMap = gameMap;

        cursorObservers = new ArrayList<>();
        tileSelectObservers = new ArrayList<>();
        observerUpdateFlags = new HashMap<>();

        registerCursorObserver(mapMakerView);
        registerTileSelectObserver(tileSelectorView);

        try {
            protoTileLocation = new Location(0, 0, 0);    //Initialized to center spot initially.
        } catch (InvalidLocationException e) {
            throw new RuntimeException("Cannot initialize prototype tile location: " + e.getLocalizedMessage());
        }

        Terrain initialTerrain = Terrain.PASTURE;                           //Initialized to "pasture" by default
        riverConfigList = new RiverConfigurationCycler(initialTerrain);

        previousProtoTile = new Tile(initialTerrain, riverConfigList.getPrevious());
        currentProtoTile = new Tile(initialTerrain,riverConfigList.getCurrent());
        nextProtoTile = new Tile(initialTerrain,riverConfigList.getNext());

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

        this.riverConfigList.next();                        //Iterate to the next riverConfig

        //Set the nextProtoTile to a new Tile with the same terrain as the others and the new riverConfig
        this.nextProtoTile = new Tile(this.currentProtoTile.getTerrain() , this.riverConfigList.getCurrent());
    }

    public void previousRiverConfiguration(){
        this.nextProtoTile = this.currentProtoTile;         //Set the next prototype to the current one
        this.currentProtoTile = this.previousProtoTile;     //set the current prototype to the previous one

        this.riverConfigList.previous();                    //Iterate to the previous riverConfig

        //Set the nextProtoTile to a new Tile with the same terrain as the others and the new riverConfig
        this.previousProtoTile = new Tile(this.currentProtoTile.getTerrain() , this.riverConfigList.getCurrent());
    }

    public boolean tryPlaceTile(){
        return this.gameMap.placeTile(protoTileLocation, currentProtoTile.makeClone());
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
        updateTerrain(Terrain.SEA);
    }
    public void setPastureTerrain(){
        updateTerrain(Terrain.PASTURE);
    }
    public void setWoodsTerrain(){
        updateTerrain(Terrain.WOODS);
    }
    public void setRockyTerrain(){
        updateTerrain(Terrain.ROCK);
    }
    public void setMountainTerrain(){
        updateTerrain(Terrain.MOUNTAIN);
    }
    public void setDesertTerrain(){
        updateTerrain(Terrain.DESERT);
    }

    private void updateTerrain(Terrain newTerrain){
        updateRiverConfigList(newTerrain);  //Updates the iterator of possible river configurations to those possible for the new Terrain
        previousProtoTile = new Tile(newTerrain,riverConfigList.getPrevious());
        currentProtoTile = new Tile(newTerrain,riverConfigList.getCurrent());
        nextProtoTile = new Tile(newTerrain,riverConfigList.getNext());

    }

    /* Update the iterator of RiverConfigurations */
    private void updateRiverConfigList(Terrain newTerrain){
        this.riverConfigList.updateTerrain(newTerrain);
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
