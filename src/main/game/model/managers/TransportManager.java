package game.model.managers;

import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.map.RBMap;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.model.transport.TransportLocation;

import java.util.*;

public class TransportManager {

    private Player player;
    private TransportAbilityManager transportAbilityManager;
    private Map<TileCompartmentLocation, List<Transport>> transports;
    public TransportManager(Player player, TransportAbilityManager transportAbilityManager) {
        this.player = player;
        this.transports = new HashMap<TileCompartmentLocation, List<Transport>>();
        this.transportAbilityManager = transportAbilityManager;
    }

    public PlayerId getPlayerId() {
        return this.player.getPlayerId();
    }

    public void addTransport(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        // TODO: enforce transport type limits
        if (transports.containsKey(tileCompartmentLocation)) {
            List<Transport> transportList = transports.get(tileCompartmentLocation);
            transportList.add(transport);
        } else {
            List<Transport> transportList = new ArrayList<>();
            transportList.add(transport);
            transports.put(tileCompartmentLocation, transportList);
        }
    }

    public boolean moveTransport(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        boolean foundTransport = false;
        Iterator it = transports.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileCompartmentLocation mapLocation = (TileCompartmentLocation) pair.getKey();
            List<Transport> mapTransportLocation = transports.get(tileCompartmentLocation);

            Iterator transportIterator = mapTransportLocation.iterator();
            while (transportIterator.hasNext()) {
                Transport iteratorTransport = (Transport) transportIterator.next();
                if (transportIterator.equals(transport)) {
                    transportIterator.remove();
                    foundTransport = true;
                    break;
                }
            }
        }

        if (!foundTransport) return false; // could not find transport

        addTransport(transport, tileCompartmentLocation);
        return true;
    }

    public Transport getTransport(TransportId transportId) {
        // TODO
        return null;
    }

    public Map<TileCompartmentLocation, List<Transport>> getTransports() {
        return this.transports;
    }

    public void onTransportSelected(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        this.transportAbilityManager.addAbilities(transport, tileCompartmentLocation);
    }

    public void onTransportUnselected() {
        this.transportAbilityManager.removeAbilities();
    }

    public TransportAbilityManager getTransportAbilityManager() { return this.transportAbilityManager; }
}
