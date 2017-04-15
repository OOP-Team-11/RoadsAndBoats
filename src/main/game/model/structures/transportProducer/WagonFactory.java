package game.model.structures.transportProducer;

import game.model.structures.StructureType;

public class WagonFactory extends TransportProducer {

    public WagonFactory() {

    }

    @Override
    public StructureType getType() {
        return StructureType.WAGON_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
