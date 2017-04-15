package game.model.tinyGame;

import java.util.ArrayList;
import java.util.List;

public class Phase {

    private Integer currentPhase;
    private List<String> phases;

    public Phase() {
        this.currentPhase = 0;
        this.phases = new ArrayList<>();
        initializePhases();
    }

    private void initializePhases() {
        this.phases.add("Trading");
        this.phases.add("(Re)Production");
        this.phases.add("Movement");
        this.phases.add("Building");
        this.phases.add("Wonder");
    }

    private Integer getCurrentPhase() {
        return this.currentPhase;
    }

    public String getCurrentPhaseName() {
        return this.phases.get(getCurrentPhase());
    }

    public void goToNextPhase() {
        currentPhase = (currentPhase + 1) % phases.size();
    }

}
