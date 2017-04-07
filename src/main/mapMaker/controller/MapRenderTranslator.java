package mapMaker.controller;

import mapMaker.model.Map;
import mapMaker.model.tile.Location;
import mapMaker.model.tile.Tile;
import mapMaker.view.render.TileRenderInformation;

import java.util.HashMap;
import java.util.Set;

public class MapRenderTranslator {

    private static Set<java.util.Map.Entry<Location, Tile>> getEntrySetForMap(java.util.Map<Location, Tile> map) {
        return map.entrySet();
    }

    public static java.util.Map<Location, TileRenderInformation> getRenderInformationForMap(Map myMap) {
        java.util.Map<Location, TileRenderInformation> renderMap = new HashMap<>();

        for (java.util.Map.Entry<Location, Tile> entry : getEntrySetForMap(myMap.getTiles())) {
            TileRenderInformation tileRenderInformation = new TileRenderInformation(entry.getValue());
            renderMap.put(entry.getKey(), tileRenderInformation);
        }
        return renderMap;
    }
}
