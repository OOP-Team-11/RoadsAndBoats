package game.model.transport;

import game.model.direction.TileCompartmentDirection;

import java.util.Objects;

public class TransportLocation {

    private Transport transport;
    private TileCompartmentDirection tileCompartmentDirection;

    public TransportLocation(Transport transport, TileCompartmentDirection tileCompartmentDirection) {
        this.transport = transport;
        this.tileCompartmentDirection = tileCompartmentDirection;
    }


    @Override
    public int hashCode() {
        return Objects.hash(transport, tileCompartmentDirection);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TransportLocation))
            return false;
        if (object == this)
            return true;

        TransportLocation tl = (TransportLocation) object;
        return tl.transport.equals(transport) && tl.tileCompartmentDirection.equals(tileCompartmentDirection);
    }

    public Transport getTransport() {
        return transport;
    }

    public TileCompartmentDirection getTileCompartmentDirection() {
        return tileCompartmentDirection;
    }
}
