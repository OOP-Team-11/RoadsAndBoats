package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.StoneQuarry;
import game.model.structures.resourceProducer.primaryProducer.Woodcutter;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildStoneQuarryAbility extends Ability {
    private StructureManagerVisitor structureManagerVisitor;
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public BuildStoneQuarryAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        this.resourceManager.removeResource(ResourceType.BOARDS, 2);
        StoneQuarry stoneQuarry = new StoneQuarry();
        structureManagerVisitor.addStructureVisit(stoneQuarry, tileCompartmentLocation);
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
