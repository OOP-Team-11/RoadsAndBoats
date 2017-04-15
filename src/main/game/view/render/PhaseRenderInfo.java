package game.view.render;

import game.model.tinyGame.Phase;

public class PhaseRenderInfo {

    private Phase phase;
    public PhaseRenderInfo(Phase phase) {
        this.phase = phase;
    }

    public String getName() {
        return phase.getCurrentPhaseName();
    }

}
