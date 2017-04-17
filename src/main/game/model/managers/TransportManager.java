package game.model.managers;

import game.controller.MainViewController;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.map.RBMap;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.model.visitors.StructureManagerVisitor;
import game.model.visitors.TransportManagerVisitor;
import game.utilities.observable.MapTransportRenderInfoObservable;
import game.utilities.observer.MapTransportRenderInfoObserver;
import game.view.render.MapTransportRenderInfo;
import game.view.render.TransportRenderInfo;

import java.util.*;

public class TransportManager implements MapTransportRenderInfoObservable, TransportManagerVisitor {

    private PlayerId playerId;
    private TransportAbilityManager transportAbilityManager;
    private Map<TileCompartmentLocation, List<Transport>> transports;
    private List<MapTransportRenderInfoObserver> mapTransportRenderInfoObservers;
    public TransportManager(PlayerId playerId, MainViewController mainViewController,
                            GooseManager gooseManager, RBMap map,
                            StructureManagerVisitor structureManagerVisitor, ResearchManager researchManager) {
        this.playerId = playerId;
        this.transports = new HashMap<TileCompartmentLocation, List<Transport>>();
        this.mapTransportRenderInfoObservers = new Vector<>();
        this.transportAbilityManager = new TransportAbilityManager(mainViewController, gooseManager, map, this, structureManagerVisitor, researchManager);
        mainViewController.addTransportManager(this);
    }

    public PlayerId getPlayerId() {
        return this.playerId;
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

    public void removeTransport(Transport t) {
        for (List<Transport> transports : this.transports.values()) {
            transports.removeIf(transport -> transport.equals(t));
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

    private Transport getTransport(TransportId transportId, Location loc) {
        for(TileCompartmentDirection d : TileCompartmentDirection.getAllDirections()) {
            TileCompartmentLocation tilesCompartment = new TileCompartmentLocation(loc, d);
            if(transports.get(tilesCompartment) != null && (transports.get(tilesCompartment).size() > 0)) {
                for(Transport t : transports.get(tilesCompartment)) {
                    if(t.getTransportId() == transportId) {
                        return t;
                    }
                }
            }
        }
        return null;
    }

    private TileCompartmentLocation getTransportTileCompartmentLocation(TransportId transportId, Location loc) {
        for(TileCompartmentDirection d : TileCompartmentDirection.getAllDirections()) {
            TileCompartmentLocation tilesCompartment = new TileCompartmentLocation(loc, d);
//            Check that there is an index for the tileCompartmentLocation as well as exisiting transports
            if(transports.get(tilesCompartment) != null && (transports.get(tilesCompartment).size() > 0)) {
                for(Transport t : transports.get(tilesCompartment)) {
                    if(t.getTransportId() == transportId) {
                        return tilesCompartment;
                    }
                }
            }
        }
        return null;
    }

    public Map<TileCompartmentLocation, List<Transport>> getTransports() {
        return this.transports;
    }

    public void onTransportSelected(TransportId transportId, Location loc) {
        Transport transport = getTransport(transportId, loc);
        TileCompartmentLocation transportTCL = getTransportTileCompartmentLocation(transportId, loc);
        Map<TileCompartmentDirection, List<Transport>> tileTransports = getTileTransports(loc);
        if(transport != null)
            this.transportAbilityManager.addAbilities(transport, transportTCL, tileTransports);
    }

    public Map<TileCompartmentDirection, List<Transport>> getTileTransports(Location loc) {
        Map<TileCompartmentDirection, List<Transport>> tileTransports = new HashMap<TileCompartmentDirection, List<Transport>>();
        for(TileCompartmentDirection d : TileCompartmentDirection.getAllDirections()) {
            TileCompartmentLocation tilesCompartment = new TileCompartmentLocation(loc, d);
//            Check that there is an index for the tileCompartmentLocation as well as exisiting transports
            if(transports.get(tilesCompartment) != null && (transports.get(tilesCompartment).size() > 0)) {
                tileTransports.put(d, transports.get(tilesCompartment));
            }
        }
        return tileTransports;
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
        Map<TileCompartmentLocation, List<TransportRenderInfo>> transportRenderInfoMap = new HashMap<>();
        Iterator it = transports.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileCompartmentLocation tcl = (TileCompartmentLocation) pair.getKey();
            List<Transport> transportList = this.transports.get(tcl);
            List<TransportRenderInfo> transportRenderList = new ArrayList<>();
            for (Transport t : transportList) {
                TransportRenderInfo transportRenderInfo = new TransportRenderInfo(t);
                transportRenderList.add(transportRenderInfo);
            }
            transportRenderInfoMap.put(tcl, transportRenderList);
        }
        MapTransportRenderInfo mapTransportRenderInfo = new MapTransportRenderInfo(this.playerId, transportRenderInfoMap);
        for (MapTransportRenderInfoObserver observer : this.mapTransportRenderInfoObservers) {
            observer.updateMapTransportInfo(mapTransportRenderInfo);
        }
    }

    @Override
    public void attach(MapTransportRenderInfoObserver observer) {
        this.mapTransportRenderInfoObservers.add(observer);
        notifyMapTransportRenderInfoObservers();
    }

    @Override
    public void detach(MapTransportRenderInfoObserver observer) {
        this.mapTransportRenderInfoObservers.remove(observer);
    }

    @Override
    public void addTransportVisit(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        this.addTransport(transport, tileCompartmentLocation);
    }

    @Override
    public void removeTransportVisit(Transport transport) {
        this.removeTransport(transport);
    }
}
