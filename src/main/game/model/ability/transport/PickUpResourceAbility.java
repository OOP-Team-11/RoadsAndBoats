package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import javafx.scene.input.KeyCode;

import javax.annotation.Resource;

public class PickUpResourceAbility extends Ability {
    private ResourceManager tileCompartmentRm;
    private ResourceManager transportRm;
    private ResourceType resourceType;

    public PickUpResourceAbility(MainViewController mainViewController, ResourceType resourceType) {
        super(mainViewController);
        this.resourceType = resourceType;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        tileCompartmentRm.removeResource(resourceType, 1);
        transportRm.addResource(resourceType,1);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager tileCompartmentRm, ResourceManager transportRm, int keyCodeIndex) {
        this.tileCompartmentRm = tileCompartmentRm;
        this.transportRm = transportRm;
        KeyCode kc = KeyCode.getKeyCode(Integer.toString(keyCodeIndex));
        mainViewController.addControl(kc, this);
    }
    @Override
    public String getDisplayString() {
        return "Pick up 1 "+ resourceType.getExportString();
    }
}
