package game.model.structures.transportProducer;

import game.model.structures.StructureType;

public class SteamerFactory extends TransportProducer {

    SteamerFactory() {

    }

    @Override
    public StructureType getType() {
        return StructureType.STEAMER_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
