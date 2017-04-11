package mapMaker.model.tile;

public class mmTileCompartment {
    private boolean hasWater;
    public mmTileCompartment(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean hasWater() {
        return this.hasWater;
    }
    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

}
