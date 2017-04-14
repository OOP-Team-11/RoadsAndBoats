package game.model.ability.goose;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.resources.Goose;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;
import java.util.Vector;

public class FollowAbility extends Ability {
//    private MainViewController mainViewController;
    private Transport transport;
    private int gooseCount;
    private KeyCode keyCode;
    private Vector<Goose> availableGeese;

    public FollowAbility(MainViewController mainViewController) {
        super(mainViewController);

    }
    @Override
    public void perform() {
        transport.addFollowers(availableGeese);
    }

    @Override
    public void detachFromController() {
        this.mainViewController.removeControl(this.keyCode);
        transport.removeFollowers();
    }

    @Override
    public String getDisplayString() {
        return ("Get "+gooseCount+" followers.");
    }

    public void attachToController(Transport transport, int gooseCount, Vector<Goose> availableGeese) {
        this.transport = transport;
        this.gooseCount = gooseCount;
        this.availableGeese = availableGeese;
        this.keyCode = KeyCode.getKeyCode(Integer.toString(gooseCount));
        this.mainViewController.addControl(this.keyCode, this);

    }
}
