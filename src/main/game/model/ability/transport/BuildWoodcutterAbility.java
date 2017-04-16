package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;

public class BuildWoodcutterAbility extends Ability {
    private ResourceManager resourceManager;

    public BuildWoodcutterAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        this.resourceManager.removeResource(ResourceType.BOARDS, 1);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm) {
        this.resourceManager = transportRm;
    }
    @Override
    public String getDisplayString() {
        return "Build Woodcutter";
    }
}
