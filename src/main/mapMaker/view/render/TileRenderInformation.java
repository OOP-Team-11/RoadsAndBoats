package mapMaker.view.render;

import mapMaker.model.tile.Terrain;
import mapMaker.model.tile.Tile;
import mapMaker.model.tile.TileEdge;

public class TileRenderInformation {
    private Terrain terrain;
    private Boolean north;
    private Boolean northEast;
    private Boolean southEast;
    private Boolean south;
    private Boolean southWest;
    private Boolean northWest;

    public TileRenderInformation(Tile t){
        this.terrain = t.getTerrain();

        this.north = t.getRiverConfiguration().canConnectNorth();

        this.northEast = t.getRiverConfiguration().canConnectNortheast();

        this.southEast = t.getRiverConfiguration().canConnectSoutheast();

        this.south = t.getRiverConfiguration().canConnectSouth();

        this.southWest = t.getRiverConfiguration().canConnectSouthwest();

        this.northWest = t.getRiverConfiguration().canConnectNorthwest();
    }

    public Terrain getTerrain(){
        return this.terrain;
    }
    public Boolean getNorth(){
        return this.north;
    }
    public Boolean getNorthEast(){
        return this.northEast;
    }
    public Boolean getSouthEast(){
        return this.southEast;
    }
    public Boolean getSouth(){
        return this.south;
    }
    public Boolean getSouthWest(){
        return this.southWest;
    }
    public Boolean getNorthWest(){
        return this.northWest;
    }

    private boolean tileEdgeCanConnectRiver(TileEdge tileEdge) {
        return tileEdge.canConnectRiver();
    }

}
