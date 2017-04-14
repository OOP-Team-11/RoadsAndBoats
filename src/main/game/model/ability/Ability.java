package game.model.ability;

import game.controller.MainViewController;

public abstract class Ability {
    protected MainViewController mainViewController;
    public abstract void perform();
    public abstract void detachFromController();
    public abstract String getDisplayString();

    public Ability(MainViewController mainViewController) { this.mainViewController = mainViewController; }
}
