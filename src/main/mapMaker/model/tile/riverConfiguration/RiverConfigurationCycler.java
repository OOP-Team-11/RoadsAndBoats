package mapMaker.model.tile.riverConfiguration;

import mapMaker.model.tile.Terrain;
import mapMaker.utilities.Iterator.Iterator;

import java.util.ArrayList;

public class RiverConfigurationCycler implements Iterator<RiverConfiguration> {
    private ArrayList<RiverConfiguration> riverConfigurations;

    private RiverConfiguration currentRiverConfiguration;
    private int index;

    public RiverConfigurationCycler(Terrain terrain) {
        this.index = 0;
        this.riverConfigurations = new ArrayList<>();
        setupRiverConfigurationsArrayList(terrain);
    }

    public RiverConfiguration getCurrent() {
        return this.currentRiverConfiguration;
    }

    public RiverConfiguration getNext(){
        next();
        RiverConfiguration tempRiverConf = getCurrent();
        previous();
        return tempRiverConf;
    }

    public RiverConfiguration getPrevious(){
        previous();
        RiverConfiguration tempRiverConf = getCurrent();
        next();
        return tempRiverConf;
    }

    public void next() {
        this.index = ++this.index % this.riverConfigurations.size();
        setCurrentRiverConfiguration();
    }

    public void previous() {
        this.index = (this.index - 1) < 0 ? this.riverConfigurations.size() - this.index - 1: this.index - 1;
        setCurrentRiverConfiguration();
    }

    private void setCurrentRiverConfiguration() {
        this.currentRiverConfiguration = this.riverConfigurations.get(this.index);
    }

    private void setupRiverConfigurationsArrayList(Terrain terrain){
        if (terrain == Terrain.SEA) {
            this.riverConfigurations.add(RiverConfiguration.getNoRivers());
            this.index = 0; //Have to reset the index, as there are no other valid indices
        } else {
            this.riverConfigurations.add(RiverConfiguration.getNoRivers());
            this.riverConfigurations.add(RiverConfiguration.getSpringHead());
            this.riverConfigurations.add(RiverConfiguration.getAdjacentFaces());
            this.riverConfigurations.add(RiverConfiguration.getSkipAFace());
            this.riverConfigurations.add(RiverConfiguration.getOppositeFaces());
            this.riverConfigurations.add(RiverConfiguration.getEveryOtherFace());
            //Don't reset the index - keep the current one.
        }
        this.currentRiverConfiguration = this.riverConfigurations.get(this.index);
    }

    public void updateTerrain(Terrain terrain){
        setupRiverConfigurationsArrayList(terrain);
    }
}
