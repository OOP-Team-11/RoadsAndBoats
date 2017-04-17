package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.CoalBurner;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildCoalBurnerAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;
    private StructureManagerVisitor structureManagerVisitor;
    public BuildCoalBurnerAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        this.resourceManager.removeResource(ResourceType.BOARDS, 3);
        CoalBurner coalBurner = new CoalBurner();
        structureManagerVisitor.addStructureVisit(coalBurner, tileCompartmentLocation);

    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.X, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Coal Burner";
    }
}
