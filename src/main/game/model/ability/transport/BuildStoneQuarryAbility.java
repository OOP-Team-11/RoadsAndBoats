package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.StoneQuarry;
import game.model.structures.resourceProducer.primaryProducer.Woodcutter;
import javafx.scene.input.KeyCode;

public class BuildStoneQuarryAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public BuildStoneQuarryAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        this.resourceManager.removeResource(ResourceType.BOARDS, 2);
        StoneQuarry stoneQuarry = new StoneQuarry();
//      TODO:  visitor.visit(stoneQuarry)
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
        return "Build Stone Quarry";
    }
}
