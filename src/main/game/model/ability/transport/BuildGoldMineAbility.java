package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildGoldMineAbility extends Ability {

    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;
    private StructureManagerVisitor structureManagerVisitor;

    public BuildGoldMineAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        this.resourceManager.removeResource(ResourceType.BOARDS, 3);
        this.resourceManager.removeResource(ResourceType.STONE, 1);
        Mine goldMine = new Mine(4,4,0,0);
        structureManagerVisitor.addStructureVisit(goldMine, tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.G, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Gold Mine";
    }
}
