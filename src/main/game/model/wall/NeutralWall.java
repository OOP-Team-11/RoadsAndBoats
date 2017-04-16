package game.model.wall;

import game.model.PlayerId;

public class NeutralWall extends Wall {

    private int wallToken;

    public NeutralWall() {

    }

    public boolean strengthen(PlayerId pid) {
        return false;
    }

    public int getStrengthenCost() {
        return 0;
    }

    @Override
    public boolean isNeutral() {
        return true;
    }

}
