package game.model.tile;

import game.model.direction.Angle;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.movement.River;
import game.model.structures.Structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tile
{

    private Map<TileEdgeDirection, TileEdge> edges;
    private Map<TileCompartmentDirection, TileCompartment> compartments;
    private Terrain terrain;
    private RiverConfiguration riverConfiguration;
    private Structure structure;

    public Tile(Terrain terrain, RiverConfiguration riverConfiguration)
    {
        edges = new HashMap<>();
        compartments = new HashMap<>();

        this.riverConfiguration = riverConfiguration;
        this.terrain = terrain;

        setupEdges();
        setupCompartments();

        this.rotateAccordingToRiverConfiguration();
    }

    private void setupEdges()
    {
        edges.put(TileEdgeDirection.getNorth(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorth()),
                        riverConfiguration.canConnectNorth()));

        edges.put(TileEdgeDirection.getNorthEast(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorthEast()),
                        riverConfiguration.canConnectNortheast()));

        edges.put(TileEdgeDirection.getNorthWest(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorthWest()),
                        riverConfiguration.canConnectNorthwest()));

        edges.put(TileEdgeDirection.getSouth(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouth()),
                        riverConfiguration.canConnectSouth()));

        edges.put(TileEdgeDirection.getSouthEast(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouthEast()),
                        riverConfiguration.canConnectSoutheast()));

        edges.put(TileEdgeDirection.getSouthWest(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouthWest()),
                        riverConfiguration.canConnectSouthwest()));
    }

    private void setupCompartments()
    {
        List<TileCompartmentDirection> compartmentDirections = TileCompartmentDirection.getAllDirections();

        TileCompartment river = new TileCompartment();
        TileCompartment compartment = null;

        for (TileCompartmentDirection tileCompartmentDirection : compartmentDirections)
        {
            boolean hasRiver = hasRiver(tileCompartmentDirection);

            if (hasRiver)
            {
                compartment = river;

                if (!isCorner(tileCompartmentDirection))
                {
                    river.add(tileCompartmentDirection, new River());
                }
            } else if (compartment == null || compartment.hasWater())
            {
                compartment = new TileCompartment();
            }

            compartments.put(tileCompartmentDirection, compartment);
        }

        //Combine first and last in list if necessary
        for (TileCompartmentDirection tileCompartmentDirection : compartmentDirections)
        {
            if (compartments.get(tileCompartmentDirection).hasWater() == compartment.hasWater())
            {
                compartments.put(tileCompartmentDirection, compartment);
            } else
            {
                break;
            }
        }
    }

    private void rotateAccordingToRiverConfiguration()
    {
        int rotationAmount = this.riverConfiguration.getRotationAmount();
        while (rotationAmount > 0)
        {
            this.rotate(new Angle(60));
            rotationAmount--;
        }
    }

    public RiverConfiguration getRiverConfiguration()
    {
        return this.riverConfiguration;
    }

    private boolean canConnectRiver(TileEdgeDirection tileEdgeDirection)
    {
        return (tileEdgeDirection.equals(tileEdgeDirection.getNorth()) && riverConfiguration.canConnectNorth() ||
                tileEdgeDirection.equals(tileEdgeDirection.getNorthEast()) && riverConfiguration.canConnectNortheast() ||
                tileEdgeDirection.equals(tileEdgeDirection.getSouthEast()) && riverConfiguration.canConnectSoutheast() ||
                tileEdgeDirection.equals(tileEdgeDirection.getSouth()) && riverConfiguration.canConnectSouth() ||
                tileEdgeDirection.equals(tileEdgeDirection.getSouthWest()) && riverConfiguration.canConnectSouthwest() ||
                tileEdgeDirection.equals(tileEdgeDirection.getNorthWest()) && riverConfiguration.canConnectNorthwest()) ||
                terrain.canConnectRiver();
    }

    private boolean hasRiver(TileCompartmentDirection tileCompartmentDirection)
    {

        return (tileCompartmentDirection.equals(tileCompartmentDirection.getNorth()) && riverConfiguration.canConnectNorth() ||
                tileCompartmentDirection.equals(tileCompartmentDirection.getNorthEast()) && riverConfiguration.canConnectNortheast() ||
                tileCompartmentDirection.equals(tileCompartmentDirection.getSouthEast()) && riverConfiguration.canConnectSoutheast() ||
                tileCompartmentDirection.equals(tileCompartmentDirection.getSouth()) && riverConfiguration.canConnectSouth() ||
                tileCompartmentDirection.equals(tileCompartmentDirection.getSouthWest()) && riverConfiguration.canConnectSouthwest() ||
                tileCompartmentDirection.equals(tileCompartmentDirection.getNorthWest()) && riverConfiguration.canConnectNorthwest()) ||
                terrain.canConnectRiver();
    }

    // TileEdge
    public TileEdge getTileEdge(TileEdgeDirection edgeDirection)
    {
        return edges.get(edgeDirection);
    }

    public TileEdge setTileEdge(TileEdgeDirection edgeDirection, TileEdge edge)
    {
        return edges.put(edgeDirection, edge);
    }

    // TileCompartment
    public TileCompartment getTileCompartment(TileCompartmentDirection compartmentDirection)
    {
        return compartments.get(compartmentDirection);
    }

    public Terrain getTerrain()
    {
        return this.terrain;
    }

    public void setTerrain(Terrain terrain)
    {
        this.terrain = terrain;
    }

    public void rotate(Angle Angle)
    {
        int rotationDegrees = Angle.getDegrees();

        if (rotationDegrees < 60)
            return;
        for (int i = 0; i < (rotationDegrees / 60); i++)
        {
            rotateEdges();
            rotateCompartments();
            rotateRiverConfigurations();
        }
    }

    private void rotateRiverConfigurations()
    {
        riverConfiguration.rotateRiverConfigurationOnce();
    }

    private void rotateEdges()
    {
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

    private void rotateCompartments()
    {
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

        northComp.rotate();
        northNorthEastComp.rotate();
        northEastComp.rotate();
        eastComp.rotate();
        southEastComp.rotate();
        southSouthEastComp.rotate();
        southComp.rotate();
        southSouthWestComp.rotate();
        southWestComp.rotate();
        westComp.rotate();
        northWestComp.rotate();
        northNorthWestComp.rotate();

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

    public boolean addStructure(Structure structure)
    {
        if (this.structure != null) return false;

        this.structure = structure;
        return true;
    }

    public Structure getStructure()
    {
        return this.structure;
    }


    public void changeToPasture() {
        if (this.terrain.canChangeToPasture()) {
            this.setTerrain(Terrain.PASTURE);
        }
    }


    private static boolean isCorner(TileCompartmentDirection dir)
    {
        return dir.equals(TileCompartmentDirection.getNorthNorthEast())
                || dir.equals(TileCompartmentDirection.getSouthSouthEast())
                || dir.equals(TileCompartmentDirection.getNorthNorthWest())
                || dir.equals(TileCompartmentDirection.getSouthSouthWest())
                || dir.equals(TileCompartmentDirection.getEast())
                || dir.equals(TileCompartmentDirection.getWest());
    }

    public void removeUnattachedRivers()
    {
        for(TileCompartment comp: compartments.values())
        {
            comp.removeUnattachedRivers();
        }
    }
}
