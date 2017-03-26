package Controller;

import Model.Map;
import View.MapMakerView;
import View.TileSelectorView;

/**
 * Created by Konrad on 3/26/2017.
 */
public class ControlHandler {

    private Map gameMap;

    // mapMakerView is given as an observer that the map will use to notify
    // tileSelectorView is given as an observer that ControlHandler will notify
    public ControlHandler(Map gameMap, MapMakerView mapMakerView, TileSelectorView tileSelectorView){
        this.gameMap = gameMap;
    }
}
