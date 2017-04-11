package mapMaker.model.tile.riverConfiguration;

import mapMaker.model.tile.mmTerrain;
import mapMaker.utilities.Iterator.mmIterator;

import java.util.ArrayList;

public class mmRiverConfigurationCycler implements mmIterator<mmRiverConfiguration> {
    private ArrayList<mmRiverConfiguration> mmRiverConfigurations;

    private mmRiverConfiguration currentMmRiverConfiguration;
    private int index;

    public mmRiverConfigurationCycler(mmTerrain mmTerrain) {
        this.index = 0;
        this.mmRiverConfigurations = new ArrayList<>();
        setupRiverConfigurationsArrayList(mmTerrain);
    }

    public mmRiverConfiguration getCurrent() {
        return this.currentMmRiverConfiguration;
    }

    public mmRiverConfiguration getNext(){
        next();
        mmRiverConfiguration tempRiverConf = getCurrent();
        previous();
        return tempRiverConf;
    }

    public mmRiverConfiguration getPrevious(){
        previous();
        mmRiverConfiguration tempRiverConf = getCurrent();
        next();
        return tempRiverConf;
    }

    public void next() {
        this.index = ++this.index % this.mmRiverConfigurations.size();
        setCurrentRiverConfiguration();
    }

    public void previous() {
        this.index = (this.index - 1) < 0 ? this.mmRiverConfigurations.size() - this.index - 1: this.index - 1;
        setCurrentRiverConfiguration();
    }

    private void setCurrentRiverConfiguration() {
        this.currentMmRiverConfiguration = this.mmRiverConfigurations.get(this.index);
    }

    private void setupRiverConfigurationsArrayList(mmTerrain mmTerrain){
        if (mmTerrain == mmTerrain.SEA) {
            this.mmRiverConfigurations.add(mmRiverConfiguration.getNoRivers());
            this.index = 0; //Have to reset the index, as there are no other valid indices
        } else {
            this.mmRiverConfigurations.add(mmRiverConfiguration.getNoRivers());
            this.mmRiverConfigurations.add(mmRiverConfiguration.getSpringHead());
            this.mmRiverConfigurations.add(mmRiverConfiguration.getAdjacentFaces());
            this.mmRiverConfigurations.add(mmRiverConfiguration.getSkipAFace());
            this.mmRiverConfigurations.add(mmRiverConfiguration.getOppositeFaces());
            this.mmRiverConfigurations.add(mmRiverConfiguration.getEveryOtherFace());
            //Don't reset the index - keep the current one.
        }
        this.currentMmRiverConfiguration = this.mmRiverConfigurations.get(this.index);
    }

    public void updateTerrain(mmTerrain mmTerrain){
        setupRiverConfigurationsArrayList(mmTerrain);
    }
}
