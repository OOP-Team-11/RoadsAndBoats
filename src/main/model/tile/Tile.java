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


}
