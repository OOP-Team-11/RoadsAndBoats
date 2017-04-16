package game.model.structures.transportProducer;

import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class WagonFactory extends TransportProducer {

    private static final int BOARD_REQ = 2;

    // 1 Wagon <= 1 Donkey + 2 Boards
    public WagonFactory() {

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
        return StructureType.WAGON_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
