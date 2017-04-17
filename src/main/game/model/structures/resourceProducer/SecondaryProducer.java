package game.model.structures.resourceProducer;

import game.model.structures.Structure;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public abstract class SecondaryProducer extends Structure {

    protected SecondaryProducer() {

    }

    public abstract boolean produce(TileCompartment tileCompartment);

    public abstract boolean produce(Transport transport);
}
