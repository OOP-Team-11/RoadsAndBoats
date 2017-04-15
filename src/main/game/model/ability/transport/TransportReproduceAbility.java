package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.transport.DonkeyTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import javafx.scene.input.KeyCode;

public class TransportReproduceAbility extends Ability {
    private Transport transport;
    private TileCompartmentLocation transportLocation;
    public TransportReproduceAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    public void attachToController(Transport transport, TileCompartmentLocation transportLocation) {
        this.transport = transport;
        this.transportLocation = transportLocation;
        mainViewController.addControl(KeyCode.R, this);
    }
    @Override
    public void perform() {
//        TODO: Need a visitor/observer of some sort to get the new Transport that has been produced
        DonkeyTransport newDonkey = new DonkeyTransport(transport.getPlayerId(), new TransportId());
        System.out.println("Reproduced!");
    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Breed";
    }
}
