package game.model.structures.transportProducer;

import game.model.resources.ResourceManager;
import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class TruckFactory extends TransportProducer {

    private static final int IRON_REQ = 1;
    private static final int FUEL_REQ = 2;

    // 1 Truck = 1 Iron + 1 Fuel
    public TruckFactory() {

    }

    @Override
    public Transport produce(Transport transport) {
        return null;
    }

    @Override
    public Transport produce(TileCompartment tileCompartment) {
        return null;
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
