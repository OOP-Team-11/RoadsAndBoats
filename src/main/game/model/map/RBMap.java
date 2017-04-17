package game.model.map;

import game.model.direction.*;
import game.model.tile.*;
import game.model.wonder.Irrigatable;
import game.utilities.observable.MapRenderInfoObservable;
import game.utilities.observable.MapResourceRenderInfoObservable;
import game.utilities.observer.MapRenderInfoObserver;
import game.utilities.observer.MapResourceRenderInfoObserver;
import game.utilities.observer.TileResourceInfoObserver;
import game.view.render.MapRenderInfo;
import game.view.render.MapResourceRenderInfo;
import game.view.render.ResourceManagerRenderInfo;
import game.view.render.TileResourceRenderInfo;

import java.util.*;

public class RBMap implements MapRenderInfoObservable, Irrigatable, TileResourceInfoObserver, MapResourceRenderInfoObservable
{
    private Map<Location, Tile> tiles;
    private List<MapRenderInfoObserver> mapRenderInfoObservers;
    private List<MapResourceRenderInfoObserver> mapResourceRenderInfoObservers;

    public RBMap()
    {
        tiles = new LinkedHashMap<>();
        mapRenderInfoObservers = new Vector<>();
        mapResourceRenderInfoObservers = new Vector<>();
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
        tile.attach(this);
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
        for (Tile tile : tiles.values())
        {
            tile.removeUnattachedRivers();
        }
    }

    private void notifyMapResourceRenderInfoObservers(TileResourceRenderInfo tileResourceRenderInfo) {
        Map<TileCompartmentLocation, ResourceManagerRenderInfo> mapResources = new HashMap<>();
        Iterator it = tileResourceRenderInfo.getTileResourceMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileCompartmentDirection tcd = (TileCompartmentDirection) pair.getKey();
            ResourceManagerRenderInfo resourceManagerRenderInfo = (ResourceManagerRenderInfo) pair.getValue();
            TileCompartmentLocation tcl = new TileCompartmentLocation(getLocationForTile(tileResourceRenderInfo.getTile()), tcd);
            mapResources.put(tcl, resourceManagerRenderInfo);
        }
        MapResourceRenderInfo mapResourceRenderInfo = new MapResourceRenderInfo(mapResources);

        for (MapResourceRenderInfoObserver observer : this.mapResourceRenderInfoObservers) {
            observer.updateMapResourceInfo(mapResourceRenderInfo);
        }
    }

    private Location getLocationForTile(Tile searchTile) {
        Iterator it = tiles.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Location location = (Location) pair.getKey();
            Tile tile = (Tile) pair.getValue();
            if (searchTile.equals(tile)) return location;
        }
        return null;
    }


    @Override
    public void irrigate() {
        for (Tile tile : tiles.values()) {
            tile.irrigate();
        }
        notifyMapRenderInfoObservers();
    }

    @Override
    public void onTileResourcesUpdated(TileResourceRenderInfo tileResourceRenderInfo) {
        notifyMapResourceRenderInfoObservers(tileResourceRenderInfo);
    }

    @Override
    public void attachMapResourceRenderInfoObserver(MapResourceRenderInfoObserver observer) {
        this.mapResourceRenderInfoObservers.add(observer);
    }

    @Override
    public void detachMapResourceRenderInfoObserver(MapResourceRenderInfoObserver observer) {
        this.mapResourceRenderInfoObservers.remove(observer);
    }

    public Set<TileCompartment> getAdjacentTileCompartments(TileCompartmentLocation tileCompartmentLocation)
    {
        Set<TileCompartment> comps=new HashSet<>();

        Location loc = tileCompartmentLocation.getLocation();
        Tile tile = getTile(loc);
        TileCompartment comp = tile.getTileCompartment(tileCompartmentLocation.getTileCompartmentDirection());
        Set<TileCompartmentDirection> compDirs = tile.getTileCompartmentDirections(comp);

        for (TileCompartmentDirection tcd : compDirs)
        {
            if (Tile.isCorner(tcd))
            {
                TileEdgeDirection tedL = new TileEdgeDirection(new Angle(tcd.getAngle().getDegrees() + 30));
                TileEdgeDirection tedR = new TileEdgeDirection(new Angle(tcd.getAngle().getDegrees() - 30));

                Location locL = DirectionToLocation.getLocation(loc, tedL);
                Location locR = DirectionToLocation.getLocation(loc, tedR);

                Tile tileL = tiles.get(locL);
                Tile tileR = tiles.get(locR);

                if (tileL != null)
                {
                    comps.add(tileL.getTileCompartment(new TileCompartmentDirection(new Angle(tedL.reverse().getAngle().getDegrees()-30))));
                }

                if (tileR != null)
                {
                    comps.add(tileR.getTileCompartment(new TileCompartmentDirection(new Angle(tedR.reverse().getAngle().getDegrees()+30))));
                }
            }
            else
            {
                TileEdgeDirection ted = new TileEdgeDirection(new Angle(tcd.getAngle().getDegrees() + 30));

                Location adjacentLoc = DirectionToLocation.getLocation(loc, ted);

                Tile adjacentTile = tiles.get(adjacentLoc);

                if (adjacentTile != null)
                {
                    comps.add(adjacentTile.getTileCompartment(new TileCompartmentDirection(ted.reverse().getAngle())));
                }
            }
        }

        return comps;
    }
}
