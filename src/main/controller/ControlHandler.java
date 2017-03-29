package controller;

import java.util.ArrayList;
import java.util.HashMap;

import direction.Angle;
import direction.DirectionToLocation;
import direction.TileEdgeDirection;

import model.Map;
import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;

import utilities.Observer.CursorObserver.CursorObserver;
import utilities.Observer.CursorObserver.CursorObserverSubject;
import utilities.Observer.MapMakerObserver.MapMakerObserver;
import utilities.Observer.MapMakerObserver.MapMakerObserverSubject;
import utilities.Observer.TileSelectObserver.TileSelectObserver;
import utilities.Observer.TileSelectObserver.TileSelectObserverSubject;

import view.MapMakerView;
import view.TileSelectorView;
import view.render.MapMakerCursorInfo;
import view.render.MapMakerRenderInfo;
import view.render.TileSelectorRenderInfo;


public class ControlHandler implements CursorObserverSubject, TileSelectObserverSubject, MapMakerObserverSubject {
    private ArrayList<CursorObserver> cursorObservers;          //Hold CursorObservers who've registered for Cursor updates
    private ArrayList<TileSelectObserver> tileSelectObservers;  //Hold TileSelectObservers who've registered for TileSelect updates
    private ArrayList<MapMakerObserver> mapMakerObservers;
    private HashMap<ArrayList,Boolean> observerUpdateFlags;  //Will flag a need to update one or both sets of observers when notifyObservers() is called

    private Map gameMap;

    private Tile protoTile;
    private Location protoTileLocation;

    private ArrayList<TileEdgeDirection> riverDirections;

    // mapMakerView is given as an observer that the map will use to notify
    // tileSelectorView is given as an observer that ControlHandler will notify
    public ControlHandler(Map gameMap, MapMakerView mapMakerView, TileSelectorView tileSelectorView) {
        this.gameMap = gameMap;

        cursorObservers = new ArrayList<>();
        tileSelectObservers = new ArrayList<>();
        mapMakerObservers = new ArrayList<>();
        observerUpdateFlags = new HashMap<>();

        registerCursorObserver(mapMakerView);
        registerTileSelectObserver(tileSelectorView);
        registeMapMakerObserver(mapMakerView);
        protoTile = new Tile(Terrain.PASTURE);                  //Initialized to "pasture" by default
        try {
            protoTileLocation = new Location(0, 0, 0);    //Initialized to center spot initially.
        } catch (InvalidLocationException e) {
            throw new RuntimeException("Could not initialize protoTileLocation. Got: " + e.getLocalizedMessage());
        }
        riverDirections = new ArrayList<>();

    }

    public boolean tryPlaceTile(){
        boolean placed = placeTileOnMap();
        notifyMapMakerObservers(this.gameMap.getRenderObject());
        return placed;
    }

    private boolean placeTileOnMap() {
        return this.gameMap.placeTile(protoTileLocation, protoTile);
    }

    public void clearTile() {
        clearMapTile();
        notifyMapMakerObservers(this.gameMap.getRenderObject());
    }

    public void clearMapTile() {
        this.gameMap.removeTileAtLocation(getCursorLocation());
    }

    public void moveCursor(TileEdgeDirection dir){
        Location newCursorLocation = DirectionToLocation.getLocation(protoTileLocation, dir);
        boolean isValidPlacement = gameMap.isValidPlacement(newCursorLocation, protoTile);
        MapMakerCursorInfo cursorInfo = new MapMakerCursorInfo(newCursorLocation, isValidPlacement);
        observerUpdateFlags.replace(cursorObservers,true);  //Mark the cursorObservers for notification
        notifyCursorObservers(cursorInfo);
    }

    public Location getCursorLocation(){
        return protoTileLocation;
    }

    public void rotateTileClockwise() {
        protoTile.rotate(new Angle(60));    //Single-side rotation clockwise
    }

    public void rotateTileCounterClockwise() {
        protoTile.rotate(new Angle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
    }

    public void setSeaTerrain(){
        protoTile.setTerrain(Terrain.SEA);
    }
    public void setPastureTerrain(){
        protoTile.setTerrain(Terrain.PASTURE);
    }
    public void setWoodsTerrain(){
        protoTile.setTerrain(Terrain.WOODS);
    }
    public void setRockyTerrain(){
        protoTile.setTerrain(Terrain.ROCK);
    }
    public void setMountainTerrain(){
        protoTile.setTerrain(Terrain.MOUNTAIN);
    }
    public void setDesertTerrain(){
        protoTile.setTerrain(Terrain.DESERT);
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
    public void notifyCursorObservers(MapMakerCursorInfo mapMakerCursorInfo) {
        for(int i = 0; i < cursorObservers.size(); i++){
            cursorObservers.get(i).updateCursorInfo(mapMakerCursorInfo);
        }
    }

    @Override
    public void registerTileSelectObserver(TileSelectObserver o) {
        this.tileSelectObservers.add(o);
    }

    @Override
    public void removeTileSelectObserver(TileSelectObserver o) {
        this.tileSelectObservers.remove(o);
    }

    @Override
    public void notifyTileSelectObservers(TileSelectorRenderInfo tileSelectorRenderInfo) {
        for(int i = 0; i < tileSelectObservers.size(); i++){
            tileSelectObservers.get(i).updateTileSelect(tileSelectorRenderInfo);
        }
    }

    @Override
    public void registeMapMakerObserver(MapMakerObserver o) { this.mapMakerObservers.add(o); }
    @Override
    public void removeMapMakerObserver(MapMakerObserver o) {this.mapMakerObservers.remove(o); }

    @Override
    public void notifyMapMakerObservers(MapMakerRenderInfo mapMakerRenderInfo) {
        for(int i=0; i<mapMakerObservers.size(); i++){
            mapMakerObservers.get(i).updateMapMaker(mapMakerRenderInfo);
        }
    }
}
