package game.model.structures.transportProducer;

import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class SteamerFactory extends TransportProducer {

    private static final int IRON_REQ = 1;
    private static final int FUEL_REQ = 2;

    // 1 Steamship <= 1 Iron + 2 Fuel
    public SteamerFactory() {

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
        return StructureType.STEAMER_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
