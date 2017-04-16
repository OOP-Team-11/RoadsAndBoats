package game.model.structures.transportProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;

public class SteamerFactory extends TransportProducer {

    private static final int IRON_REQ = 1;
    private static final int FUEL_REQ = 2;

    // 1 Steamship <= 1 Iron + 2 Fuel
    public SteamerFactory() {

    }

    @Override
    public Transport produce(Transport transport) {
        if (canProduceSteamer(transport)) {
            return new SteamShipTransport(transport.getPlayerId(), new TransportId());
        }
        return null;
    }

    @Override
    public boolean produce(TileCompartment tileCompartment) {
        return canProduceSteamer(tileCompartment);
    }

    private boolean canProduceSteamer(Transport transport) {
        return transport.takeResource(ResourceType.IRON, IRON_REQ)
                && transport.takeResource(ResourceType.FUEL, FUEL_REQ);
    }

    private boolean canProduceSteamer(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.IRON, IRON_REQ)
                && tileCompartment.takeResource(ResourceType.FUEL, FUEL_REQ);
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
