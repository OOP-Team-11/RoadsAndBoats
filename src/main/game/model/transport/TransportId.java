package game.model.transport;

import java.util.Objects;

public class TransportId {

    private static int transportId = 1;

    private int myTransportId;

    public TransportId() {
        this.myTransportId = transportId;
        transportId++;
    }

    public int getTransportIdValue() {
        return myTransportId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TransportId)) return false;

        TransportId transportId = (TransportId) o;
        return transportId.getTransportIdValue() == myTransportId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myTransportId);
    }
}
