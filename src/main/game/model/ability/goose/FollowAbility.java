package game.model.ability.goose;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.TransportManager;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyListener;

public class FollowAbility extends Ability {
    private MainViewController mainViewController;
    private Transport transport;
    private int gooseCount;

    public FollowAbility(MainViewController mainViewController) {
        super(mainViewController);

    }
    @Override
    public void perform() {

    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return ("Get "+gooseCount+" followers.");
    }

    public void attachToController(Transport transport, int gooseCount) {
        this.transport = transport;
        this.gooseCount = gooseCount;
        KeyCode kc = KeyCode.getKeyCode("Digit"+gooseCount);
        mainViewController.addControl(kc, this);

    }
}
