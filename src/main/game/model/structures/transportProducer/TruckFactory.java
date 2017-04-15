package game.model.structures.transportProducer;

import game.model.structures.StructureType;

public class TruckFactory extends TransportProducer {

    TruckFactory() {

    }

    @Override
    public StructureType getType() {
        return StructureType.TRUCK_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
