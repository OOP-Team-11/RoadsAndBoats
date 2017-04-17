package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildIronMineAbility extends Ability {
    private StructureManagerVisitor structureManagerVisitor;
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public BuildIronMineAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        this.resourceManager.removeResource(ResourceType.BOARDS, 3);
        this.resourceManager.removeResource(ResourceType.STONE, 1);
        Mine ironMine = new Mine(0,0,4,4);
        structureManagerVisitor.addStructureVisit(ironMine, tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.I, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Iron Mine";
    }
}
