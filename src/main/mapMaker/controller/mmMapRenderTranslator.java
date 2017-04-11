package mapMaker.controller;

import mapMaker.model.mmMap;
import mapMaker.model.tile.mmLocation;
import mapMaker.model.tile.mmTile;
import mapMaker.view.render.mmTileRenderInformation;

import java.util.HashMap;
import java.util.Set;

public class mmMapRenderTranslator {

    private static Set<java.util.Map.Entry<mmLocation, mmTile>> getEntrySetForMap(java.util.Map<mmLocation, mmTile> map) {
        return map.entrySet();
    }

    public static java.util.Map<mmLocation, mmTileRenderInformation> getRenderInformationForMap(mmMap myMmMap) {
        java.util.Map<mmLocation, mmTileRenderInformation> renderMap = new HashMap<>();

        for (java.util.Map.Entry<mmLocation, mmTile> entry : getEntrySetForMap(myMmMap.getTiles())) {
            mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(entry.getValue());
            renderMap.put(entry.getKey(), mmTileRenderInformation);
        }
        return renderMap;
    }
}
