package game.model.tile;

import game.model.direction.Angle;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.Structure;

import java.util.HashMap;
import java.util.Map;

public class Tile {

    private Map<TileEdgeDirection, TileEdge> edges;
    private Map<TileCompartmentDirection, TileCompartment> compartments;
    private Terrain terrain;
    private RiverConfiguration RiverConfiguration;
    private Structure structure;
    private ResourceManager resourceManager;

    public Tile(Terrain Terrain, RiverConfiguration RiverConfiguration) {
        edges = new HashMap<>();
        compartments = new HashMap<>();
        resourceManager = new ResourceManager();

        this.RiverConfiguration = RiverConfiguration;
        this.terrain = Terrain;

        edges.put(TileEdgeDirection.getNorth(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorth(), RiverConfiguration, Terrain),
                        RiverConfiguration.canConnectNorth()));

        edges.put(TileEdgeDirection.getNorthEast(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorthEast(), RiverConfiguration, Terrain),
                        RiverConfiguration.canConnectNortheast()));

        edges.put(TileEdgeDirection.getNorthWest(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getNorthWest(), RiverConfiguration, Terrain),
                        RiverConfiguration.canConnectNorthwest()));

        edges.put(TileEdgeDirection.getSouth(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouth(), RiverConfiguration, Terrain),
                        RiverConfiguration.canConnectSouth()));

        edges.put(TileEdgeDirection.getSouthEast(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouthEast(), RiverConfiguration, Terrain),
                        RiverConfiguration.canConnectSoutheast()));

        edges.put(TileEdgeDirection.getSouthWest(),
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouthWest(), RiverConfiguration, Terrain),
                        RiverConfiguration.canConnectSouthwest()));

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

    }

    private void rotateAccordingToRiverConfiguration() {
        int rotationAmount = this.RiverConfiguration.getRotationAmount();
        while (rotationAmount > 0) {
            this.rotate(new Angle(60));
            rotationAmount--;
        }
    }

    public RiverConfiguration getRiverConfiguration() {
        return this.RiverConfiguration;
    }

    private boolean canConnectRiver(TileEdgeDirection TileEdgeDirection, RiverConfiguration RiverConfiguration, Terrain Terrain) {

        return (TileEdgeDirection.equals(TileEdgeDirection.getNorth()) && RiverConfiguration.canConnectNorth() ||
                TileEdgeDirection.equals(TileEdgeDirection.getNorthEast()) && RiverConfiguration.canConnectNortheast() ||
                TileEdgeDirection.equals(TileEdgeDirection.getSouthEast()) && RiverConfiguration.canConnectSoutheast() ||
                TileEdgeDirection.equals(TileEdgeDirection.getSouth()) && RiverConfiguration.canConnectSouth() ||
                TileEdgeDirection.equals(TileEdgeDirection.getSouthWest()) && RiverConfiguration.canConnectSouthwest() ||
                TileEdgeDirection.equals(TileEdgeDirection.getNorthWest()) && RiverConfiguration.canConnectNorthwest()) ||
                Terrain.canConnectRiver();
    }

    // for deep cloning
    private void setRiverConfiguration(RiverConfiguration RiverConfiguration){
        this.RiverConfiguration = RiverConfiguration;
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
        return this.terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void rotate(Angle Angle) {
        int rotationDegrees = Angle.getDegrees();

        if(rotationDegrees < 60)
            return;
        for(int i = 0; i < (rotationDegrees/60); i++) {
            rotateEdges();
            rotateCompartments();
            rotateRiverConfigurations();
        }
    }
    private void rotateRiverConfigurations(){
        RiverConfiguration.rotateRiverConfigurationOnce();
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
                new TileEdge(canConnectRiver(TileEdgeDirection.getSouthWest(), RiverConfiguration, this.terrain),
                        RiverConfiguration.canConnect(dir)));
    }

    public boolean addStructure(Structure structure) {
        if (this.structure != null) return false;

        this.structure = structure;
        return true;
    }

    public Structure getStructure() {
        return this.structure;
    }

    public ResourceManager getResourceManager() {
        return this.resourceManager;
    }

    public void addResource(ResourceType resourceType, Integer amount) {
        this.resourceManager.addResource(resourceType, amount);
    }

    public boolean removeResource(ResourceType resourceType, Integer amount) {
        return this.resourceManager.removeResource(resourceType, amount);
    }
}
