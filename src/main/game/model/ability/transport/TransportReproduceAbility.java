package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.transport.DonkeyTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.model.visitors.TransportManagerVisitor;
import javafx.scene.input.KeyCode;

public class TransportReproduceAbility extends Ability {
    private Transport transport;
    private TileCompartmentLocation transportLocation;
    private TransportManagerVisitor transportManagerVisitor;
    public TransportReproduceAbility(MainViewController mainViewController, TransportManagerVisitor transportManagerVisitor) {
        super(mainViewController);
        this.transportManagerVisitor = transportManagerVisitor;
    }

    public void attachToController(Transport transport, TileCompartmentLocation transportLocation) {
        this.transport = transport;
        this.transportLocation = transportLocation;
        mainViewController.addControl(KeyCode.R, this);
    }

    @Override
    public void perform() {
        DonkeyTransport newDonkey = new DonkeyTransport(transport.getPlayerId(), new TransportId());
        transportManagerVisitor.addTransportVisit(newDonkey, transportLocation);
    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Breed";
    }
}
