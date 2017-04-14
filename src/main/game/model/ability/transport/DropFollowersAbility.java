package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;

public class DropFollowersAbility extends Ability {
    private Transport transport;

    public DropFollowersAbility(MainViewController mainViewController) {
        super(mainViewController);
    }
    @Override
    public void perform() {
        transport.removeFollowers();
    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Stop geese following";
    }

    public void attachToController(Transport transport) {
        this.transport = transport;
        mainViewController.addControl(KeyCode.D, this);
    }
}
