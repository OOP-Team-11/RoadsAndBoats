package game.view.render;

import game.model.PlayerId;
import game.model.direction.TileCompartmentLocation;

import java.util.List;
import java.util.Map;

public class MapTransportRenderInfo {

    private final Map<TileCompartmentLocation, List<TransportRenderInfo>> transports;
    private PlayerId playerId;

    public MapTransportRenderInfo(PlayerId playerId, Map<TileCompartmentLocation, List<TransportRenderInfo>> transportRenderInfoMap)
    {
        this.playerId = playerId;
        this.transports = transportRenderInfoMap;
    }

    public Map<TileCompartmentLocation, List<TransportRenderInfo>> getTransports() {
        return this.transports;
    }

    public PlayerId getPlayerId() {
        return this.playerId;
    }
}
