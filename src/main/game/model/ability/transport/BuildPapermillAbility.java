package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Papermill;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildPapermillAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;
    private StructureManagerVisitor structureManagerVisitor;

    public BuildPapermillAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        this.resourceManager.removeResource(ResourceType.BOARDS, 1);
        this.resourceManager.removeResource(ResourceType.STONE, 1);
        Papermill papermill = new Papermill();
        structureManagerVisitor.addStructureVisit(papermill, tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }
    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.P, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Papermill";
    }
}
