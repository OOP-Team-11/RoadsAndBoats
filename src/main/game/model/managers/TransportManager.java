package game.model.managers;

import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.model.transport.TransportLocation;

import java.util.*;

public class TransportManager {

    private Player player;
    private GooseManager gooseManager;
    private TransportAbilityManager transportAbilityManager;
    private Map<Location, List<TransportLocation>> transports;
    public TransportManager(Player player) {
        this.player = player;
        this.transports = new HashMap<Location, List<TransportLocation>>();
    }

    public PlayerId getPlayerId() {
        return this.player.getPlayerId();
    }

    public void addTransport(Transport transport, Location location, TileCompartmentDirection tileCompartmentDirection) {
        // TODO: enforce transport type limits
        TransportLocation tl = new TransportLocation(transport, tileCompartmentDirection);
        if (transports.containsKey(location)) {
            List<TransportLocation> transportLocations = transports.get(location);
            transportLocations.add(tl);
        } else {
            List<TransportLocation> tls = new ArrayList<>();
            tls.add(tl);
            transports.put(location, tls);
        }
    }

    public boolean moveTransport(Transport transport, Location location, TileCompartmentDirection tileCompartmentDirection) {
        Location oldLocation = null;
        TransportLocation transportLocation = null;
        Iterator it = transports.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Location mapLocation = (Location) pair.getKey();
            List<TransportLocation> mapTransportLocation = transports.get(mapLocation);

            Iterator tlIterator = mapTransportLocation.iterator();
            while (tlIterator.hasNext()) {
                TransportLocation tl = (TransportLocation) tlIterator.next();
                if (tl.getTransport().equals(transport)) {
                    tlIterator.remove();
                    transportLocation = tl;
                    break;
                }
            }
        }

        if (transportLocation == null) return false; // could not find transport

        addTransport(transport, location, tileCompartmentDirection);
        return true;
    }

    public Transport getTransport(TransportId transportId) {
        // TODO
        return null;
    }

    public Map<Location, List<TransportLocation>> getTransports() {
        return this.transports;
    }
}
