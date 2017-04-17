package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.TransportManager;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;

public class PickUpTransportAbility extends Ability {
    private Transport transport;
    private Transport transportToPickUp;
    private TransportManager transportManager;

    public PickUpTransportAbility(MainViewController mainViewController) {
        super(mainViewController);
    }
    @Override
    public void perform() {
        mainViewController.detachControls();
        transport.storeTransport(transportToPickUp);
        transportManager.removeTransport(transportToPickUp);
    }

    public void attachToController(Transport transportToPickUp, Transport transport, TransportManager transportManager) {
        this.transportToPickUp = transportToPickUp;
        this.transport = transport;
        this.transportManager = transportManager;
        mainViewController.addControl(KeyCode.P, this);
    }
    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Pick up " +transportToPickUp.getType().toString();
    }
}
