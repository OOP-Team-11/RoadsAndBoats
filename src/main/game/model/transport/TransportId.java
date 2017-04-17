package game.model.transport;

import java.util.Objects;

public class TransportId {

    private static int transportId = 1;

    private int myTransportId;

    public TransportId() {
        this.myTransportId = transportId;
        transportId++;
    }

    /**
     * Used by map importer
     * @param id
     */
    public TransportId(int id) {
        this.myTransportId = id;
        if (id > transportId) transportId = id+1;
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
