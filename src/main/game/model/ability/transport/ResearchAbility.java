package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ResearchType;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.GooseManager;
import game.model.managers.ResearchManager;
import game.model.resources.ResourceType;
import game.model.tile.TileCompartment;

public class ResearchAbility extends Ability {
    private ResearchType researchType;
    private ResearchManager researchManager;
    private GooseManager gooseManager;
    public ResearchAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        researchManager.performResearch(researchType);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResearchType researchType, ResearchManager researchManger, GooseManager gooseManager, TileCompartment tileCompartment, TileCompartmentLocation tileCompartmentLocation) {
        this.researchType = researchType;
        this.gooseManager = gooseManager;
        tileCompartment.takeResource(ResourceType.PAPER, 1);
        gooseManager.removeGoose(tileCompartmentLocation, gooseManager.getMapGeese().get(tileCompartmentLocation).get(0));
        gooseManager.removeGoose(tileCompartmentLocation, gooseManager.getMapGeese().get(tileCompartmentLocation).get(0));

    }
    @Override
    public String getDisplayString() {
        return "Research "+researchType.toString();
    }
}
