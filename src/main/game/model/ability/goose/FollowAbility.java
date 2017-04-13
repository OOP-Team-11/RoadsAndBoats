package game.model.ability.goose;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.TransportManager;
import game.model.transport.TransportId;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyListener;

public class FollowAbility implements Ability {
    private MainViewController mainViewController;
    private TransportId transportId;
    private TransportManager transportManager;
    private int gooseCount;

    public FollowAbility(MainViewController mainViewController) {
        this.mainViewController = mainViewController;

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

    public void attachToController(TransportId transportId, TransportManager transManager, int gooseCount) {
        this.transportId = transportId;
        this.transportManager = transManager;
        this.gooseCount = gooseCount;
        KeyCode kc = KeyCode.getKeyCode("Digit"+gooseCount);
        mainViewController.addControl(kc, this);

    }
}
