package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.TransportManager;
import game.model.movement.Move;

public class MoveAbility extends Ability {

    private Move moveObj;

    public MoveAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        mainViewController.detachControls();

    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(Transport transport, Move moveObj, TransportManager transportManager) {
        this.moveObj = moveObj;
    }
    @Override
    public String getDisplayString() {
        return "Move "+moveObj.getTileEdgeDirection();
    }
}
