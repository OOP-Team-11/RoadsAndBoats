package mapMaker.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import mapMaker.direction.Angle;
import mapMaker.direction.DirectionToLocation;
import mapMaker.direction.TileEdgeDirection;

import mapMaker.model.FileExporter;
import mapMaker.model.FileImporter;
import mapMaker.model.Map;
import mapMaker.model.tile.*;

import mapMaker.model.tile.riverConfiguration.RiverConfiguration;
import mapMaker.model.tile.riverConfiguration.RiverConfigurationCycler;

import mapMaker.utilities.Observer.CursorObserver.CursorObserver;
import mapMaker.utilities.Observer.CursorObserver.CursorObserverSubject;
import mapMaker.utilities.Observer.MapMakerObserver.MapMakerObserver;
import mapMaker.utilities.Observer.MapMakerObserver.MapMakerObserverSubject;
import mapMaker.utilities.Observer.TileSelectObserver.TileSelectObserver;
import mapMaker.utilities.Observer.TileSelectObserver.TileSelectObserverSubject;

import mapMaker.view.MapMakerView;
import mapMaker.view.TileSelectorView;
import mapMaker.view.render.MapMakerCursorInfo;
import mapMaker.view.render.MapMakerRenderInfo;
import mapMaker.view.render.TileRenderInformation;
import mapMaker.view.render.TileSelectorRenderInfo;


public class ControlHandler implements CursorObserverSubject, TileSelectObserverSubject, MapMakerObserverSubject {
    private ArrayList<CursorObserver> cursorObservers;          //Hold CursorObservers who've registered for Cursor updates
    private ArrayList<TileSelectObserver> tileSelectObservers;  //Hold TileSelectObservers who've registered for TileSelect updates
    private ArrayList<MapMakerObserver> mapMakerObservers;
    private Map gameMap;

    private Tile previousProtoTile;
    private Tile currentProtoTile;
    private Tile nextProtoTile;

    private Location protoTileLocation;
    private Location cursorLocation;

    private RiverConfigurationCycler riverConfigList;
    private MouseInterpreter mouseInterpreter;
    private int cameraX;
    private int cameraY;

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
            cursorLocation = new Location(0,0,0);
        } catch (InvalidLocationException e) {
            throw new RuntimeException("Cannot initialize prototype tile location: " + e.getLocalizedMessage());
        }

        try {
            notifyCursorObservers(new MapMakerCursorInfo(new Location(0,0,0), true));
        } catch(InvalidLocationException e) {
            System.out.println("Error : "+ e.getMessage());
        }
        cameraX = 0;
        cameraY = 0;


        mouseInterpreter = new MouseInterpreter(1000,800,114,128);

        Terrain initialTerrain = Terrain.PASTURE;                           //Initialized to "pasture" by default
        riverConfigList = new RiverConfigurationCycler(initialTerrain);

        nextProtoTile = new Tile(initialTerrain,riverConfigList.getNext());
        currentProtoTile = new Tile(initialTerrain,riverConfigList.getCurrent());
        previousProtoTile = new Tile(initialTerrain, riverConfigList.getPrevious());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        notifyCursorObservers(makeMapMakerCursorInfo());
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

    private TileSelectorRenderInfo makeTileSelectRenderInfo(){

        TileRenderInformation previousTileRenderInfo = new TileRenderInformation(getPreviousProtoTile());
        TileRenderInformation currentTileRenderInfo = new TileRenderInformation(getCurrentProtoTile());
        TileRenderInformation nextTileRenderInfo = new TileRenderInformation(getNextProtoTile());
        boolean temp = gameMap.isValid();
        return new TileSelectorRenderInfo(previousTileRenderInfo, currentTileRenderInfo, nextTileRenderInfo, temp);
    }

    private MapMakerCursorInfo makeMapMakerCursorInfo(){

        boolean isValid = gameMap.isValidPlacement(protoTileLocation,currentProtoTile);
        MapMakerCursorInfo cursorInfo = new MapMakerCursorInfo(protoTileLocation,isValid);
        cursorInfo.setCameraX(cameraX);
        cursorInfo.setCameraY(cameraY);
        return cursorInfo;
    }

    public void nextRiverConfiguration(){
        this.riverConfigList.next();                        //Iterate to the next riverConfig

        this.previousProtoTile = this.currentProtoTile;     //Set the previous prototype to the current one
        this.currentProtoTile = this.nextProtoTile;         //Set the current prototype to the next one
        this.nextProtoTile = new Tile(this.currentProtoTile.getTerrain() , this.riverConfigList.getNext());
        notifyCursorObservers(makeMapMakerCursorInfo());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
    }

    public void previousRiverConfiguration(){
        this.riverConfigList.previous();                        //Iterate to the next riverConfig

        this.nextProtoTile = this.currentProtoTile;             //Set the next prototype to the current one
        this.currentProtoTile = this.previousProtoTile;         //Set the current prototype to the previous one
        this.previousProtoTile = new Tile(this.currentProtoTile.getTerrain() , this.riverConfigList.getPrevious());
        notifyCursorObservers(makeMapMakerCursorInfo());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
    }

    public boolean tryPlaceTile(){
        boolean placed = placeTileOnMap();
        if (placed) {
            gameMap.recenter();
        }
        notifyMapMakerObservers(this.gameMap.getRenderObject());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        resetCamera();
        return placed;
    }

    private boolean placeTileOnMap() {
        Tile tile = new Tile(currentProtoTile.getTerrain(), (RiverConfiguration)currentProtoTile.getRiverConfiguration().clone());
        return this.gameMap.placeTile(protoTileLocation.clone(), tile);
    }

    public void clearTile() {
        clearMapTile();
        notifyMapMakerObservers(this.gameMap.getRenderObject());
    }
    public void cursorClicked(double x, double y){
        Location location = mouseInterpreter.interpretMouseClick(x,y);
        cursorLocation = location;
        protoTileLocation = location;
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    public void updateMouseClickInterpreter(int canvasX, int canvasY){
        mouseInterpreter.updateCanvasDimensions(canvasX, canvasY);
    }

    public void clearMapTile() {
        this.gameMap.removeTileAtLocation(getCursorLocation());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    public void moveCursor(TileEdgeDirection dir){
        Location newCursorLocation = DirectionToLocation.getLocation(cursorLocation, dir);
        cursorLocation = newCursorLocation;
        protoTileLocation = newCursorLocation;
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    public void moveViewport(int x, int y) {
        cameraX = cameraX + x;
        cameraY = cameraY + y;
        mouseInterpreter.updateCameraOffsetValues(cameraX, cameraY);
        notifyCursorObservers(makeMapMakerCursorInfo());
    }
    public void resetCamera(){
        mouseInterpreter.updateCameraOffsetValues(cameraX, cameraY);
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    public Location getCursorLocation(){
        return protoTileLocation;
    }

    public void rotateTileClockwise() {
        currentProtoTile.rotate(new Angle(60));    //Single-side rotation clockwise
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        notifyCursorObservers(makeMapMakerCursorInfo());
        notifyMapMakerObservers(this.gameMap.getRenderObject());
        resetCamera();
    }

    public void rotateTileCounterClockwise() {
        currentProtoTile.rotate(new Angle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        notifyCursorObservers(makeMapMakerCursorInfo());
        notifyMapMakerObservers(this.gameMap.getRenderObject());
        resetCamera();
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
        notifyCursorObservers(makeMapMakerCursorInfo());
        nextProtoTile = new Tile(newTerrain,riverConfigList.getNext());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
    }

    public void importMap(File file) throws IOException {
        FileImporter fileImporter = new FileImporter();
        this.gameMap = fileImporter.readFile(file);
        notifyMapMakerObservers(this.gameMap.getRenderObject());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        resetCamera();
    }

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
