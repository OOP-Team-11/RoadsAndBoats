package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.Woodcutter;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildWoodcutterAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;
    private StructureManagerVisitor structureManagerVisitor;

    public BuildWoodcutterAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        this.resourceManager.removeResource(ResourceType.BOARDS, 1);
        Woodcutter woodcutter = new Woodcutter();
        structureManagerVisitor.addStructureVisit(woodcutter, tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.W, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Woodcutter";
    }
}
