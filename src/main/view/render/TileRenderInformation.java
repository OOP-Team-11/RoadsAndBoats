package view.render;

import model.tile.Terrain;

public class TileRenderInformation {
    private Terrain terrain;
    private Boolean north;
    private Boolean northEast;
    private Boolean southEast;
    private Boolean south;
    private Boolean southWest;
    private Boolean northWest;

    public TileRenderInformation(Terrain terrain){
        this.terrain = terrain;
    }
    public Terrain getTerrain(){
        return this.terrain;
    }
    public void setNorth(Boolean north){
        this.north = north;
    }
    public void setNorthEast(Boolean northEast){
        this.northEast = northEast;
    }
    public void setSouthEast(Boolean southEast){
        this.southEast = southEast;
    }
    public void setSouth(Boolean south){
        this.south = south;
    }
    public void setSouthWest(Boolean southWest){
        this.southWest = southWest;
    }
    public void setNorthWest(Boolean northWest){
        this.northWest = northWest;
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

}
