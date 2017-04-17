package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;

public class DropTransportAbility extends Ability {
    private Transport transport;

    public DropTransportAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        transport.removeTransport();
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(Transport transport) {
        this.transport = transport;
        mainViewController.addControl(KeyCode.D, this);
    }
    @Override
    public String getDisplayString() {
        return "Drop Transport";
    }
}
