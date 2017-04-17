package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.ResourceManager;
import game.model.structures.resourceProducer.secondaryProducer.StoneFactory;

public class ProduceStonesAbility extends Ability {
    private StoneFactory stoneFactory;
    private ResourceManager transportRm;
    public ProduceStonesAbility(MainViewController mainViewController) {
        super(mainViewController);
    }
    @Override
    public void perform() {
        mainViewController.detachControls();
        stoneFactory.produce(transportRm);
    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Produce Stones";
    }

    public void attachToController(ResourceManager transportRm, StoneFactory stoneFactory) {
        this.transportRm = transportRm;
        this.stoneFactory = stoneFactory;
    }
}
