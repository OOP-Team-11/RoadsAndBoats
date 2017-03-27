package model;

import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;

import java.util.HashMap;
import java.util.Set;

public class Map
{
    private java.util.Map<Location, Tile> tiles;

    public Map()
    {
        tiles = new HashMap<Location, Tile>();
    }

    public Tile getTile(Location tileLocation)
    {
        return tiles.get(tileLocation);
    }

    public void addTile(Location tileLocation, Tile tile)
    {
        tiles.put(tileLocation, tile);
    }

    public boolean isValidToAddTile(Location tileLocation, Tile tile)
    {
        return true;
    }

    public void recenter()
    {
        Location center = calculateCenter();

        HashMap<Location, Tile> newMap = new HashMap<Location, Tile>();

        for (java.util.Map.Entry<Location, Tile> entry : tiles.entrySet())
        {
            Location loc = offsetLocation(entry.getKey(), center);

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

        int x = (maxX - minX) / 2;
        int y = (maxY - minY) / 2;
        int z = (maxZ - minZ) / 2;

        try
        {
            return new Location(x, y, z);
        }
        catch (InvalidLocationException e)
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
        }
        catch (InvalidLocationException e)
        {
            return null; //Impossible to reach
        }
    }

    private Set<Location> getAllLocations()
    {
        return tiles.keySet();
    }
}
