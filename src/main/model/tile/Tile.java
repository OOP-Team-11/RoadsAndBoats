package model.tile;

import direction.Angle;
import direction.TileCompartmentDirection;
import direction.TileEdgeDirection;

import java.util.HashMap;
import java.util.Map;

public class Tile {

    private Map<Angle, TileEdge> edges;
    private Map<Angle, TileCompartment> compartments;
    private Terrain terrain;

    public Tile(Terrain terrain) {
        edges = new HashMap<Angle, TileEdge>();
        compartments = new HashMap<Angle, TileCompartment>();

        edges.put(TileEdgeDirection.getNorth().getAngle(), new TileEdge(false));
        edges.put(TileEdgeDirection.getNorthEast().getAngle(), new TileEdge(false));
        edges.put(TileEdgeDirection.getNorthWest().getAngle(), new TileEdge(false));
        edges.put(TileEdgeDirection.getSouth().getAngle(), new TileEdge(false));
        edges.put(TileEdgeDirection.getSouthEast().getAngle(), new TileEdge(false));
        edges.put(TileEdgeDirection.getSouthWest().getAngle(), new TileEdge(false));

        compartments.put(TileCompartmentDirection.getEast().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorthNorthEast().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorthEast().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorth().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorthNorthWest().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getNorthWest().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getWest().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouthWest().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouthSouthWest().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouth().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouthEast().getAngle(), new TileCompartment(false));
        compartments.put(TileCompartmentDirection.getSouthSouthEast().getAngle(), new TileCompartment(false));

        this.terrain = terrain;
    }

    // TileEdge
    public TileEdge getTileEdge(TileEdgeDirection edgeDirection) {
        return edges.get(edgeDirection.getAngle());
    }

    public void toggleCanConnectWater(TileEdgeDirection direction, boolean bool) {
        getTileEdge(direction).setCanConnectRiver(bool);
    }

    public Map<Angle, TileEdge> getAllEdges() {
        return edges;
    }

    // TileCompartment
    public TileCompartment getTileCompartment(TileCompartmentDirection compartmentDirection) {
        return compartments.get(compartmentDirection.getAngle());
    }

    public void toggleHasWater(TileCompartmentDirection direction, boolean bool) {
        getTileCompartment(direction).setHasWater(bool);
    }

    public Map<Angle, TileCompartment> getAllCompartments() {
        return compartments;
    }

    public Terrain getTerrain() {
        return terrain;
    }


}
