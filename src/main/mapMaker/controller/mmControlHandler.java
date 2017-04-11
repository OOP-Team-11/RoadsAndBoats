package mapMaker.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import mapMaker.direction.mmAngle;
import mapMaker.direction.mmDirectionToLocation;
import mapMaker.direction.mmTileEdgeDirection;

import mapMaker.model.mmFileExporter;
import mapMaker.model.mmFileImporter;
import mapMaker.model.mmMap;
import mapMaker.model.tile.*;

import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;
import mapMaker.model.tile.riverConfiguration.mmRiverConfigurationCycler;

import mapMaker.utilities.Observer.CursorObserver.mmCursorObserver;
import mapMaker.utilities.Observer.CursorObserver.mmCursorObserverSubject;
import mapMaker.utilities.Observer.MapMakerObserver.mmMapMakerObserver;
import mapMaker.utilities.Observer.MapMakerObserver.mmMapMakerObserverSubject;
import mapMaker.utilities.Observer.TileSelectObserver.mmTileSelectObserver;
import mapMaker.utilities.Observer.TileSelectObserver.mmTileSelectObserverSubject;

import mapMaker.view.mmMapMakerView;
import mapMaker.view.mmTileSelectorView;
import mapMaker.view.render.mmMapMakerCursorInfo;
import mapMaker.view.render.mmMapMakerRenderInfo;
import mapMaker.view.render.mmTileRenderInformation;
import mapMaker.view.render.mmTileSelectorRenderInfo;


public class mmControlHandler implements mmCursorObserverSubject, mmTileSelectObserverSubject, mmMapMakerObserverSubject {
    private ArrayList<mmCursorObserver> mmCursorObservers;          //Hold CursorObservers who've registered for Cursor updates
    private ArrayList<mmTileSelectObserver> mmTileSelectObservers;  //Hold TileSelectObservers who've registered for TileSelect updates
    private ArrayList<mmMapMakerObserver> mmMapMakerObservers;
    private mmMap gameMmMap;

    private mmTile previousProtoMmTile;
    private mmTile currentProtoMmTile;
    private mmTile nextProtoMmTile;

    private mmLocation protoTileMmLocation;
    private mmLocation cursorMmLocation;

    private mmRiverConfigurationCycler riverConfigList;
    private mmMouseInterpreter mmMouseInterpreter;
    private int cameraX;
    private int cameraY;

    // mapMakerView is given as an observer that the map will use to notify
    // tileSelectorView is given as an observer that mmControlHandler will notify
    public mmControlHandler(mmMap gameMmMap, mmMapMakerView mapMakerView, mmTileSelectorView tileSelectorView) {
        this.gameMmMap = gameMmMap;

        mmCursorObservers = new ArrayList<>();
        mmTileSelectObservers = new ArrayList<>();
        mmMapMakerObservers = new ArrayList<>();

        registerCursorObserver(mapMakerView);
        registerTileSelectObserver(tileSelectorView);
        registeMapMakerObserver(mapMakerView);

        try {
            protoTileMmLocation = new mmLocation(0, 0, 0);    //Initialized to center spot initially.
            cursorMmLocation = new mmLocation(0,0,0);
        } catch (mmInvalidLocationException e) {
            throw new RuntimeException("Cannot initialize prototype tile location: " + e.getLocalizedMessage());
        }

        try {
            notifyCursorObservers(new mmMapMakerCursorInfo(new mmLocation(0,0,0), true));
        } catch(mmInvalidLocationException e) {
            System.out.println("Error : "+ e.getMessage());
        }
        cameraX = 0;
        cameraY = 0;


        mmMouseInterpreter = new mmMouseInterpreter(1000,800,114,128);

        mmTerrain initialMmTerrain = mmTerrain.PASTURE;                           //Initialized to "pasture" by default
        riverConfigList = new mmRiverConfigurationCycler(initialMmTerrain);

        nextProtoMmTile = new mmTile(initialMmTerrain,riverConfigList.getNext());
        currentProtoMmTile = new mmTile(initialMmTerrain,riverConfigList.getCurrent());
        previousProtoMmTile = new mmTile(initialMmTerrain, riverConfigList.getPrevious());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    /* Returns the "previous" prototype tile in terms of river configuration*/
    public mmTile getPreviousProtoMmTile() {
        return this.previousProtoMmTile;

    }

    /* Returns the prototype tile that would actually be placed*/
    public mmTile getCurrentProtoMmTile() {
        return this.currentProtoMmTile;
    }

    /* Returns the "next" prototype tile in terms of river configuration*/
    public mmTile getNextProtoMmTile() {
        return this.nextProtoMmTile;
    }

    private mmTileSelectorRenderInfo makeTileSelectRenderInfo(){

        mmTileRenderInformation previousTileRenderInfo = new mmTileRenderInformation(getPreviousProtoMmTile());
        mmTileRenderInformation currentTileRenderInfo = new mmTileRenderInformation(getCurrentProtoMmTile());
        mmTileRenderInformation nextTileRenderInfo = new mmTileRenderInformation(getNextProtoMmTile());
        boolean temp = gameMmMap.isValid();
        return new mmTileSelectorRenderInfo(previousTileRenderInfo, currentTileRenderInfo, nextTileRenderInfo, temp);
    }

    private mmMapMakerCursorInfo makeMapMakerCursorInfo(){

        boolean isValid = gameMmMap.isValidPlacement(protoTileMmLocation, currentProtoMmTile);
        mmMapMakerCursorInfo cursorInfo = new mmMapMakerCursorInfo(protoTileMmLocation,isValid);
        cursorInfo.setCameraX(cameraX);
        cursorInfo.setCameraY(cameraY);
        return cursorInfo;
    }

    public void nextRiverConfiguration(){
        this.riverConfigList.next();                        //Iterate to the next riverConfig

        this.previousProtoMmTile = this.currentProtoMmTile;     //Set the previous prototype to the current one
        this.currentProtoMmTile = this.nextProtoMmTile;         //Set the current prototype to the next one
        this.nextProtoMmTile = new mmTile(this.currentProtoMmTile.getMmTerrain() , this.riverConfigList.getNext());
        notifyCursorObservers(makeMapMakerCursorInfo());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
    }

    public void previousRiverConfiguration(){
        this.riverConfigList.previous();                        //Iterate to the next riverConfig

        this.nextProtoMmTile = this.currentProtoMmTile;             //Set the next prototype to the current one
        this.currentProtoMmTile = this.previousProtoMmTile;         //Set the current prototype to the previous one
        this.previousProtoMmTile = new mmTile(this.currentProtoMmTile.getMmTerrain() , this.riverConfigList.getPrevious());
        notifyCursorObservers(makeMapMakerCursorInfo());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
    }

    public boolean tryPlaceTile(){
        boolean placed = placeTileOnMap();
        if (placed) {
            gameMmMap.recenter();
        }
        notifyMapMakerObservers(this.gameMmMap.getRenderObject());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        resetCamera();
        return placed;
    }

    private boolean placeTileOnMap() {
        mmTile mmTile = new mmTile(currentProtoMmTile.getMmTerrain(), (mmRiverConfiguration) currentProtoMmTile.getMmRiverConfiguration().clone());
        return this.gameMmMap.placeTile(protoTileMmLocation.clone(), mmTile);
    }

    public void clearTile() {
        clearMapTile();
        notifyMapMakerObservers(this.gameMmMap.getRenderObject());
    }
    public void cursorClicked(double x, double y){
        mmLocation mmLocation = mmMouseInterpreter.interpretMouseClick(x,y);
        cursorMmLocation = mmLocation;
        protoTileMmLocation = mmLocation;
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    public void updateMouseClickInterpreter(int canvasX, int canvasY){
        mmMouseInterpreter.updateCanvasDimensions(canvasX, canvasY);
    }

    public void clearMapTile() {
        this.gameMmMap.removeTileAtLocation(getCursorMmLocation());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    public void moveCursor(mmTileEdgeDirection dir){
        mmLocation newCursorMmLocation = mmDirectionToLocation.getLocation(cursorMmLocation, dir);
        cursorMmLocation = newCursorMmLocation;
        protoTileMmLocation = newCursorMmLocation;
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    public void moveViewport(int x, int y) {
        cameraX = cameraX + x;
        cameraY = cameraY + y;
        mmMouseInterpreter.updateCameraOffsetValues(cameraX, cameraY);
        notifyCursorObservers(makeMapMakerCursorInfo());
    }
    public void resetCamera(){
        mmMouseInterpreter.updateCameraOffsetValues(cameraX, cameraY);
        notifyCursorObservers(makeMapMakerCursorInfo());
    }

    public mmLocation getCursorMmLocation(){
        return protoTileMmLocation;
    }

    public void rotateTileClockwise() {
        currentProtoMmTile.rotate(new mmAngle(60));    //Single-side rotation clockwise
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        notifyCursorObservers(makeMapMakerCursorInfo());
        notifyMapMakerObservers(this.gameMmMap.getRenderObject());
        resetCamera();
    }

    public void rotateTileCounterClockwise() {
        currentProtoMmTile.rotate(new mmAngle(300));   //300 degree clockwise rotation = 60 degree counterclockwise
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        notifyCursorObservers(makeMapMakerCursorInfo());
        notifyMapMakerObservers(this.gameMmMap.getRenderObject());
        resetCamera();
    }
    public void setSeaTerrain(){
        updateTerrain(mmTerrain.SEA);
    }
    public void setPastureTerrain(){
        updateTerrain(mmTerrain.PASTURE);
    }
    public void setWoodsTerrain(){
        updateTerrain(mmTerrain.WOODS);
    }
    public void setRockyTerrain(){
        updateTerrain(mmTerrain.ROCK);
    }
    public void setMountainTerrain(){
        updateTerrain(mmTerrain.MOUNTAIN);
    }
    public void setDesertTerrain(){
        updateTerrain(mmTerrain.DESERT);
    }

    private void updateTerrain(mmTerrain newMmTerrain){
        updateRiverConfigList(newMmTerrain);  //Updates the iterator of possible river configurations to those possible for the new mmTerrain
        previousProtoMmTile = new mmTile(newMmTerrain,riverConfigList.getPrevious());
        currentProtoMmTile = new mmTile(newMmTerrain,riverConfigList.getCurrent());
        notifyCursorObservers(makeMapMakerCursorInfo());
        nextProtoMmTile = new mmTile(newMmTerrain,riverConfigList.getNext());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
    }

    public void importMap(File file) throws IOException {
        mmFileImporter mmFileImporter = new mmFileImporter();
        this.gameMmMap = mmFileImporter.readFile(file);
        notifyMapMakerObservers(this.gameMmMap.getRenderObject());
        notifyTileSelectObservers(makeTileSelectRenderInfo());
        resetCamera();
    }

    public void exportMap(String filename) {
        mmFileExporter mmFileExporter = new mmFileExporter();
        mmFileExporter.writeToFile(this.gameMmMap,filename);
    }

    /* Update the iterator of RiverConfigurations */
    private void updateRiverConfigList(mmTerrain newMmTerrain){
        this.riverConfigList.updateTerrain(newMmTerrain);
    }

    @Override
    public void registerCursorObserver(mmCursorObserver o) {
        this.mmCursorObservers.add(o);
    }

    @Override
    public void removeCursorObserver(mmCursorObserver o) {
        this.mmCursorObservers.remove(o);
    }

    @Override
    public void notifyCursorObservers(mmMapMakerCursorInfo mmMapMakerCursorInfo) {
        for(int i = 0; i < mmCursorObservers.size(); i++){
            mmCursorObservers.get(i).updateCursorInfo(mmMapMakerCursorInfo);
        }
    }

    @Override
    public void registerTileSelectObserver(mmTileSelectObserver o) {
        this.mmTileSelectObservers.add(o);
    }

    @Override
    public void removeTileSelectObserver(mmTileSelectObserver o) {
        this.mmTileSelectObservers.remove(o);
    }

    @Override
    public void notifyTileSelectObservers(mmTileSelectorRenderInfo mmTileSelectorRenderInfo) {
        for(int i = 0; i < mmTileSelectObservers.size(); i++){
            mmTileSelectObservers.get(i).updateTileSelect(mmTileSelectorRenderInfo);
        }
    }

    @Override
    public void registeMapMakerObserver(mmMapMakerObserver o) { this.mmMapMakerObservers.add(o); }
    @Override
    public void removeMapMakerObserver(mmMapMakerObserver o) {this.mmMapMakerObservers.remove(o); }

    @Override
    public void notifyMapMakerObservers(mmMapMakerRenderInfo mmMapMakerRenderInfo) {
        for(int i = 0; i< mmMapMakerObservers.size(); i++){
            mmMapMakerObservers.get(i).updateMapMaker(mmMapMakerRenderInfo);
        }
    }
}
