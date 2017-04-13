package game.view;

import game.view.utilities.Assets;

public abstract class View {

    protected Assets assets = Assets.getInstance();

    public abstract void render();

}
