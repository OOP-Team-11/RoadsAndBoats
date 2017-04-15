package game.model.transport;

import game.model.PlayerId;
import game.model.livestock.Livestock;

public class DonkeyTransport extends LandTransport implements Livestock {

//    private Vector<Ability> abilities;
    public DonkeyTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, 2, 2);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " " + this.getType().getName() +  " " + getResourceManager().getExportString();
    }

    @Override
    public TransportType getType() {
        return TransportType.DONKEY;
    }

//    public void breed() {
//
//    }

}
