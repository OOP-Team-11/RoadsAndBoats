package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;

public class MoveAbility extends Ability {

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

    public void attachToController() {

    }
    @Override
    public String getDisplayString() {
        return "Move";
    }
}
