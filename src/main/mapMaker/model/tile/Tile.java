package mapMaker.model.tile;


import mapMaker.direction.Angle;
import mapMaker.direction.TileCompartmentDirection;
import mapMaker.direction.TileEdgeDirection;
import mapMaker.model.tile.riverConfiguration.RiverConfiguration;

import java.util.HashMap;
import java.util.Map;

public class Tile implements Cloneable{

    private Map<TileEdgeDirection, TileEdge> edges;
    private Map<TileCompartmentDirection, TileCompartment> compartments;
    private Terrain terrain;
    private RiverConfiguration riverConfiguration;

    public Tile(Terrain terrain, RiverConfiguration riverConfiguration) {
        edges = new HashMap<TileEdgeDirection, TileEdge>();
        compartments = new HashMap<TileCompartmentDirection, TileCompartment>();

        this.riverConfiguration = riverConfiguration;
        this.terrain = terrain;

        edges.put(TileEdgeDirection.getNorth(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorth(), riverConfiguration, terrain),
                        riverConfiguration.canConnectNorth()));

        edges.put(TileEdgeDirection.getNorthEast(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorthEast(), riverConfiguration, terrain),
                        riverConfiguration.canConnectNortheast()));

        edges.put(TileEdgeDirection.getNorthWest(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorthWest(), riverConfiguration, terrain),
                        riverConfiguration.canConnectNorthwest()));

        edges.put(TileEdgeDirection.getSouth(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouth(), riverConfiguration, terrain),
                        riverConfiguration.canConnectSouth()));

        edges.put(TileEdgeDirection.getSouthEast(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouthEast(), riverConfiguration, terrain),
                        riverConfiguration.canConnectSoutheast()));

        edges.put(TileEdgeDirection.getSouthWest(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouthWest(), riverConfiguration, terrain),
                        riverConfiguration.canConnectSouthwest()));

        this.rotateAccordingToRiverConfiguration();

        compartments.put(TileCompartmentDirection.getEast(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorthNorthEast(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorthEast(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorth(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorthNorthWest(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorthWest(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getWest(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouthWest(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouthSouthWest(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouth(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouthEast(), new TileCompartment(false));

        this.terrain = terrain;

    }

    private void rotateAccordingToRiverConfiguration() {
        int rotationAmount = this.riverConfiguration.getRotationAmount();
        while (rotationAmount > 0) {
            this.rotate(new Angle(60));
            rotationAmount--;
        }
    }

    public RiverConfiguration getRiverConfiguration() {
        return this.riverConfiguration;
    }

    private boolean canConnectRiver(TileEdgeDirection tileEdgeDirection, RiverConfiguration riverConfiguration, Terrain terrain) {

        return (tileEdgeDirection.equals(TileEdgeDirection.getNorth()) && riverConfiguration.canConnectNorth() ||
                tileEdgeDirection.equals(TileEdgeDirection.getNorthEast()) && riverConfiguration.canConnectNortheast() ||
                tileEdgeDirection.equals(TileEdgeDirection.getSouthEast()) && riverConfiguration.canConnectSoutheast() ||
                tileEdgeDirection.equals(TileEdgeDirection.getSouth()) && riverConfiguration.canConnectSouth() ||
                tileEdgeDirection.equals(TileEdgeDirection.getSouthWest()) && riverConfiguration.canConnectSouthwest() ||
                tileEdgeDirection.equals(TileEdgeDirection.getNorthWest()) && riverConfiguration.canConnectNorthwest()) ||
                terrain.canConnectRiver();
    }

    public Object makeClone(){
        try{
            Tile tileClone = (Tile) super.clone();
            tileClone.setRiverConfiguration((RiverConfiguration)(tileClone.getRiverConfiguration()));
            Map<TileEdgeDirection, TileEdge> directionsClone = new HashMap<TileEdgeDirection, TileEdge>();
            for (Map.Entry<TileEdgeDirection, TileEdge> entry : edges.entrySet()) {
                TileEdgeDirection  directionClone = (TileEdgeDirection)entry.getKey().clone();
                TileEdge edgeClone = (TileEdge)entry.getValue().clone();
                directionsClone.put(directionClone,edgeClone);
            }
            tileClone.edges = directionsClone;
            return tileClone;
        } catch(CloneNotSupportedException e){
            throw new InternalError(e.toString());
        }
    }

    // for deep cloning
    private void setRiverConfiguration(RiverConfiguration riverConfiguration){
        this.riverConfiguration = riverConfiguration;
    }

    // TileEdge
    public TileEdge getTileEdge(TileEdgeDirection edgeDirection) {
        return edges.get(edgeDirection);
    }

    public TileEdge setTileEdge(TileEdgeDirection edgeDirection, TileEdge edge) {
        return edges.put(edgeDirection, edge);
    }

    // TileCompartment
    public TileCompartment getTileCompartment(TileCompartmentDirection compartmentDirection) {
        return compartments.get(compartmentDirection);
    }

    public void setHasWater(TileCompartmentDirection direction, boolean bool) {
        getTileCompartment(direction).setHasWater(bool);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void rotate(Angle angle) {
        int rotationDegrees = angle.getDegrees();

        if(rotationDegrees < 60)
            return;
        for(int i = 0; i < (rotationDegrees/60); i++) {
            rotateEdges();
            rotateCompartments();
            rotateRiverConfigurations();
        }
    }
    private void rotateRiverConfigurations(){
        riverConfiguration.rotateRiverConfigurationOnce();
    }

    private void rotateEdges() {
        TileEdge northEdge = edges.get(TileEdgeDirection.getNorth());
        TileEdge northEastEdge = edges.get(TileEdgeDirection.getNorthEast());
        TileEdge northWestEdge = edges.get(TileEdgeDirection.getNorthWest());
        TileEdge southEdge = edges.get(TileEdgeDirection.getSouth());
        TileEdge southEastEdge = edges.get(TileEdgeDirection.getSouthEast());
        TileEdge southWestEdge = edges.get(TileEdgeDirection.getSouthWest());

        edges.put(TileEdgeDirection.getNorth(), northWestEdge);
        edges.put(TileEdgeDirection.getNorthEast(), northEdge);
        edges.put(TileEdgeDirection.getSouthEast(), northEastEdge);
        edges.put(TileEdgeDirection.getSouth(), southEastEdge);
        edges.put(TileEdgeDirection.getSouthWest(), southEdge);
        edges.put(TileEdgeDirection.getNorthWest(), southWestEdge);

    }

    private void rotateCompartments() {
        TileCompartment northComp = compartments.get(TileCompartmentDirection.getNorth());
        TileCompartment northNorthEastComp = compartments.get(TileCompartmentDirection.getNorthNorthEast());
        TileCompartment northEastComp = compartments.get(TileCompartmentDirection.getNorthEast());
        TileCompartment eastComp = compartments.get(TileCompartmentDirection.getEast());
        TileCompartment southEastComp = compartments.get(TileCompartmentDirection.getSouthEast());
        TileCompartment southSouthEastComp = compartments.get(TileCompartmentDirection.getSouthSouthEast());
        TileCompartment southComp = compartments.get(TileCompartmentDirection.getSouth());
        TileCompartment southSouthWestComp = compartments.get(TileCompartmentDirection.getSouthSouthWest());
        TileCompartment southWestComp = compartments.get(TileCompartmentDirection.getSouthWest());
        TileCompartment westComp = compartments.get(TileCompartmentDirection.getWest());
        TileCompartment northWestComp = compartments.get(TileCompartmentDirection.getNorthWest());
        TileCompartment northNorthWestComp = compartments.get(TileCompartmentDirection.getNorthNorthWest());

        compartments.put(TileCompartmentDirection.getEast(), northNorthEastComp);
        compartments.put(TileCompartmentDirection.getNorthNorthEast(), northNorthWestComp);
        compartments.put(TileCompartmentDirection.getNorthEast(), northComp);
        compartments.put(TileCompartmentDirection.getNorth(), northWestComp);
        compartments.put(TileCompartmentDirection.getNorthNorthWest(), westComp);
        compartments.put(TileCompartmentDirection.getNorthWest(), southWestComp);
        compartments.put(TileCompartmentDirection.getWest(), southSouthWestComp);
        compartments.put(TileCompartmentDirection.getSouthWest(), southComp);
        compartments.put(TileCompartmentDirection.getSouthSouthWest(), southSouthEastComp);
        compartments.put(TileCompartmentDirection.getSouth(), southEastComp);
        compartments.put(TileCompartmentDirection.getSouthEast(), northEastComp);
        compartments.put(TileCompartmentDirection.getSouthSouthEast(), eastComp);

    }

    public void resetTileEdge(TileEdgeDirection dir)
    {
        edges.put(TileEdgeDirection.getSouthWest(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouthWest(), riverConfiguration, terrain),
                        riverConfiguration.canConnect(dir)));
    }
}
