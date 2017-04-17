package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.ResourceManager;
import game.model.structures.resourceProducer.secondaryProducer.Sawmill;
import game.model.structures.resourceProducer.secondaryProducer.StoneFactory;

public class ProduceBoardsAbility extends Ability {
    private Sawmill sawmill;
    private ResourceManager transportRm;
    public ProduceBoardsAbility(MainViewController mainViewController) {
        super(mainViewController);
    }
    @Override
    public void perform() {
        mainViewController.detachControls();
        sawmill.produce(transportRm);
    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Produce boards";
    }

    public void attachToController(ResourceManager transportRm, Sawmill sawmill) {
        this.transportRm = transportRm;
        this.sawmill = sawmill;
    }
}
