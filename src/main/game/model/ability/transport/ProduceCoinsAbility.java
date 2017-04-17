package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.ResourceManager;
import game.model.structures.resourceProducer.secondaryProducer.Mint;
import game.model.transport.Transport;

public class ProduceCoinsAbility extends Ability {
    private Mint mint;
    private ResourceManager transportRm;
    @Override
    public void perform() {
        mainViewController.detachControls();
        mint.produce(transportRm);
    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return null;
    }

    public void attachToController(ResourceManager transportRm, Mint mint) {
        this.transportRm = transportRm;
        this.mint = mint;
    }

    public ProduceCoinsAbility(MainViewController mainViewController) {
        super(mainViewController);
    }
}
