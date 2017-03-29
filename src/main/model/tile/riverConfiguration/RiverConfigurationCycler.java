package model.tile.riverConfiguration;

import model.tile.Terrain;
import utilities.Iterator.Iterator;

import java.util.ArrayList;

public class RiverConfigurationCycler implements Iterator<RiverConfiguration> {

    private RiverConfiguration currentRiverConfiguration;
    private int index;
    private ArrayList<RiverConfiguration> riverConfigurations;
    public RiverConfigurationCycler(Terrain terrain) {
        this.riverConfigurations = new ArrayList<>();
        if (terrain == Terrain.SEA) {
            this.riverConfigurations.add(RiverConfiguration.getNoRivers());
        } else {
            this.riverConfigurations.add(RiverConfiguration.getNoRivers());
            this.riverConfigurations.add(RiverConfiguration.getSpringHead());
            this.riverConfigurations.add(RiverConfiguration.getAdjacentFaces());
            this.riverConfigurations.add(RiverConfiguration.getSkipAFace());
            this.riverConfigurations.add(RiverConfiguration.getOppositeFaces());
            this.riverConfigurations.add(RiverConfiguration.getEveryOtherFace());
        }

        this.index = 0;
        this.currentRiverConfiguration = this.riverConfigurations.get(this.index);
    }

    public RiverConfiguration getCurrent() {
        return this.currentRiverConfiguration;
    }

    public void next() {
        this.index = this.index++ % this.riverConfigurations.size();
        setCurrentRiverConfiguration();
    }

    public void previous() {
        this.index = (this.index - 1) < 0 ? this.riverConfigurations.size() - this.index - 1: this.index - 1;
        setCurrentRiverConfiguration();
    }

    private void setCurrentRiverConfiguration() {
        this.currentRiverConfiguration = this.riverConfigurations.get(this.index);
    }
}
