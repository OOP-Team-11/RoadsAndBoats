package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.PlayerId;
import game.model.ability.Ability;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.wonder.WonderManager;
import javafx.scene.input.KeyCode;

public class BuyWonderBrickAbility extends Ability {
    private ResourceManager transportRm;
    private WonderManager wonderManager;
    private PlayerId playerId;

    public BuyWonderBrickAbility(MainViewController mainViewController) { super(mainViewController); }

    public void attachToController(ResourceManager transportRm, WonderManager wonderManager, PlayerId playerId) {
        this.transportRm = transportRm;
        this.wonderManager = wonderManager;
        this.playerId = playerId;
        mainViewController.addControl(KeyCode.W, this);
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        for(ResourceType resourceType : ResourceType.values()) {
            if(transportRm.hasResource(resourceType)) {
                transportRm.removeResource(resourceType, 1);
                wonderManager.addBrick(this.playerId);
            }
        }
    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Buy wonder brick!";
    }
}
