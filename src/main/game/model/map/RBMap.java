package game.model.map;

import game.model.direction.DirectionToLocation;
import game.model.direction.Location;
import game.model.direction.TileEdgeDirection;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.tile.TileEdge;
import game.utilities.observable.MapRenderInfoObservable;
import game.utilities.observer.MapRenderInfoObserver;
import game.view.render.MapRenderInfo;

import java.util.*;

public class RBMap implements MapRenderInfoObservable
{
    private final int MAX_DISTANCE = 21;

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
    
    public boolean placeTile(Location tileLocation, Tile Tile){
        updateTileEdges(tileLocation, Tile);
        tiles.put(tileLocation, Tile);
        notifyMapRenderInfoObservers();
        return true;
    }

    private MapBoundary getMapBoundaries()
    {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;

        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;

        for (Location loc : getAllLocations())
        {
            minX = Math.min(minX, loc.getX());
            minY = Math.min(minY, loc.getY());
            minZ = Math.min(minZ, loc.getZ());

            maxX = Math.max(maxX, loc.getX());
            maxY = Math.max(maxY, loc.getY());
            maxZ = Math.max(maxZ, loc.getZ());
        }

        return new MapBoundary(minX, minY, minZ, maxX, maxY, maxZ);
    }

    private void updateTileEdges(Location tileLocation, Tile Tile)
    {
        for (TileEdgeDirection dir : TileEdgeDirection.getAllDirections())
        {
            Location loc = DirectionToLocation.getLocation(tileLocation, dir);
            Tile existingTile = tiles.get(loc);

            if(existingTile == null)
            {
                continue;
            }

            TileEdge newTileEdge = Tile.getTileEdge(dir);
            TileEdge existingTileEdge = existingTile.getTileEdge(dir.reverse());

            boolean canConnectRiver = newTileEdge.canConnectRiver() || existingTileEdge.canConnectRiver();
            existingTileEdge.setCanConnectRiver(canConnectRiver);

            Tile.setTileEdge(dir, existingTileEdge);
        }
    }

    /**
     * Sets the center Tile as (0,0,0) and recalculates all the other locations around it.
     */
    public void recenter()
    {
        Location center = calculateCenter();

        HashMap<Location, Tile> newMap = new LinkedHashMap<Location, Tile>();

        for (java.util.Map.Entry<Location, Tile> entry : tiles.entrySet())
        {
            Location loc = offsetLocation(entry.getKey(), center);
            newMap.put(loc, entry.getValue());
            this.tiles = newMap;
        }
    }

    private Location calculateCenter()
    {
        MapBoundary bounds = getMapBoundaries();

        int x = (bounds.maxX + bounds.minX) / 2;
        int y = (bounds.maxY + bounds.minY) / 2;
        int z = (bounds.maxZ + bounds.minZ) / 2;

        return new Location(x, y, z);
    }

    private static Location offsetLocation(Location loc, Location center)
    {
        return new Location(loc.getX() - center.getX(),
                loc.getY() - center.getY(),
                loc.getZ() - center.getZ());
    }

    public Set<Location> getAllLocations()
    {
        return tiles.keySet();
    }

    public boolean hasTiles() {
        return !(tiles.isEmpty());
    }

    public boolean updateTerrain(Location location, Terrain terrain) {
        if (!this.tiles.containsKey(location)) return false;

        Tile tileAtLocation = this.tiles.get(location);
        tileAtLocation.setTerrain(terrain);
        notifyMapRenderInfoObservers();
        return true;
    }

    private MapRenderInfo getMapRenderInfo() {
        Map<Location, Terrain> terrainMap = new HashMap<>();
        Map<Location, RiverConfiguration> riverConfigurationMap = new HashMap<>();

        Iterator it = tiles.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Location location = (Location) pair.getKey();
            Tile tile = (Tile) pair.getValue();
            terrainMap.put(location, tile.getTerrain());
            riverConfigurationMap.put(location, tile.getRiverConfiguration());
        }
        return new MapRenderInfo(terrainMap, riverConfigurationMap, this);
    }

    public void attach(MapRenderInfoObserver observer) {
        mapRenderInfoObservers.add(observer);
        notifyMapRenderInfoObservers();
    }

    public void detach(MapRenderInfoObserver observer) {
        mapRenderInfoObservers.remove(observer);
    }

    private void notifyMapRenderInfoObservers() {
        for (MapRenderInfoObserver observer : mapRenderInfoObservers) {
            observer.updateMapInfo(getMapRenderInfo());
        }
    }
}
