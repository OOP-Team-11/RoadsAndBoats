package model;


import direction.DirectionToLocation;
import direction.TileEdgeDirection;

import model.tile.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


public class Map {
    private java.util.Map<Location, Tile> tiles;

    public Map()
    {
        tiles = new HashMap<Location, Tile>();
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
                (hasAdjacentTile(tileLocation) && hasMatchingEdges(tileLocation, tile));
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

        HashMap<Location, Tile> newMap = new HashMap<Location, Tile>();

        for (java.util.Map.Entry<Location, Tile> entry : tiles.entrySet())
        {
            Location loc = offsetLocation(entry.getKey(), center);
            newMap.put(loc, entry.getValue());
            this.tiles = newMap;
        }
    }

    private Location calculateCenter()
    {
        int minX = 0;
        int minY = 0;
        int minZ = 0;

        int maxX = 0;
        int maxY = 0;
        int maxZ = 0;

        for (Location loc : getAllLocations())
        {
            minX = Math.min(minX, loc.getX());
            minY = Math.min(minY, loc.getY());
            minZ = Math.min(minZ, loc.getZ());

            maxX = Math.max(maxX, loc.getX());
            maxY = Math.max(maxY, loc.getY());
            maxZ = Math.max(maxZ, loc.getZ());
        }

        int x = (maxX + minX) / 2;
        int y = (maxY + minY) / 2;
        int z = (maxZ + minZ) / 2;

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

    private Set<Location> getAllLocations()
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
    public void setTile(Location tileLocation, Terrain terrain){this.tiles.put(tileLocation, new Tile(terrain));}
    public boolean hasTiles(){return !(tiles.isEmpty());}
    public Collection<Location> getLocations(){return tiles.keySet();}
}
