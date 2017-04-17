package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;

public class DropResourceAbility extends Ability {
    private ResourceType resourceType;
    private Transport transport;
    private TileCompartment tileCompartment;
    public DropResourceAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        transport.getResourceManager().removeResource(this.resourceType, 1);
        this.tileCompartment.storeResource(this.resourceType, 1);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager tileCompartmentRm, ResourceManager transportRm, ResourceType resource) {
        this.transport = transport;
        this.tileCompartment = tileCompartment;
        this.resourceType = resourceType;
        mainViewController.addControl(KeyCode.Q, this);
    }
    @Override
    public String getDisplayString() {
        return "Drop 1 "+this.resourceType;
    }
}
