package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;

public class ResearchAbility extends Ability {

    public ResearchAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {

    }

    @Override
    public void detachFromController() {

    }

    public void attachToController() {

    }
    @Override
    public String getDisplayString() {
        return "Research";
    }
}
