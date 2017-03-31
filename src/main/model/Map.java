package model;


import direction.DirectionToLocation;
import direction.TileEdgeDirection;

import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.TileEdge;
import view.render.MapMakerRenderInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class Map
{
    private final int MAX_DISTANCE = 21;

    private java.util.Map<Location, Tile> tiles;

    public Map()
    {
        tiles = new LinkedHashMap<Location, Tile>();
//        this.initialize();
    }

    /**
     * Gets a tile from the map
     *
//     * @param tileLocation The location you are trying to find a tile at.
     * @return returns the tile at the specified location or null if there isn't one there.
     */
    public java.util.Map<Location, Tile> getTiles() {
        return tiles;
    }
    public Tile getTile(Location tileLocation)
    {
        return tiles.get(tileLocation);
    }

    public boolean placeTile(Location tileLocation, Tile tile)
    {
        if (!isValidPlacement(tileLocation, tile))
        {
            return false;
        }
        updateTileEdges(tileLocation, tile);
        tiles.put(tileLocation, tile);
        recenter();
        return true;
    }

    /**
     * Checks if a tile may be placed at a location on the map
     *
     * @param tileLocation The location you are trying to place a tile at.
     * @param tile The tile you are trying to place.
     * @return true if tile can be placed at the specified location, false otherwise.
     */
    public boolean isValidPlacement(Location tileLocation, Tile tile)
    {
        return isEmptyMap() ||
                (hasAdjacentTile(tileLocation)
                        && hasMatchingEdges(tileLocation, tile)
                        && isWithinMaxDistance(tileLocation));
    }

    private boolean isEmptyMap()
    {
        return tiles.size() == 0;
    }

    private boolean hasAdjacentTile(Location tileLocation)
    {
        for (TileEdgeDirection dir : TileEdgeDirection.getAllDirections())
        {
            Location loc = DirectionToLocation.getLocation(tileLocation, dir);

            if (tiles.get(loc) != null)
            {
                return true;
            }
        }

        return false;
    }

    private boolean hasMatchingEdges(Location tileLocation, Tile tile)
    {
        for (TileEdgeDirection dir : TileEdgeDirection.getAllDirections())
        {
            Location loc = DirectionToLocation.getLocation(tileLocation, dir);
            Tile existingTile = tiles.get(loc);

            if(existingTile == null)
            {
                continue;
            }

            TileEdge newTileEdge=tile.getTileEdge(dir);
            TileEdge existingTileEdge=existingTile.getTileEdge(dir.reverse());

            if(newTileEdge.hasRiver() && !existingTileEdge.canConnectRiver())
            {
                return false;
            }
        }

        return true;
    }

    private boolean isWithinMaxDistance(Location tileLocation)
    {
        MapBoundary bounds = getMapBoundaries();

        int x = tileLocation.getX();
        int y = tileLocation.getY();
        int z = tileLocation.getZ();

        return x > bounds.maxX - MAX_DISTANCE
                && x < bounds.minX + MAX_DISTANCE
                && y > bounds.maxY - MAX_DISTANCE
                && y < bounds.minY + MAX_DISTANCE
                && z > bounds.maxZ - MAX_DISTANCE
                && z < bounds.minZ + MAX_DISTANCE;
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

    private void updateTileEdges(Location tileLocation, Tile tile)
    {
        for (TileEdgeDirection dir : TileEdgeDirection.getAllDirections())
        {
            Location loc = DirectionToLocation.getLocation(tileLocation, dir);
            Tile existingTile = tiles.get(loc);

            if(existingTile == null)
            {
                continue;
            }

            TileEdge existingTileEdge=existingTile.getTileEdge(dir.reverse());
            tile.setTileEdge(dir, existingTileEdge);
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

        try
        {
            return new Location(x, y, z);
        } catch (InvalidLocationException e)
        {
            return new Location(x, y);
        }
    }

    private static Location offsetLocation(Location loc, Location center)
    {
        try
        {
            return new Location(loc.getX() - center.getX(),
                    loc.getY() - center.getY(),
                    loc.getZ() - center.getZ());
        } catch (InvalidLocationException e)
        {
            return null; //Impossible to reach
        }
    }

    public Set<Location> getAllLocations()
    {
        return tiles.keySet();
    }

    /**
     * Removes a tile at a location in the map if it exists.
     *
     * @param location
     * @return true if tile was removed. false otherwise.
     */
    public boolean removeTileAtLocation(Location location)
    {
        if (this.tiles.containsKey(location))
        {
            this.tiles.remove(location);
            return true;
        }

        return false;
    }

    public boolean hasTiles() {
        return !(tiles.isEmpty());
    }

    public MapMakerRenderInfo getRenderObject() {
        return null;
    }

}
