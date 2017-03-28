package model.tile;

import direction.Angle;
import direction.TileCompartmentDirection;
import direction.TileEdgeDirection;

import java.util.HashMap;
import java.util.Map;

public class Tile {

    private Map<TileEdgeDirection, TileEdge> edges;
    private Map<TileCompartmentDirection, TileCompartment> compartments;
    private Terrain terrain;

    public Tile(Terrain terrain) {
        edges = new HashMap<TileEdgeDirection, TileEdge>();
        compartments = new HashMap<TileCompartmentDirection, TileCompartment>();

        edges.put(TileEdgeDirection.getNorth(), new TileEdge(false));
        edges.put(TileEdgeDirection.getNorthEast(), new TileEdge(false));
        edges.put(TileEdgeDirection.getNorthWest(), new TileEdge(false));
        edges.put(TileEdgeDirection.getSouth(), new TileEdge(false));
        edges.put(TileEdgeDirection.getSouthEast(), new TileEdge(false));
        edges.put(TileEdgeDirection.getSouthWest(), new TileEdge(false));

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
        compartments.put(TileCompartmentDirection.getSouthSouthEast(), new TileCompartment(false));

        this.terrain = terrain;
    }

    // TileEdge
    public TileEdge getTileEdge(TileEdgeDirection edgeDirection) {
        return edges.get(edgeDirection);
    }

    public void setCanConnectWater(TileEdgeDirection direction, boolean bool) {
        getTileEdge(direction).setCanConnectRiver(bool);
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
        }
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
}
