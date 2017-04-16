package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Papermill;
import javafx.scene.input.KeyCode;

public class BuildPapermillAbility extends Ability {
    private ResourceManager resourceManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public BuildPapermillAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        this.resourceManager.removeResource(ResourceType.BOARDS, 1);
        this.resourceManager.removeResource(ResourceType.STONE, 1);
        Papermill papermill = new Papermill();
//        visitor.visit(papermill);

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
        return "TINY RICK";
    }
}
