package game.model.transport;

import game.model.PlayerId;
import game.model.livestock.Livestock;

public class DonkeyTransport extends LandTransport implements Livestock {

    private static final int MOVE_CAP = 2;
    private static final int CARRY_CAP = 2;

//    private Vector<Ability> abilities;
    public DonkeyTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, MOVE_CAP, CARRY_CAP);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " " + this.getType().getName() +  " " + getResourceManager().getExportString();
    }

    @Override
    public TransportType getType() {
        return TransportType.DONKEY;
    }

    @Override
    public boolean canMoveOnLand()
    {
        return true;
    }

    @Override
    public boolean canReproduce() {
        return true;
    }

//    public void breed() {
//
//    }

}
