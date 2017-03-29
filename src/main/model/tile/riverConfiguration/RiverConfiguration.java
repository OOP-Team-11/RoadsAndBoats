package model.tile.riverConfiguration;

import direction.TileEdgeDirection;

import java.util.HashMap;
import java.util.Map;

public class RiverConfiguration {

    private Map<TileEdgeDirection, Boolean> riverMap;

    private RiverConfiguration(Map<TileEdgeDirection, Boolean> riverMap) {
        this.riverMap = riverMap;
    }

    public RiverConfiguration(int side) {

    }

    public RiverConfiguration(int side1, int side2) {

    }

    public RiverConfiguration(int side1, int side2, int side3) {

    }

    private static Map<TileEdgeDirection, Boolean> getDefaultMap() {
        Map<TileEdgeDirection, Boolean> riverMap = new HashMap<>();
        riverMap.put(TileEdgeDirection.getNorth(), false);
        riverMap.put(TileEdgeDirection.getNorthEast(), false);
        riverMap.put(TileEdgeDirection.getNorthWest(), false);
        riverMap.put(TileEdgeDirection.getSouth(), false);
        riverMap.put(TileEdgeDirection.getSouthEast(), false);
        riverMap.put(TileEdgeDirection.getSouthWest(), false);
        return riverMap;
    }

    public boolean canConnectNorth() {
        return this.riverMap.get(TileEdgeDirection.getNorth());
    }

    public boolean canConnectNortheast() {
        return this.riverMap.get(TileEdgeDirection.getNorthEast());
    }

    public boolean canConnectSoutheast() {
        return this.riverMap.get(TileEdgeDirection.getSouthEast());
    }

    public boolean canConnectSouth() {
        return this.riverMap.get(TileEdgeDirection.getSouth());
    }

    public boolean canConnectSouthwest() {
        return this.riverMap.get(TileEdgeDirection.getSouthWest());
    }

    public boolean canConnectNorthwest() {
        return this.riverMap.get(TileEdgeDirection.getNorthWest());
    }

    public static RiverConfiguration getNoRivers() {
        return new RiverConfiguration(getDefaultMap());
    }

    public static RiverConfiguration getSpringHead() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        return new RiverConfiguration(riverMap);
    }

    public static RiverConfiguration getAdjacentFaces() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getNorthEast(), true);
        return new RiverConfiguration(riverMap);
    }

    public static RiverConfiguration getSkipAFace() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouthEast(), true);
        return new RiverConfiguration(riverMap);
    }

    public static RiverConfiguration getOppositeFaces() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouth(), true);
        return new RiverConfiguration(riverMap);
    }

    public static RiverConfiguration getEveryOtherFace() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouthEast(), true);
        riverMap.replace(TileEdgeDirection.getSouthWest(), true);
        return new RiverConfiguration(riverMap);
    }
}
