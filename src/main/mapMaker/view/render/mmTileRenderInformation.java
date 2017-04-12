package mapMaker.view.render;

import mapMaker.model.tile.mmTerrain;
import mapMaker.model.tile.mmTile;
import game.model.TileEdge;

public class mmTileRenderInformation {
    private mmTerrain mmTerrain;
    private Boolean north;
    private Boolean northEast;
    private Boolean southEast;
    private Boolean south;
    private Boolean southWest;
    private Boolean northWest;

    public mmTileRenderInformation(mmTile t){
        this.mmTerrain = t.getMmTerrain();

        this.north = t.getMmRiverConfiguration().canConnectNorth();

        this.northEast = t.getMmRiverConfiguration().canConnectNortheast();

        this.southEast = t.getMmRiverConfiguration().canConnectSoutheast();

        this.south = t.getMmRiverConfiguration().canConnectSouth();

        this.southWest = t.getMmRiverConfiguration().canConnectSouthwest();

        this.northWest = t.getMmRiverConfiguration().canConnectNorthwest();
    }

    public mmTerrain getMmTerrain(){
        return this.mmTerrain;
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

    private boolean tileEdgeCanConnectRiver(TileEdge mmTileEdge) {
        return mmTileEdge.canConnectRiver();
    }

}
