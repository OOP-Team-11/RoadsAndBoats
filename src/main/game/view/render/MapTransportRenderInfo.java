package game.view.render;

import game.model.direction.TileCompartmentLocation;

import java.util.List;
import java.util.Map;

public class MapTransportRenderInfo {

    private final Map<TileCompartmentLocation, List<TransportRenderInfo>> transports;

    public MapTransportRenderInfo(Map<TileCompartmentLocation, List<TransportRenderInfo>> transportRenderInfoMap)
    {
        this.transports = transportRenderInfoMap;
    }

    public Map<TileCompartmentLocation, List<TransportRenderInfo>> getTransports() {
        return this.transports;
    }
}
