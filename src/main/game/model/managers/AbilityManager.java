package game.model.managers;

import game.controller.MainViewController;
import game.model.ability.Ability;

import java.util.ArrayList;

public class AbilityManager {
    private ArrayList<Ability> abilities;
    private MainViewController mainViewController;

    public AbilityManager(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }
}
