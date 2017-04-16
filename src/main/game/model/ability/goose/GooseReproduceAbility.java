package game.model.ability.goose;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.resources.Goose;
import game.model.resources.GooseId;
import game.model.visitors.GooseManagerVisitor;

public class GooseReproduceAbility extends Ability {
    private TileCompartmentLocation tileCompartmentLocation;
    private GooseManagerVisitor gooseManagerVisitor;
    public GooseReproduceAbility(MainViewController mainViewController, GooseManagerVisitor gooseManagerVisitor) {
        super(mainViewController);
        this.gooseManagerVisitor = gooseManagerVisitor;
    }

    public void attachToController(TileCompartmentLocation tileCompartmentLocation) {
        this.tileCompartmentLocation = tileCompartmentLocation;
    }

    @Override
    public void perform() {
//        TODO: Create a visitor that visits this and adds the new Goose to the GooseManager
        Goose goose = new Goose(new GooseId());
        gooseManagerVisitor.addGooseVisit(goose, tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Breed Geese";
    }
}
