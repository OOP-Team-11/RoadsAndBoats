package game.model.managers;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.ability.goose.FollowAbility;
import game.model.direction.TileCompartmentLocation;
import game.model.factory.AbilityFactory;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.transport.Transport;

import java.util.ArrayList;
import java.util.List;

public class TransportAbilityManager {
    private GooseManager gooseManager;
    private AbilityFactory abilityFactory;
    private ArrayList<Ability> abilities;
    private RBMap map;
    private MainViewController mainViewController;

    public TransportAbilityManager(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        this.abilityFactory = new AbilityFactory(mainViewController);
        this.abilities = new ArrayList<Ability>();
    }

    public void setMap(RBMap map) {
        this.map = map;
    }

    public RBMap getMap() {
        return map;
    }

    public void addAbilities(TileCompartmentLocation tileCompartmentLocation, Transport transport) {
        this.addFollowAbility(tileCompartmentLocation, transport);
//        fill out with reset of abilities --> "add_____Ability(loc, transport, tcd)"
    }

    public void addFollowAbility(TileCompartmentLocation tileCompartmentLocation, Transport transport) {
        List<Goose> availableGeese = gooseManager.getMapGeese().get(tileCompartmentLocation);
        if(availableGeese == null)
            return;
        for(int gooseCount = 0; gooseCount < availableGeese.size(); gooseCount++) {
            FollowAbility followAbility = abilityFactory.getFollowAbility();
            followAbility.attachToController(transport, gooseCount);

        }
    }
}
