package tile;

import direction.TileCompartmentDirection;
import direction.TileEdgeDirection;

import java.util.HashMap;
import java.util.Map;

public class Tile {

    public static final int MAX_EDGES = 6;
    public static final int MAX_COMPARTMENTS = 12;

    private Map<TileEdgeDirection, TileEdge> edges;
    private Map<TileCompartmentDirection, TileCompartment> compartments;
    private Terrain terrain;

    public Tile(Terrain terrain) {
        edges = new HashMap<TileEdgeDirection, TileEdge>();
        compartments = new HashMap<TileCompartmentDirection, TileCompartment>();
        this.terrain = terrain;
    }

    public void putTileEdge(TileEdgeDirection edgeDirection, TileEdge edge) throws TileEdgeLimitException {
        if (edges.size() == MAX_EDGES) {
            throw new TileEdgeLimitException("Tile can only have 6 edges.");
        }
        edges.put(edgeDirection, edge);
    }

    public void putTileCompartment(TileCompartmentDirection compartmentDirection, TileCompartment compartment) throws TileCompartmentLimitException{
        if (compartments.size() == MAX_COMPARTMENTS) {
            throw new TileCompartmentLimitException("Tile can only have 12 compartments.");
        }
        compartments.put(compartmentDirection, compartment);
    }

    public TileEdge getTileEdge(TileEdgeDirection edgeDirection) {
        return edges.get(edgeDirection);
    }

    public TileCompartment getTileCompartment(TileCompartmentDirection compartmentDirection) {
        return compartments.get(compartmentDirection);
    }

    public Map<TileEdgeDirection, TileEdge> getAllEdges() {
        return edges;
    }

    public Map<TileCompartmentDirection, TileCompartment> getAllCompartments() {
        return compartments;
    }

    public Terrain getTerrain() {
        return terrain;
    }

}
