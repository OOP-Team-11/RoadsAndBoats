package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import direction.Angle;
import direction.DirectionToLocation;
import direction.TileEdgeDirection;

import model.FileExporter;
import model.FileImporter;
import model.Map;
import model.tile.*;

import model.tile.riverConfiguration.RiverConfigurationCycler;

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
    private MapMakerCursorInfo cursorInfo;
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
        mapMakerObservers = new ArrayList<>();

        registerCursorObserver(mapMakerView);
        registerTileSelectObserver(tileSelectorView);
        registeMapMakerObserver(mapMakerView);

        try {
            protoTileLocation = new Location(0, 0, 0);    //Initialized to center spot initially.
        } catch (InvalidLocationException e) {
            throw new RuntimeException("Cannot initialize prototype tile location: " + e.getLocalizedMessage());
        }

        try {
            notifyCursorObservers(new MapMakerCursorInfo(new Location(0,0,0), true));
        } catch(InvalidLocationException e) {
            System.out.println("Error : "+ e.getMessage());
        }

        cursorInfo = new MapMakerCursorInfo(protoTileLocation, true);

        Terrain initialTerrain = Terrain.PASTURE;                           //Initialized to "pasture" by default
        riverConfigList = new RiverConfigurationCycler(initialTerrain);

        previousProtoTile = new Tile(initialTerrain, riverConfigList.getPrevious());
        currentProtoTile = new Tile(initialTerrain,riverConfigList.getCurrent());
        nextProtoTile = new Tile(initialTerrain,riverConfigList.getNext());
        nextRiverConfiguration();
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

    private TileSelectorRenderInfo makeRenderInfo(){

        return new TileSelectorRenderInfo(getPreviousProtoTile(),getCurrentProtoTile(),getNextProtoTile());
    }

    public void nextRiverConfiguration(){
        this.previousProtoTile = this.currentProtoTile;     //Set the previous prototype to the current one
        this.currentProtoTile = this.nextProtoTile;         //Set the current prototype to the next one

        this.riverConfigList.next();                        //Iterate to the next riverConfig

        //Set the nextProtoTile to a new Tile with the same terrain as the others and the new riverConfig
        this.nextProtoTile = new Tile(this.currentProtoTile.getTerrain() , this.riverConfigList.getCurrent());
        notifyTileSelectObservers(makeRenderInfo());
    }

    public void previousRiverConfiguration(){
        this.nextProtoTile = this.currentProtoTile;         //Set the next prototype to the current one
        this.currentProtoTile = this.previousProtoTile;     //set the current prototype to the previous one

        this.riverConfigList.previous();                    //Iterate to the previous riverConfig

        //Set the nextProtoTile to a new Tile with the same terrain as the others and the new riverConfig
        this.previousProtoTile = new Tile(this.currentProtoTile.getTerrain() , this.riverConfigList.getCurrent());
        notifyTileSelectObservers(makeRenderInfo());
    }

    public boolean tryPlaceTile(){
        boolean placed = placeTileOnMap();
        notifyMapMakerObservers(this.gameMap.getRenderObject());
        return placed;
    }

    private boolean placeTileOnMap() {
        return this.gameMap.placeTile(protoTileLocation, currentProtoTile.makeClone());
    }

    public void clearTile() {
        clearMapTile();
        notifyMapMakerObservers(this.gameMap.getRenderObject());
    }

    public void clearMapTile() {
        this.gameMap.removeTileAtLocation(getCursorLocation());
    }

    public void moveCursor(TileEdgeDirection dir){
        Location newCursorLocation = DirectionToLocation.getLocation(cursorInfo.getCursorLocation(), dir);
        protoTileLocation = newCursorLocation;
        boolean isValidPlacement = gameMap.isValidPlacement(newCursorLocation, currentProtoTile);
        cursorInfo.setCursorLocation(newCursorLocation);
        cursorInfo.setIsCursorValid(isValidPlacement);
        notifyCursorObservers(cursorInfo);
    }

    public void moveViewport(int x, int y) {
        cursorInfo.setCameraX(cursorInfo.getCameraX() + x);
        cursorInfo.setCameraY(cursorInfo.getCameraY() + y);
        notifyCursorObservers(cursorInfo);
    }

    public Location getCursorLocation(){
        return protoTileLocation;
    }

    public void rotateTileClockwise() {
        previousProtoTile.rotate(new Angle(60));    //Single-side rotation clockwise
        currentProtoTile.rotate(new Angle(60));    //Single-side rotation clockwise
        nextProtoTile.rotate(new Angle(60));    //Single-side rotation clockwise
        notifyTileSelectObservers(makeRenderInfo());
    }

    public void rotateTileCounterClockwise() {
        previousProtoTile.rotate(new Angle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
        currentProtoTile.rotate(new Angle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
        nextProtoTile.rotate(new Angle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
        notifyTileSelectObservers(makeRenderInfo());
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
        notifyTileSelectObservers(makeRenderInfo());
    }
//
//    public void importMap(String filename) throws IOException {
//        FileImporter fileImporter = new FileImporter();
//        this.gameMap = fileImporter.readFile(filename);
//    }

    public void exportMap(String filename) {
        FileExporter fileExporter = new FileExporter();
        fileExporter.writeToFile(this.gameMap,filename);

    }

    /* Update the iterator of RiverConfigurations */
    private void updateRiverConfigList(Terrain newTerrain){
        this.riverConfigList.updateTerrain(newTerrain);
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
