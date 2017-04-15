package game.view.render;

import game.model.direction.TileCompartmentLocation;

import java.util.Map;

public class MapTransportRenderInfo {

    private final Map<TileCompartmentLocation, TransportRenderInfo> transports;

    public MapTransportRenderInfo(Map<TileCompartmentLocation, TransportRenderInfo> transportRenderInfoMap)
    {
        this.transports = transportRenderInfoMap;
    }

    public Map<TileCompartmentLocation, TransportRenderInfo> getTransports() {
        return this.transports;
    }
}
