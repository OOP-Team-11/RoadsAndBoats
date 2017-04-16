package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.ClayPit;
import game.model.structures.resourceProducer.primaryProducer.Woodcutter;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;

public class BuildClayPitAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public BuildClayPitAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        this.resourceManager.removeResource(ResourceType.BOARDS, 1);
        ClayPit clayPit = new ClayPit();
//      TODO:  visitor.visit(claypit);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.resourceManager = transportRm;
        this.tileCompartmentLocation = tileCompartmentLocation;
        mainViewController.addControl(KeyCode.C, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Clay Pit";
    }
}
