package game.model.wall;

import game.model.PlayerId;

public class PlayerWall extends Wall {

    private PlayerId pid;
    private int wallToken;

    public PlayerWall() {

    }

    public boolean canCross(PlayerId pid) {
        return false;
    }

    public boolean strengthen(PlayerId pid) {
        return false;
    }

    public boolean demolish(PlayerId pid) {
        return false;
    }

    public int getDemolishCost() {
        return 0;
    }

    public int getStrengthCost() {
        return 0;
    }

    @Override
    public boolean isNeutral() {
        return false;
    }

}
