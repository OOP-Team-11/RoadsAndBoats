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
import java.util.Vector;

public class TransportAbilityManager {
    private GooseManager gooseManager;
    private AbilityFactory abilityFactory;
    private ArrayList<Ability> abilities;
    private RBMap map;
    private MainViewController mainViewController;

    public TransportAbilityManager(MainViewController mainViewController, GooseManager gooseManager) {
        this.mainViewController = mainViewController;
        this.abilityFactory = new AbilityFactory(mainViewController);
        this.abilities = new ArrayList<Ability>();
        this.gooseManager = gooseManager;
    }

    public void setMap(RBMap map) {
        this.map = map;
    }

    public RBMap getMap() { return map; }

    public int getAbilityCount() { return this.abilities.size(); }

    public void addAbilities(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        this.addFollowAbility(tileCompartmentLocation, transport);
//        fill out with reset of abilities --> "add_____Ability(loc, transport, tcd)"
    }

    public void removeAbilities() {
        for(Ability ability : abilities)
            ability.detachFromController();
    }

    public void addFollowAbility(TileCompartmentLocation tileCompartmentLocation, Transport transport) {
        List<Goose> availableGeese = gooseManager.getMapGeese().get(tileCompartmentLocation);
        Vector<Goose> abilityGeese = new Vector<Goose>();
        if(availableGeese == null)
            return;
        for(int gooseCount = 1; gooseCount < availableGeese.size()+1; gooseCount++) {
            abilityGeese.add(availableGeese.get(0));
            FollowAbility followAbility = abilityFactory.getFollowAbility();
            followAbility.attachToController(transport, gooseCount, abilityGeese);
            abilities.add(followAbility);
        }
    }
}
