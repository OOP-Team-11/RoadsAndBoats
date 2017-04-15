package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;

public class DropFollowersAbility extends Ability {
    public DropFollowersAbility(MainViewController mainViewController) {
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
        return null;
    }

    public void attachToController() {
        return;
    }
}
