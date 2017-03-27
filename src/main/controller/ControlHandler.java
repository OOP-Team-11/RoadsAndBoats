package controller;

import model.Map;
import model.tile.Location;
import model.tile.Tile;
import view.MapMakerView;
import view.TileSelectorView;

 class ControlHandler implements CursorObserverSubject,TileSelectObserverSubject{

    private Map gameMap;
    private Tile protoTile;
    private Location protoTileLocation;

    // mapMakerView is given as an observer that the map will use to notify
    // tileSelectorView is given as an observer that ControlHandler will notify
    public ControlHandler(Map gameMap, MapMakerView mapMakerView, TileSelectorView tileSelectorView){
        this.gameMap = gameMap;
    }
}
