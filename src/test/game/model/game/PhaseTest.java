package model.game;

import game.model.game.Phase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhaseTest {

    private Phase phase;

    @Before
    public void setUp() {
        this.phase = new Phase();
    }

    @Test
    public void cycling() {
        assertEquals("Trading", this.phase.getCurrentPhaseName());
        this.phase.goToNextPhase();
        assertEquals("(Re)Production", this.phase.getCurrentPhaseName());
        this.phase.goToNextPhase();
        assertEquals("Movement", this.phase.getCurrentPhaseName());
        this.phase.goToNextPhase();
        assertEquals("Building", this.phase.getCurrentPhaseName());
        this.phase.goToNextPhase();
        assertEquals("Wonder", this.phase.getCurrentPhaseName());
        this.phase.goToNextPhase();
        assertEquals("Trading", this.phase.getCurrentPhaseName());
    }
}
