package game.model.ability.goose;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.resources.Goose;
import game.model.resources.GooseId;

public class GooseReproduceAbility extends Ability {
    private TileCompartmentLocation tileCompartmentLocation;

    public GooseReproduceAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    public void attachToController(TileCompartmentLocation tileCompartmentLocation) {
        this.tileCompartmentLocation = tileCompartmentLocation;
    }

    @Override
    public void perform() {
//        TODO: Create a visitor that visits this and adds the new Goose to the GooseManager
        Goose goose = new Goose(new GooseId());

    }

    @Override
    public void detachFromController() {

    }

    @Override
    public String getDisplayString() {
        return "Breed Geese";
    }
}
