package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.RowboatFactory;
import game.model.structures.transportProducer.SteamerFactory;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildSteamshipFactoryAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;
    private StructureManagerVisitor structureManagerVisitor;

    public BuildSteamshipFactoryAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        this.resourceManager.removeResource(ResourceType.BOARDS, 1);
        this.resourceManager.removeResource(ResourceType.STONE, 1);
        SteamerFactory steamshipFactory = new SteamerFactory();
        structureManagerVisitor.addStructureVisit(steamshipFactory, tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }
    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.N, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Steamer Factory";
    }
}
