package game.model.tile;

import game.model.direction.Angle;
import game.model.direction.TileCompartmentDirection;
import game.model.managers.ResourceManager;
import game.model.movement.River;
import game.model.movement.Road;
import game.model.resources.ResourceType;
import game.model.transport.Transport;
import game.model.visitors.TileCompartmentVisitor;
import game.utilities.observable.TileCompartmentResourceAddedObservable;
import game.utilities.observer.TileCompartmentResourceAddedObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TileCompartment implements TileCompartmentResourceAddedObservable, TileCompartmentVisitor
{
    private ResourceManager resourceManager;
    private Collection<Transport> transports;
    private Map<TileCompartmentDirection, Road> roads;
    private Map<TileCompartmentDirection, River> rivers;
    private List<TileCompartmentResourceAddedObserver> tileCompartmentResourceAddedObservers;

    public TileCompartment()
    {
        resourceManager = new ResourceManager();
        transports = new HashSet<>();
        roads = new HashMap<>();
        rivers = new HashMap<>();
        tileCompartmentResourceAddedObservers = new ArrayList<>();
    }

    public boolean hasWater()
    {
        return rivers.size() > 0;
    }

    public void add(TileCompartmentDirection dir, River river)
    {
        rivers.put(dir, river);
    }

    public void add(TileCompartmentDirection dir, Road road)
    {
        roads.put(dir, road);
    }

    public void add(Transport transport)
    {
        transports.add(transport);
    }

    public Map<TileCompartmentDirection, Road> getRoads()
    {
        return roads;
    }

    public Map<TileCompartmentDirection, River> getRivers()
    {
        return rivers;
    }

    public Collection<Transport> getTransports()
    {
        return transports;
    }

    public River getRiver(TileCompartmentDirection dir)
    {
        return rivers.get(dir);
    }

    public void setRiverDestination(TileCompartmentDirection dir, TileCompartment destination)
    {
        rivers.get(dir).setDestination(destination);
    }

    public int getWealthPoints()
    {
        return resourceManager.getWealthPoints();
    }

    public ResourceManager getResourceManager()
    {
        return this.resourceManager;
    }

    public void storeResource(ResourceType type, Integer numberToAdd)
    {
        resourceManager.addResource(type, numberToAdd);
        notifyObservers();
    }

    public boolean takeResource(ResourceType type, Integer numberToRemove)
    {
        boolean removed = resourceManager.removeResource(type, numberToRemove);
        if (removed)
            notifyObservers();
        return removed;
    }

    public boolean hasResource(ResourceType wellDoesIt)
    {
        return resourceManager.hasResource(wellDoesIt);
    }

    public int getResourceCount(ResourceType desiredType)
    {
        return resourceManager.getResourceCount(desiredType);
    }

    public boolean hasNoResources()
    {
        return resourceManager.hasNoResources();
    }

    public boolean attachRivers(TileCompartmentDirection direction, TileCompartment compartment)
    {
        River river = rivers.get(direction);

        if (river == null || !compartment.hasRiver(direction.reverse()))
        {
            return false;
        } else
        {
            river.setDestination(compartment);
            return true;
        }
    }

    private boolean hasRiver(TileCompartmentDirection direction)
    {
        return rivers.get(direction) != null;
    }

    public void rotate()
    {
        Map<TileCompartmentDirection, Road> roads = new HashMap<>();
        Map<TileCompartmentDirection, River> rivers = new HashMap<>();

        for (Map.Entry<TileCompartmentDirection, Road> entry : this.roads.entrySet())
        {
            TileCompartmentDirection dir = new TileCompartmentDirection(new Angle(entry.getKey().getAngle().getDegrees() - 60));
            roads.put(dir, entry.getValue());
        }

        for (Map.Entry<TileCompartmentDirection, River> entry : this.rivers.entrySet())
        {
            TileCompartmentDirection dir = new TileCompartmentDirection(new Angle(entry.getKey().getAngle().getDegrees() - 60));
            rivers.put(dir, entry.getValue());
        }

        this.roads = roads;
        this.rivers = rivers;
    }

    public void removeUnattachedRivers()
    {
        Map<TileCompartmentDirection, River> toRemove = new HashMap<>();

        for (Map.Entry<TileCompartmentDirection, River> entry : rivers.entrySet())
        {
            if (entry.getValue().getDestination() == null)
            {
                toRemove.put(entry.getKey(), entry.getValue());
            }
        }

        for (Map.Entry<TileCompartmentDirection, River> entry : toRemove.entrySet())
        {
            rivers.remove(entry.getKey(), entry.getValue());
        }
    }

    private void notifyObservers() {
        for (TileCompartmentResourceAddedObserver observer : tileCompartmentResourceAddedObservers) {
            observer.onTileCompartmentResourcesChanged();
        }
    }

    @Override
    public void attach(TileCompartmentResourceAddedObserver observer) {
        this.tileCompartmentResourceAddedObservers.add(observer);
    }

    @Override
    public void detach(TileCompartmentResourceAddedObserver observer) {
        this.tileCompartmentResourceAddedObservers.remove(observer);
    }

    public Road getRoad(TileCompartmentDirection tcd)
    {
        return roads.get(tcd);
    }

    public void buildRoad(Road road)
    {
        roads.put(road.getCompartmentDirection(), road);
    }

    public boolean canBuildRoad(Road road)
    {
        if(roads.get(road.getCompartmentDirection()) != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public void addResourcesVisit(ResourceType resourceType, Integer amountToAdd) {
        this.storeResource(resourceType, amountToAdd);
    }
}
