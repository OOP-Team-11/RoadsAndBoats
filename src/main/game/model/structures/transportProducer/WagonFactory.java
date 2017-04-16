package game.model.structures.transportProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.DonkeyTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;

public class WagonFactory extends TransportProducer {

    private static final int BOARD_REQ = 2;

    // 1 Wagon <= 1 Donkey + 2 Boards
    public WagonFactory() {

    }

    @Override
    public Transport produce(Transport transport) {
        //TODO Remove the DonkeyTransport passed in from TransportManager (DonkeyTransport passed becomes the Donkey for pulling the Wagon)
        if (canProduceWagon(transport)) {
            return new DonkeyTransport(transport.getPlayerId(), new TransportId());
        }
        return null;
    }

    @Override
    public boolean produce(TileCompartment tileCompartment) {
        return canProduceWagon(tileCompartment);
    }

    private boolean canProduceWagon(Transport transport) {
        return transport.takeResource(ResourceType.BOARDS, BOARD_REQ);
    }

    private boolean canProduceWagon(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.BOARDS, BOARD_REQ);
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
