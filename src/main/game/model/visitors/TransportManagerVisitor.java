package game.model.visitors;

import game.model.direction.TileCompartmentLocation;
import game.model.transport.Transport;

public interface TransportManagerVisitor {
    void addTransportVisit(Transport transport, TileCompartmentLocation tileCompartmentLocation);
    void removeTransportVisit(Transport transport);
}
