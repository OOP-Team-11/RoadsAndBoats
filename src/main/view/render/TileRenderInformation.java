package view.render;

import direction.TileEdgeDirection;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.TileEdge;

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

        TileEdge northEdge = t.getTileEdge(TileEdgeDirection.getNorth());
        this.north = tileEdgeCanConnectRiver(northEdge);

        TileEdge northEast = t.getTileEdge(TileEdgeDirection.getNorthEast());
        this.northEast = tileEdgeCanConnectRiver(northEast);

        TileEdge southEast = t.getTileEdge(TileEdgeDirection.getSouthEast());
        this.southEast = tileEdgeCanConnectRiver(southEast);

        TileEdge south = t.getTileEdge(TileEdgeDirection.getSouth());
        this.south = tileEdgeCanConnectRiver(south);

        TileEdge southWest = t.getTileEdge(TileEdgeDirection.getSouthWest());
        this.southWest = tileEdgeCanConnectRiver(southWest);

        TileEdge northWest = t.getTileEdge(TileEdgeDirection.getNorthWest());
        this.northWest = tileEdgeCanConnectRiver(northWest);
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
