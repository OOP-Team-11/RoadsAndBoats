package mapMaker.model;
import mapMaker.controller.mmMapRenderTranslator;
import mapMaker.direction.mmDirectionToLocation;
import mapMaker.direction.mmTileEdgeDirection;

import mapMaker.model.tile.mmInvalidLocationException;
import mapMaker.model.tile.mmLocation;
import mapMaker.model.tile.mmTile;
import mapMaker.model.tile.mmTileEdge;

import mapMaker.view.render.mmMapMakerRenderInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class mmMap
{
    private final int MAX_DISTANCE = 21;

    private java.util.Map<mmLocation, mmTile> tiles;

    public mmMap()
    {
        tiles = new LinkedHashMap<mmLocation, mmTile>();
    }

    /**
     * Gets a tile from the map
     *
     * @return returns all tiles on the map.
     */
    public java.util.Map<mmLocation, mmTile> getTiles()
    {
        return tiles;
    }

    public mmTile getTile(mmLocation tileMmLocation)
    {
        return tiles.get(tileMmLocation);
    }

    public boolean placeTile(mmLocation tileMmLocation, mmTile mmTile)
    {
        if (!isValidPlacement(tileMmLocation, mmTile))
        {
            return false;
        }

        updateTileEdges(tileMmLocation, mmTile);
        tiles.put(tileMmLocation, mmTile);

        return true;
    }
    public boolean placeTileWithoutCheck(mmLocation tileMmLocation, mmTile mmTile){
        updateTileEdges(tileMmLocation, mmTile);
        tiles.put(tileMmLocation, mmTile);
        return true;
    }
    /**
     * Checks if a mmTile may be placed at a location on the map
     *
     * @param tileMmLocation The location you are trying to place a mmTile at.
     * @param mmTile The mmTile you are trying to place.
     * @return true if mmTile can be placed at the specified location, false otherwise.
     */
    public boolean isValidPlacement(mmLocation tileMmLocation, mmTile mmTile)
    {
        return isEmptyMap() ||
                (hasAdjacentTile(tileMmLocation)
                        && noTileAlreadyPresent(tileMmLocation, mmTile)
                        && hasMatchingEdges(tileMmLocation, mmTile)
                        && isWithinMaxDistance(tileMmLocation));
    }

    private boolean isEmptyMap()
    {
        return tiles.size() == 0;
    }

    private boolean hasAdjacentTile(mmLocation tileMmLocation)
    {
        for (mmTileEdgeDirection dir : mmTileEdgeDirection.getAllDirections())
        {
            mmLocation loc = mmDirectionToLocation.getLocation(tileMmLocation, dir);

            if (tiles.get(loc) != null)
            {
                return true;
            }
        }

        return false;
    }

    private boolean noTileAlreadyPresent(mmLocation tileMmLocation, mmTile mmTile)
    {
        return !tiles.containsKey(tileMmLocation);
    }

    private boolean hasMatchingEdges(mmLocation tileMmLocation, mmTile mmTile)
    {
        for (mmTileEdgeDirection dir : mmTileEdgeDirection.getAllDirections())
        {
            mmLocation loc = mmDirectionToLocation.getLocation(tileMmLocation, dir);
            mmTile existingMmTile = tiles.get(loc);

            if(existingMmTile == null)
            {
                continue;
            }

            mmTileEdge newMmTileEdge = mmTile.getTileEdge(dir);
            mmTileEdge existingMmTileEdge = existingMmTile.getTileEdge(dir.reverse());

            if(newMmTileEdge.hasRiver() && !existingMmTileEdge.canConnectRiver() ||
                    existingMmTileEdge.hasRiver() && !newMmTileEdge.canConnectRiver())
            {
                return false;
            }
        }

        return true;
    }

    private boolean isWithinMaxDistance(mmLocation tileMmLocation)
    {
        mmMapBoundary bounds = getMapBoundaries();

        int x = tileMmLocation.getX();
        int y = tileMmLocation.getY();
        int z = tileMmLocation.getZ();

        return x > bounds.maxX - MAX_DISTANCE
                && x < bounds.minX + MAX_DISTANCE
                && y > bounds.maxY - MAX_DISTANCE
                && y < bounds.minY + MAX_DISTANCE
                && z > bounds.maxZ - MAX_DISTANCE
                && z < bounds.minZ + MAX_DISTANCE;
    }

    private mmMapBoundary getMapBoundaries()
    {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;

        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;

        for (mmLocation loc : getAllLocations())
        {
            minX = Math.min(minX, loc.getX());
            minY = Math.min(minY, loc.getY());
            minZ = Math.min(minZ, loc.getZ());

            maxX = Math.max(maxX, loc.getX());
            maxY = Math.max(maxY, loc.getY());
            maxZ = Math.max(maxZ, loc.getZ());
        }

        return new mmMapBoundary(minX, minY, minZ, maxX, maxY, maxZ);
    }

    private void updateTileEdges(mmLocation tileMmLocation, mmTile mmTile)
    {
        for (mmTileEdgeDirection dir : mmTileEdgeDirection.getAllDirections())
        {
            mmLocation loc = mmDirectionToLocation.getLocation(tileMmLocation, dir);
            mmTile existingMmTile = tiles.get(loc);

            if(existingMmTile == null)
            {
                continue;
            }

            mmTileEdge newMmTileEdge = mmTile.getTileEdge(dir);
            mmTileEdge existingMmTileEdge = existingMmTile.getTileEdge(dir.reverse());

            boolean canConnectRiver = newMmTileEdge.canConnectRiver() || existingMmTileEdge.canConnectRiver();
            existingMmTileEdge.setCanConnectRiver(canConnectRiver);

            mmTile.setTileEdge(dir, existingMmTileEdge);
        }
    }

    /**
     * Sets the center mmTile as (0,0,0) and recalculates all the other locations around it.
     */
    public void recenter()
    {
        mmLocation center = calculateCenter();

        HashMap<mmLocation, mmTile> newMap = new LinkedHashMap<mmLocation, mmTile>();

        for (java.util.Map.Entry<mmLocation, mmTile> entry : tiles.entrySet())
        {
            mmLocation loc = offsetLocation(entry.getKey(), center);
            newMap.put(loc, entry.getValue());
            this.tiles = newMap;
        }
    }

    private mmLocation calculateCenter()
    {
        mmMapBoundary bounds = getMapBoundaries();

        int x = (bounds.maxX + bounds.minX) / 2;
        int y = (bounds.maxY + bounds.minY) / 2;
        int z = (bounds.maxZ + bounds.minZ) / 2;

        try
        {
            return new mmLocation(x, y, z);
        } catch (mmInvalidLocationException e)
        {
            return new mmLocation(x, y);
        }
    }

    private static mmLocation offsetLocation(mmLocation loc, mmLocation center)
    {
        try
        {
            return new mmLocation(loc.getX() - center.getX(),
                    loc.getY() - center.getY(),
                    loc.getZ() - center.getZ());
        } catch (mmInvalidLocationException e)
        {
            return null; //Impossible to reach
        }
    }

    public Set<mmLocation> getAllLocations()
    {
        return tiles.keySet();
    }

    /**
     * Removes a tile at a mmLocation in the map if it exists.
     *
     * @param mmLocation
     * @return true if tile was removed. false otherwise.
     */
    public boolean removeTileAtLocation(mmLocation mmLocation)
    {
        if (this.tiles.containsKey(mmLocation))
        {
            this.tiles.remove(mmLocation);

            for (mmTileEdgeDirection dir : mmTileEdgeDirection.getAllDirections())
            {
                mmLocation loc = mmDirectionToLocation.getLocation(mmLocation, dir);
                mmTile mmTile = tiles.get(loc);
                if(mmTile != null)
                {
                    mmTile.resetTileEdge(dir);
                }
            }

            return true;
        }
        return false;
    }

    public boolean hasTiles() {
        return !(tiles.isEmpty());
    }

    public mmMapMakerRenderInfo getRenderObject() {
        return new mmMapMakerRenderInfo(mmMapRenderTranslator.getRenderInformationForMap(this));
    }

    public boolean isValid()
    {
        boolean b = !isEmptyMap();
        b = b && isContinuousMap();
        b = b && hasNoHangingRiver();
        b = b && allTilesAreValid();

        return b || this.tiles.size() == 1 && hasNoHangingRiver();
    }

    private boolean isContinuousMap()
    {
        mmLocation loc = getTiles().keySet().iterator().next();

        int continuousSize = getAllConnectedLocations(loc, new HashSet<mmLocation>()).size();

        return continuousSize == getTiles().size();
    }

    private Set<mmLocation> getAllConnectedLocations(mmLocation loc, Set<mmLocation> mmLocations)
    {
        for (mmTileEdgeDirection dir : mmTileEdgeDirection.getAllDirections())
        {
            mmLocation newLoc = mmDirectionToLocation.getLocation(loc, dir);

            if(tiles.containsKey(newLoc) && mmLocations.add(newLoc))
            {
                getAllConnectedLocations(newLoc, mmLocations);
            }
        }

        return mmLocations;
    }

    private boolean hasNoHangingRiver()
    {
        for(mmLocation loc: getTiles().keySet())
        {
            if(hasHangingRiver(loc))
            {
                return false;
            }
        }

        return true;
    }

    private boolean hasHangingRiver(mmLocation tileMmLocation)
    {
        mmTile mmTile =getTile(tileMmLocation);

        for (mmTileEdgeDirection dir : mmTileEdgeDirection.getAllDirections())
        {
            mmLocation loc = mmDirectionToLocation.getLocation(tileMmLocation, dir);
            mmTile adjacentMmTile = tiles.get(loc);

            mmTileEdge mmTileEdge = mmTile.getTileEdge(dir);

            if(adjacentMmTile == null && mmTileEdge.hasRiver() && !mmTile.getMmTerrain().canConnectRiver())
            {
                return true;
            }
        }

        return false;
    }

    private boolean allTilesAreValid()
    {
        for(java.util.Map.Entry<mmLocation, mmTile> entry: getTiles().entrySet())
        {
            mmLocation tileMmLocation = entry.getKey();
            mmTile mmTile = entry.getValue();

            if(!hasAdjacentTile(tileMmLocation)
                    || !hasMatchingEdges(tileMmLocation, mmTile)
                    || !isWithinMaxDistance(tileMmLocation))
            {
                return false;
            }
        }

        return true;
    }

}
