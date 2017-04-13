package game.model.managers;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.Location;
import game.model.factory.AbilityFactory;

import java.util.ArrayList;

public class TransportAbilityManager {
    private AbilityFactory abilityFactory;
    private ArrayList<Ability> abilities;
    private MainViewController mainViewController;

    public TransportAbilityManager(MainViewController mainViewController, AbilityFactory abilityFactory) {
        this.mainViewController = mainViewController;
        this.abilityFactory = abilityFactory;
        this.abilities = new ArrayList<Ability>();
    }

//    public void attachAbilites(Location loc, Transport transport) {
//        addFollowAbility()
//    }

    public void addFollowAbility() {

    }



}
