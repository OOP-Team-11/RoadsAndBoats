package game.model.ability.transportProducer;

import game.controller.MainViewController;
import game.model.ability.Ability;

public class ProduceTransportAbility extends Ability {
    public ProduceTransportAbility(MainViewController mainViewController) {
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
        return "TINY RICK";
    }
}
