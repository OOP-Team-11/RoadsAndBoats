package game.model.resources;

import game.model.transport.TransportId;

import java.util.Objects;

public class GooseId {

    private static int gooseId = 1;

    private int myGooseId;
    public GooseId() {
        myGooseId = gooseId;
        gooseId++;
    }

    public int getGooseIdValue() {
        return myGooseId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GooseId)) return false;

        GooseId gooseId = (GooseId) o;
        return gooseId.getGooseIdValue() == myGooseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myGooseId);
    }
}
