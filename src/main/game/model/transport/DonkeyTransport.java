package game.model.transport;

import game.model.PlayerId;
import game.model.livestock.Livestock;

public class DonkeyTransport extends LandTransport implements Livestock {

//    private Vector<Ability> abilities;

    public DonkeyTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " DONKEY " + getResourceManager().getExportString();
    }

//    public void breed() {
//
//    }

}
