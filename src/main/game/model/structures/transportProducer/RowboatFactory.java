package game.model.structures.transportProducer;

import game.model.resources.ResourceManager;
import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class RowboatFactory extends TransportProducer {

    private static final int BOARDS_REQ = 5;

    // 1 Rowboat <= 5 Boards
    public RowboatFactory() {

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
        return StructureType.ROWBOAT_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
