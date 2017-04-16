package game.model.structures.transportProducer;

import game.model.managers.TransportManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.RaftTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;

public class RaftFactory extends TransportProducer {

    private static final int TRUNKS_REQ = 2;

    // 1 Raft <= 2 Trunks
    public RaftFactory() {

    }

    @Override
    public Transport produce(Transport transport) {
        if (transport.takeResource(ResourceType.TRUNKS, TRUNKS_REQ)) {
            return new RaftTransport(transport.getPlayerId(), new TransportId());
        }
        return null;
    }

    @Override
    public Transport produce(TileCompartment tileCompartment) {
        return null;
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
