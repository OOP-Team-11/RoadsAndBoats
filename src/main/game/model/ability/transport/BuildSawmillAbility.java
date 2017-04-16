package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Sawmill;
import javafx.scene.input.KeyCode;

public class BuildSawmillAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public BuildSawmillAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        this.resourceManager.removeResource(ResourceType.BOARDS, 2);
        this.resourceManager.removeResource(ResourceType.STONE, 1);
        Sawmill sawmill = new Sawmill();
//       TODO:  visitor.visit(sawmill);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.S, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Sawmill";
    }
}
