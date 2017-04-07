package mapMaker.model.tile;

public class TileCompartment {
    private boolean hasWater;
    public TileCompartment(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean hasWater() {
        return this.hasWater;
    }
    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

}
