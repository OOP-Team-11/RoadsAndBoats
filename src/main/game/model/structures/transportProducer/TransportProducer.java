package game.model.structures.transportProducer;

import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportManager;
import game.model.structures.Structure;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;
import game.model.visitors.TransportManagerVisitor;

public abstract class TransportProducer extends Structure {

    private TileCompartmentLocation tileCompartmentLocation;

    TransportProducer(TileCompartmentLocation tcl) {
        this.tileCompartmentLocation = tcl;
    }

    public abstract boolean produce(TransportManagerVisitor visitor, Transport transport, TileCompartmentLocation tcl);

    public abstract boolean produce(TileCompartment tileCompartment);

    protected void accept(TransportManagerVisitor visitor, Transport transport, TileCompartmentLocation tcl) {
        visitor.addTransportVisit(transport, tcl);
    }

    public TileCompartmentLocation getTileCompartmentLocation() {
        return tileCompartmentLocation;
    }

}
