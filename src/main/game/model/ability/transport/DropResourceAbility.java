package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.ResourceManager;
import game.model.managers.TransportManager;
import game.model.resources.ResourceType;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;

public class DropResourceAbility extends Ability {
    private ResourceType resourceType;
    private ResourceManager transportRm;
    private ResourceManager tileCompartmentRm;

    public DropResourceAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        transportRm.removeResource(this.resourceType, 1);
        this.tileCompartmentRm.addResource(this.resourceType, 1);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager tileCompartmentRm, ResourceManager transportRm, ResourceType resource, int index) {
        this.tileCompartmentRm = tileCompartmentRm;
        this.transportRm = transportRm;
        this.resourceType = resource;
        KeyCode kc = KeyCode.getKeyCode(Integer.toString(index));
        mainViewController.addControl(kc, this);
    }
    @Override
    public String getDisplayString() {
        return "Drop 1 "+this.resourceType;
    }
}
