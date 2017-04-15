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
import game.utilities.observable.MapTransportRenderInfoObservable;
import game.utilities.observer.MapStructureRenderInfoObserver;
import game.utilities.observer.MapTransportRenderInfoObserver;
import game.view.render.MapStructureRenderInfo;
import game.view.render.MapTransportRenderInfo;
import game.view.render.StructureRenderInfo;
import game.view.render.TransportRenderInfo;

import java.util.*;

public class TransportManager implements MapTransportRenderInfoObservable{

    private Player player;
    private TransportAbilityManager transportAbilityManager;
    private Map<TileCompartmentLocation, List<Transport>> transports;
    private List<MapTransportRenderInfoObserver> mapTransportRenderInfoObservers;
    public TransportManager(Player player, TransportAbilityManager transportAbilityManager) {
        this.player = player;
        this.transports = new HashMap<TileCompartmentLocation, List<Transport>>();
        this.mapTransportRenderInfoObservers = new Vector<>();
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

        notifyMapTransportRenderInfoObservers();
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
        notifyMapTransportRenderInfoObservers();
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
        Map<TileCompartmentDirection, List<Transport>> tileTransports = new HashMap<TileCompartmentDirection, List<Transport>>();
        for(TileCompartmentDirection d : TileCompartmentDirection.getAllDirections()) {
            TileCompartmentLocation tilesCompartment = new TileCompartmentLocation(tileCompartmentLocation.getLocation(), d);
//            Check that there is an index for the tileCompartmentLocation as well as exisiting transports
            if(transports.get(tilesCompartment) != null && (transports.get(tilesCompartment).size() > 0)) {
                tileTransports.put(d, transports.get(tilesCompartment));
            }
        }
        this.transportAbilityManager.addAbilities(transport, tileCompartmentLocation, tileTransports);
    }

    public void onTransportUnselected() {
        this.transportAbilityManager.removeAbilities();
    }

    public TransportAbilityManager getTransportAbilityManager() { return this.transportAbilityManager; }

    private Transport getTransport(TransportId transportId, TileCompartmentLocation tcl) {
        for (Transport transport : this.transports.get(tcl)) {
            if (transport.getTransportId() == transportId)
                return transport;
        }
        return null;
    }

    public void notifyMapTransportRenderInfoObservers() {
        Map<TileCompartmentLocation, TransportRenderInfo> transportRenderInfoMap = new HashMap<>();
        Iterator it = transports.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileCompartmentLocation tcl = (TileCompartmentLocation) pair.getKey();
            List<Transport> transportList = this.transports.get(tcl);
            for (Transport t : transportList) {
                TransportRenderInfo transportRenderInfo = new TransportRenderInfo(t);
                transportRenderInfoMap.put(tcl, transportRenderInfo);
            }
        }
        MapTransportRenderInfo mapTransportRenderInfo = new MapTransportRenderInfo(transportRenderInfoMap);
        for (MapTransportRenderInfoObserver observer : this.mapTransportRenderInfoObservers) {
            observer.updateMapTransportInfo(mapTransportRenderInfo);
        }
    }

    @Override
    public void attach(MapTransportRenderInfoObserver observer) {
        this.mapTransportRenderInfoObservers.add(observer);
    }

    @Override
    public void detach(MapTransportRenderInfoObserver observer) {
        this.mapTransportRenderInfoObservers.remove(observer);
    }
}
