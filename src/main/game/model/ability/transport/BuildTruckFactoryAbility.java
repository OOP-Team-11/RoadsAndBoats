package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.managers.StructureManager;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.RowboatFactory;
import game.model.structures.transportProducer.TruckFactory;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildTruckFactoryAbility extends Ability {
    private final StructureManagerVisitor structureManagerVisitor;
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public BuildTruckFactoryAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        this.resourceManager.removeResource(ResourceType.BOARDS, 2);
        this.resourceManager.removeResource(ResourceType.STONE, 2);
        TruckFactory truckFactory = new TruckFactory();
        structureManagerVisitor.addStructureVisit(truckFactory, tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }
    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.R, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Truck Factory";
    }
}
