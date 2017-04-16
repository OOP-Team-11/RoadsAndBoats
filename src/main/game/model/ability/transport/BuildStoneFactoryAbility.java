package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.StoneFactory;
import javafx.scene.input.KeyCode;

public class BuildStoneFactoryAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;
    public BuildStoneFactoryAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        this.resourceManager.removeResource(ResourceType.BOARDS, 2);
        StoneFactory stoneFactory = new StoneFactory();
//      structureManagerVisitor.addStructureVisit(stoneFactory, tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.Z, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Stone Factory";
    }
}
