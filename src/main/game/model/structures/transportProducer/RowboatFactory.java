package game.model.structures.transportProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.RowboatTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;

public class RowboatFactory extends TransportProducer {

    private static final int BOARDS_REQ = 5;

    // 1 Rowboat <= 5 Boards
    public RowboatFactory() {

    }

    @Override
    public Transport produce(Transport transport) {
        if (canProduceRowboat(transport)) {
            return new RowboatTransport(transport.getPlayerId(), new TransportId());
        }
        return null;
    }

    @Override
    public boolean produce(TileCompartment tileCompartment) {
        return canProduceRowboat(tileCompartment);
    }

    private boolean canProduceRowboat(Transport transport) {
        return transport.takeResource(ResourceType.BOARDS, BOARDS_REQ);
    }

    private boolean canProduceRowboat(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.BOARDS, BOARDS_REQ);
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
