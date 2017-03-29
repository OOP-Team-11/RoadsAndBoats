package model.tile.riverConfiguration;

import direction.TileEdgeDirection;

import java.util.HashMap;
import java.util.Map;

public class RiverConfiguration {

    private Map<TileEdgeDirection, Boolean> riverMap;
    private int rotationAmount;

    private RiverConfiguration(Map<TileEdgeDirection, Boolean> riverMap) {
        this.riverMap = riverMap;
    }

    public RiverConfiguration(int side) {
        if (side < 1 || side > 6) throw new IllegalArgumentException("Side must be [1-6]");
        this.riverMap = getSpringHeadMap();
        this.rotationAmount = side - 1;
    }

    public RiverConfiguration(int side1, int side2) {
        if (side2 < side1) throw new IllegalArgumentException("Side2 must be less than side1");

        int sideDifference = side2 - side1;
        switch (sideDifference) {
            case 1:
                this.riverMap = getAdjacentFacesMap();
                break;
            case 2:
                this.riverMap = getSkipAFaceMap();
                break;
            case 3:
                this.riverMap = getOppositeFacesMap();
                break;
        }

        this.rotationAmount = side1 - 1;
    }

    public RiverConfiguration(int side1, int side2, int side3) {
        if (side2 < side1 || side3 < side2) throw new IllegalArgumentException("Side1 < side2 < side2");
        if (side3 - side2 != 2 || side2 - side1 != 2) throw new IllegalArgumentException("Sides must be 2 apart.");

        this.riverMap = getEveryOtherFaceMap();

        if (side1 == 1) {
            this.rotationAmount = 0;
        } else if (side1 == 2) {
            this.rotationAmount = 1;
        }
    }

    public int getRotationAmount() {
        return this.rotationAmount;
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

    private static Map<TileEdgeDirection, Boolean> getSpringHeadMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        return riverMap;
    }

    public static RiverConfiguration getSpringHead() {
        return new RiverConfiguration(getSpringHeadMap());
    }

    public static Map<TileEdgeDirection, Boolean> getAdjacentFacesMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getNorthEast(), true);
        return riverMap;
    }

    public static RiverConfiguration getAdjacentFaces() {
        return new RiverConfiguration(getAdjacentFacesMap());
    }

    public static Map<TileEdgeDirection, Boolean> getSkipAFaceMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouthEast(), true);
        return riverMap;
    }

    public static RiverConfiguration getSkipAFace() {
        return new RiverConfiguration(getSkipAFaceMap());
    }

    public static Map<TileEdgeDirection, Boolean> getOppositeFacesMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouth(), true);
        return riverMap;
    }

    public static RiverConfiguration getOppositeFaces() {
        return new RiverConfiguration(getOppositeFacesMap());
    }

    public static Map<TileEdgeDirection, Boolean> getEveryOtherFaceMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouthEast(), true);
        riverMap.replace(TileEdgeDirection.getSouthWest(), true);
        return riverMap;
    }

    public static RiverConfiguration getEveryOtherFace() {
        return new RiverConfiguration(getEveryOtherFaceMap());
    }
}
