package game.model.structures.transportProducer;

import game.model.direction.TileCompartmentLocation;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;
import game.model.visitors.TransportManagerVisitor;

public class RaftFactory extends TransportProducer {

    private static final int TRUNKS_REQ = 2;

    // 1 Raft <= 2 Trunks
    public RaftFactory(TileCompartmentLocation tileCompartmentLocation) {
        super(tileCompartmentLocation);
    }

    @Override
    public boolean produce(TransportManagerVisitor visitor, Transport transport, TileCompartmentLocation tcl) {
        if (canProduceRaft(transport)) {
            accept(visitor, transport, tcl);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(TileCompartment tileCompartment) {
        return canProduceRaft(tileCompartment);
    }

    private boolean canProduceRaft(Transport transport) {
        return transport.takeResource(ResourceType.TRUNKS, TRUNKS_REQ);
    }

    private boolean canProduceRaft(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.TRUNKS, TRUNKS_REQ);
    }

    @Override
    public StructureType getType() {
        return StructureType.RAFT_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
