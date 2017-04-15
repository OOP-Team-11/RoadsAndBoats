package game.model.structures.transportProducer;

import game.model.structures.StructureType;

public class RaftFactory extends TransportProducer {

    RaftFactory() {

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
