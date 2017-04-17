package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportManager;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;

public class DropTransportAbility extends Ability {
    private Transport transport;
    private TransportManager transportManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public DropTransportAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        transportManager.addTransport(transport.removeTransport(), tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(Transport transport, TransportManager transportManager, TileCompartmentLocation tileCompartmentLocation) {
        this.transport = transport;
        this.transportManager = transportManager;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.D, this);
    }
    @Override
    public String getDisplayString() {
        return "Drop Transport";
    }
}
