package game.view.render;

import game.model.direction.TileCompartmentLocation;
import game.model.transport.Transport;

import java.util.HashMap;
import java.util.List;

public class TransportRenderInfo
{
    public final HashMap<TileCompartmentLocation, List<Transport>> transports;


    public TransportRenderInfo(HashMap<TileCompartmentLocation, List<Transport>> transports)
    {
        this.transports = transports;
    }
}
