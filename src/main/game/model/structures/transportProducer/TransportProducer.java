package game.model.structures.transportProducer;

import game.model.structures.Structure;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public abstract class TransportProducer extends Structure {

    TransportProducer() {

    }

    public abstract Transport produce(Transport transport);

    public abstract boolean produce(TileCompartment tileCompartment);

}
