package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Mint;
import game.model.transport.Transport;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildMintAbility extends Ability {
    private StructureManagerVisitor structureManagerVisitor;
    private TileCompartmentLocation tileCompartmentLocation;
    private ResourceManager resourceManager;

    public BuildMintAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        resourceManager.removeResource(ResourceType.BOARDS, 2);
        resourceManager.removeResource(ResourceType.STONE, 1);
        Mint mint = new Mint();
        this.structureManagerVisitor.addStructureVisit(mint, this.tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.tileCompartmentLocation = tileCompartmentLocation;
        this.resourceManager = transportRm;
        mainViewController.addControl(KeyCode.V, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Mint";
    }
}
