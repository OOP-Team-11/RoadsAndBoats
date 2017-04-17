package game.model.map;

import game.model.direction.DirectionToLocation;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.tile.*;
import game.model.wonder.Irrigatable;
import game.utilities.observable.MapRenderInfoObservable;
import game.utilities.observer.MapRenderInfoObserver;
import game.view.render.MapRenderInfo;

import java.util.*;

public class RBMap implements MapRenderInfoObservable, Irrigatable
{
    private Map<Location, Tile> tiles;
    private Vector<MapRenderInfoObserver> mapRenderInfoObservers;

    public RBMap()
    {
        tiles = new LinkedHashMap<>();
        mapRenderInfoObservers = new Vector<>();
    }

    /**
     * Gets a tile from the map
     *
     * @return returns all tiles on the map.
     */
    public java.util.Map<Location, Tile> getTiles()
    {
        return tiles;
    }

    public Tile getTile(Location tileLocation)
    {
        return tiles.get(tileLocation);
    }

    public boolean placeTile(Location tileLocation, Tile tile)
    {
        updateTileEdges(tileLocation, tile);
        updateRiverConnections(tileLocation, tile);
        tiles.put(tileLocation, tile);
        notifyMapRenderInfoObservers();
        return true;
    }

    private void updateRiverConnections(Location tileLocation, Tile tile)
    {
        for (TileEdgeDirection dir : TileEdgeDirection.getAllDirections())
        {
            Location loc = DirectionToLocation.getLocation(tileLocation, dir);
            Tile existingTile = tiles.get(loc);

            if (existingTile == null)
            {
                continue;
            }

            TileCompartmentDirection directionToNew = new TileCompartmentDirection(dir.reverse().getAngle());
            TileCompartmentDirection directionToExisting = new TileCompartmentDirection(dir.getAngle());

            TileCompartment existingComp = existingTile.getTileCompartment(directionToNew);
            TileCompartment newComp = tile.getTileCompartment(directionToExisting);

            newComp.attachRivers(directionToExisting, existingComp);
            existingComp.attachRivers(directionToNew, newComp);
        }
    }

    private void updateTileEdges(Location tileLocation, Tile tile)
    {
        for (TileEdgeDirection dir : TileEdgeDirection.getAllDirections())
        {
            Location loc = DirectionToLocation.getLocation(tileLocation, dir);
            Tile existingTile = tiles.get(loc);

            if (existingTile == null)
            {
                continue;
            }

            TileEdge newTileEdge = tile.getTileEdge(dir);
            TileEdge existingTileEdge = existingTile.getTileEdge(dir.reverse());

            boolean canConnectRiver = newTileEdge.canConnectRiver() || existingTileEdge.canConnectRiver();
            existingTileEdge.setCanConnectRiver(canConnectRiver);

            tile.setTileEdge(dir, existingTileEdge);
        }
    }

    public Set<Location> getAllLocations()
    {
        return tiles.keySet();
    }

    public boolean hasTiles()
    {
        return !(tiles.isEmpty());
    }

    public boolean updateTerrain(Location location, Terrain terrain)
    {
        if (!this.tiles.containsKey(location)) return false;

        Tile tileAtLocation = this.tiles.get(location);
        tileAtLocation.setTerrain(terrain);
        notifyMapRenderInfoObservers();
        return true;
    }


    public MapRenderInfo getMapRenderInfo() {
        Map<Location, Terrain> terrainMap = new HashMap<>();
        Map<Location, RiverConfiguration> riverConfigurationMap = new HashMap<>();

        Iterator it = tiles.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Location location = (Location) pair.getKey();
            Tile tile = (Tile) pair.getValue();
            terrainMap.put(location, tile.getTerrain());
            riverConfigurationMap.put(location, tile.getRiverConfiguration());
        }
        return new MapRenderInfo(terrainMap, riverConfigurationMap, this);
    }

    public void attach(MapRenderInfoObserver observer)
    {
        mapRenderInfoObservers.add(observer);
        notifyMapRenderInfoObservers();
    }

    public void detach(MapRenderInfoObserver observer)
    {
        mapRenderInfoObservers.remove(observer);
    }

    private void notifyMapRenderInfoObservers()
    {
        for (MapRenderInfoObserver observer : mapRenderInfoObservers)
        {
            observer.updateMapInfo(getMapRenderInfo());
        }
    }

    public void finalizeMap()
    {
        for(Tile tile: tiles.values())
        {
            tile.removeUnattachedRivers();
        }
    }


    @Override
    public void irrigate() {
        for (Tile tile : tiles.values()) {
            tile.irrigate();
        }
        notifyMapRenderInfoObservers();
    }
}
