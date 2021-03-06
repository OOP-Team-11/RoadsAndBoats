package game.model.tile;

import game.model.direction.*;
import game.model.movement.Bridge;
import game.model.movement.River;
import game.model.movement.Road;
import game.model.resources.ResourceType;
import game.model.structures.Structure;
import game.utilities.observable.TileResourceInfoObservable;
import game.utilities.observer.TileCompartmentResourceAddedObserver;
import game.utilities.observer.TileResourceInfoObserver;
import game.view.render.ResourceManagerRenderInfo;
import game.view.render.TileResourceRenderInfo;

import java.util.*;

public class Tile implements TileResourceInfoObservable, TileCompartmentResourceAddedObserver
{
    private Map<TileEdgeDirection, TileEdge> edges;
    private Map<TileCompartmentDirection, TileCompartment> compartments;
    private Terrain terrain;
    private Structure structure;
    private List<TileResourceInfoObserver> tileResourceInfoObservers;

    public Tile(Terrain terrain, RiverConfiguration riverConfiguration)
    {
        edges = new HashMap<>();
        compartments = new HashMap<>();
        tileResourceInfoObservers = new ArrayList<>();

        this.terrain = terrain;
        setupCompartments(riverConfiguration);
        setupEdges(riverConfiguration);

        this.rotateAccordingToRiverConfiguration(riverConfiguration);
    }

    private void setupEdges(RiverConfiguration riverConfiguration)
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

    private void setupCompartments(RiverConfiguration riverConfiguration)
    {
        List<TileCompartmentDirection> compartmentDirections = TileCompartmentDirection.getAllDirections();

        TileCompartment river = new TileCompartment();
        river.attach(this);
        TileCompartment compartment = null;

        for (TileCompartmentDirection tileCompartmentDirection : compartmentDirections)
        {
            boolean hasRiver = hasRiver(tileCompartmentDirection, riverConfiguration);

            if (hasRiver)
            {
                compartment = river;

                if (!isCorner(tileCompartmentDirection))
                {
                    river.add(tileCompartmentDirection, new River(tileCompartmentDirection));
                }
            } else if (compartment == null || compartment.hasWater())
            {
                compartment = new TileCompartment();
                compartment.attach(this);
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

    private void rotateAccordingToRiverConfiguration(RiverConfiguration riverConfiguration)
    {
        int rotationAmount = riverConfiguration.getRotationAmount();
        while (rotationAmount > 0)
        {
            this.rotate(new Angle(60));
            rotationAmount--;
        }
    }

    public RiverConfiguration getRiverConfiguration()
    {
        Map<TileEdgeDirection, Boolean> rivers= new HashMap<>();
        for(TileEdgeDirection ted: TileEdgeDirection.getAllDirections())
        {
            TileCompartmentDirection tcd =new TileCompartmentDirection(ted.getAngle());
            boolean hasRiver =getTileCompartment(tcd).getRiver(tcd) != null;
            rivers.put(ted, hasRiver);
        }

        return new RiverConfiguration(rivers);
    }

    private boolean canConnectRiver(TileEdgeDirection tileEdgeDirection)
    {
        if(terrain.canConnectRiver())
        {
            return true;
        }

        TileCompartmentDirection tcd =new TileCompartmentDirection(tileEdgeDirection.getAngle());
        TileCompartment tc =getTileCompartment(tcd);

        return tc.getRiver(tcd) != null;
    }

    private boolean hasRiver(TileCompartmentDirection tileCompartmentDirection, RiverConfiguration riverConfiguration)
    {
        if(terrain.canConnectRiver())
        {
            return true;
        }

        if(isCorner(tileCompartmentDirection))
        {
            return false;
        }

        return riverConfiguration.canConnect(new TileEdgeDirection(tileCompartmentDirection.getAngle()));
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
        }
    }

    private void rotateEdges()
    {
        HashMap<TileEdgeDirection, TileEdge> edges = new HashMap<>();

        for(Map.Entry<TileEdgeDirection, TileEdge> entry: this.edges.entrySet())
        {
            TileEdgeDirection dir = new TileEdgeDirection(new Angle(entry.getKey().getAngle().getDegrees()-60));
            edges.put(dir, entry.getValue());
        }

        this.edges=edges;
    }

    private void rotateCompartments()
    {
        HashMap<TileCompartmentDirection, TileCompartment> compartments = new HashMap<>();

        for(Map.Entry<TileCompartmentDirection, TileCompartment> entry: this.compartments.entrySet())
        {
            TileCompartmentDirection dir = new TileCompartmentDirection(new Angle(entry.getKey().getAngle().getDegrees()-60));
            compartments.put(dir, entry.getValue());
        }

        this.compartments=compartments;

        HashSet<TileCompartment> tileCompartments = new HashSet<>();
        tileCompartments.addAll(this.compartments.values());

        for(TileCompartment tc: tileCompartments)
        {
            tc.rotate();
        }
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

    public void irrigate() {
        if (this.terrain.isIrrigatable()) {
            this.setTerrain(Terrain.PASTURE);
        }
    }

    public static boolean isCorner(TileCompartmentDirection dir)
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

    private Set<TileCompartment> getUniqueTileCompartments() {
        Set<TileCompartment> tileCompartments = new HashSet<>();
        for (TileCompartment tileCompartment : compartments.values()) {
            tileCompartments.add(tileCompartment);
        }
        return tileCompartments;
    }

    private Set<TileCompartmentDirection> getKeysForUniqueCompartments() {
        Set<TileCompartment> uniqueTileCompartments = getUniqueTileCompartments();
        Set<TileCompartmentDirection> uniqueTileCompartmentDirections = new HashSet<>();
        for (TileCompartment uniqueTileCompartment : uniqueTileCompartments) {
            Iterator it = compartments.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                TileCompartment tileCompartment = (TileCompartment) pair.getValue();
                if (tileCompartment.equals(uniqueTileCompartment)) {
                    TileCompartmentDirection tcd = (TileCompartmentDirection) pair.getKey();
                    uniqueTileCompartmentDirections.add(tcd);
                    break;
                }
            }
        }
        return uniqueTileCompartmentDirections;
    }

    private Map<TileCompartmentDirection, TileCompartment> getUniqueTileCompartmentsMap() {
        Map<TileCompartmentDirection, TileCompartment> tileCompartmentMap = new HashMap<>();
        for (TileCompartmentDirection tileCompartmentDirection : getKeysForUniqueCompartments()) {
            tileCompartmentMap.put(tileCompartmentDirection, compartments.get(tileCompartmentDirection));
        }
        return tileCompartmentMap;
    }

    private void notifyResourceRenderInfoObservers() {
        Map<TileCompartmentDirection, ResourceManagerRenderInfo> resourceMap = new HashMap<>();
        Iterator it = getUniqueTileCompartmentsMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileCompartmentDirection tcd = (TileCompartmentDirection) pair.getKey();
            ResourceManagerRenderInfo resourceManagerRenderInfo = compartments.get(tcd).getResourceManager().getRenderInfo();
            resourceMap.put(tcd, resourceManagerRenderInfo);
        }
        TileResourceRenderInfo tileResourceRenderInfo = new TileResourceRenderInfo(this, resourceMap);

        for (TileResourceInfoObserver observer : this.tileResourceInfoObservers) {
            observer.onTileResourcesUpdated(tileResourceRenderInfo);
        }
    }

    @Override
    public void onTileCompartmentResourcesChanged() {
        this.notifyResourceRenderInfoObservers();
    }

    @Override
    public void attach(TileResourceInfoObserver observer) {
        this.tileResourceInfoObservers.add(observer);
    }

    @Override
    public void detach(TileResourceInfoObserver observer) {
        this.tileResourceInfoObservers.remove(observer);
    }

    public Set<TileCompartmentDirection> getTileCompartmentDirections(TileCompartment comp)
    {
        Set<TileCompartmentDirection> dirs=new HashSet<TileCompartmentDirection>();

        for(TileCompartmentDirection tcd: TileCompartmentDirection.getAllDirections())
        {
            if(compartments.get(tcd).equals(comp))
            {
                dirs.add(tcd);
            }
        }

        return dirs;
    }

    public RoadConfiguration getRoadConfiguration()
    {
        Map<TileEdgeDirection, Boolean> roads= new HashMap<>();
        for(TileEdgeDirection ted: TileEdgeDirection.getAllDirections())
        {
            TileCompartmentDirection tcd =new TileCompartmentDirection(ted.getAngle());
            boolean hasRoad =getTileCompartment(tcd).getRoad(tcd) != null;
            roads.put(ted, hasRoad);
        }

        return new RoadConfiguration(roads);
    }

    public boolean canBuildRoad(Road road)
    {
        if(hasRiver(road.getCompartmentDirection(), getRiverConfiguration()))
        {
            return false;
        }

        return getTileCompartment(road.getCompartmentDirection()).canBuildRoad(road);
    }

    public void buildRoad(Road road)
    {
        getTileCompartment(road.getCompartmentDirection()).buildRoad(road);
    }

    public boolean canBuildBridge(Bridge bridge)
    {
        return !compartments.get(bridge.tcd1).equals(compartments.get(bridge.tcd2));
    }

    public void buildBridge(Bridge bridge)
    {
        TileCompartment comp1=compartments.get(bridge.tcd1);
        TileCompartment comp2=compartments.get(bridge.tcd2);

        for(Map.Entry<TileCompartmentDirection, River> entry: comp1.getRivers().entrySet())
        {
            comp2.add(entry.getKey(), entry.getValue());
            entry.getValue().setDestination(comp1);
        }

        for(Map.Entry<TileCompartmentDirection, Road> entry: comp1.getRoads().entrySet())
        {
            comp2.add(entry.getKey(), entry.getValue());
            entry.getValue().setDestination(comp1);
        }

        for(Map.Entry<ResourceType, Integer> entry: comp1.getResourceManager().getResourceTypeIntegerMap().entrySet())
        {
            comp2.getResourceManager().addResource(entry.getKey(), entry.getValue());
        }

        for(TileCompartmentDirection dir: getTileCompartmentDirections(comp1))
        {
            compartments.replace(dir, comp2);
        }
    }
}
