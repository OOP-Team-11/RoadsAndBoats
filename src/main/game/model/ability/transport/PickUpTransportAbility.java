package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.transport.Transport;

public class PickUpTransportAbility extends Ability {
    private Transport transport;
    private Transport transportToPickUp;

    public PickUpTransportAbility(MainViewController mainViewController) {
        super(mainViewController);
    }
    @Override
    public void perform() {
        mainViewController.detachControls();
        transport.storeTransport(transportToPickUp);
    }

    public void attachToController(Transport transportToPickUp, Transport transport) {
        this.transportToPickUp = transportToPickUp;
        this.transport = transport;
    }
    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Pick up " +transportToPickUp.getExportString();
    }
}
