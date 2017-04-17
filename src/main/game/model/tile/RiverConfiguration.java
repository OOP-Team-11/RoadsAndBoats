package game.model.tile;


import game.model.direction.TileEdgeDirection;
import game.model.gameImportExport.exporter.Serializable;

import java.util.*;

public class RiverConfiguration implements Serializable {

    private Map<TileEdgeDirection, Boolean> riverMap;
    private int rotationAmount;

    public RiverConfiguration(Map<TileEdgeDirection, Boolean> riverMap) {
        this.riverMap = riverMap;
        this.rotationAmount = 0;
    }

    public RiverConfiguration(int side) {
        if (side < 1 || side > 6) throw new IllegalArgumentException("Side must be [1-6]");
        this.riverMap = getSpringHeadMap();
        this.rotationAmount = side - 1;
    }

    public RiverConfiguration(int side1, int side2) {
        if (side1 < 1 || side1 > 6) throw new IllegalArgumentException("Side1 must be [1-6]");
        if (side2 < 1 || side2 > 6) throw new IllegalArgumentException("Side2 must be [1-6]");
        if (side2 < side1) throw new IllegalArgumentException("Side2 must be less than side1");

        int sideDifference = side2 - side1;
        if (sideDifference == 4) {
            sideDifference = 2;
            this.rotationAmount = Math.max(side1, side2) - 1;
        }
        else if (sideDifference == 5) {
            sideDifference = 1;
            this.rotationAmount = Math.max(side1, side2) - 1;
        } else {
            this.rotationAmount = Math.min(side1, side2) - 1;
        }

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

    }

    public RiverConfiguration(int side1, int side2, int side3) {
        if (side1 < 1 || side1 > 6) throw new IllegalArgumentException("Side1 must be [1-6]");
        if (side2 < 1 || side2 > 6) throw new IllegalArgumentException("Side2 must be [1-6]");
        if (side3 < 1 || side3 > 6) throw new IllegalArgumentException("Side3 must be [1-6]");
        if (side2 < side1 || side3 < side2) throw new IllegalArgumentException("Side1 < side2 < side2");
        if (side3 - side2 != 2 || side2 - side1 != 2) throw new IllegalArgumentException("Sides must be 2 apart.");

        this.riverMap = getEveryOtherFaceMap();

        if (side1 == 1) {
            this.rotationAmount = 0;
        } else if (side1 == 2) {
            this.rotationAmount = 1;
        }
    }
    public void rotateRiverConfigurationOnce(){
        Boolean north = riverMap.get(TileEdgeDirection.getNorth());
        Boolean northEast =  riverMap.get(TileEdgeDirection.getNorthEast());
        Boolean southEast = riverMap.get(TileEdgeDirection.getSouthEast());
        Boolean south = riverMap.get(TileEdgeDirection.getSouth());
        Boolean southWest = riverMap.get(TileEdgeDirection.getSouthWest());
        Boolean northWest = riverMap.get(TileEdgeDirection.getNorthWest());

        this.riverMap = null;
        this.riverMap = new HashMap<>();
        riverMap.put(TileEdgeDirection.getNorth(), northWest);
        riverMap.put(TileEdgeDirection.getNorthEast(), north);
        riverMap.put(TileEdgeDirection.getSouthEast(), northEast);
        riverMap.put(TileEdgeDirection.getSouth(), southEast);
        riverMap.put(TileEdgeDirection.getSouthWest(), south);
        riverMap.put(TileEdgeDirection.getNorthWest(), southWest);
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

    private static Map<TileEdgeDirection, Boolean> getAdjacentFacesMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getNorthEast(), true);
        return riverMap;
    }

    public static RiverConfiguration getAdjacentFaces() {
        return new RiverConfiguration(getAdjacentFacesMap());
    }

    private static Map<TileEdgeDirection, Boolean> getSkipAFaceMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouthEast(), true);
        return riverMap;
    }

    public static RiverConfiguration getSkipAFace() {
        return new RiverConfiguration(getSkipAFaceMap());
    }

    private static Map<TileEdgeDirection, Boolean> getOppositeFacesMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouth(), true);
        return riverMap;
    }

    public static RiverConfiguration getOppositeFaces() {
        return new RiverConfiguration(getOppositeFacesMap());
    }

    private static Map<TileEdgeDirection, Boolean> getEveryOtherFaceMap() {
        Map<TileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(TileEdgeDirection.getNorth(), true);
        riverMap.replace(TileEdgeDirection.getSouthEast(), true);
        riverMap.replace(TileEdgeDirection.getSouthWest(), true);
        return riverMap;
    }

    public static RiverConfiguration getEveryOtherFace() {
        return new RiverConfiguration(getEveryOtherFaceMap());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(riverMap, rotationAmount);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof RiverConfiguration))
        {
            return false;
        }

        RiverConfiguration rc = (RiverConfiguration)o;

        return rc.riverMap.equals(riverMap) && rotationAmount == rc.rotationAmount;
    }

    public boolean canConnect(TileEdgeDirection dir)
    {
        return riverMap.get(dir);
    }

    private int getNumRivers() {
        int numRivers = 0;
        for (Boolean hasRiver : riverMap.values()) {
            if (hasRiver) numRivers++;
        }
        return numRivers;
    }

    private int getSideNumberForTileEdgeDirection(TileEdgeDirection tileEdgeDirection) {
        if (tileEdgeDirection.equals(TileEdgeDirection.getNorth())) {
            return 1;
        } else if (tileEdgeDirection.equals(TileEdgeDirection.getNorthEast())) {
            return 2;
        } else if (tileEdgeDirection.equals(TileEdgeDirection.getSouthEast())) {
            return 3;
        } else if (tileEdgeDirection.equals(TileEdgeDirection.getSouth())) {
            return 4;
        } else if (tileEdgeDirection.equals(TileEdgeDirection.getSouthWest())) {
            return 5;
        } else if (tileEdgeDirection.equals(TileEdgeDirection.getNorthWest())) {
            return 6;
        } else {
            throw new RuntimeException("Invalid TileEdgeDirection.");
        }
    }

    public String getExportString() {
        StringBuilder sb = new StringBuilder();
        if (getNumRivers() == 0) return "";

        sb.append("( ");

        Iterator it = riverMap.entrySet().iterator();
        List<Integer> riverConnections = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileEdgeDirection tileEdgeDirection = (TileEdgeDirection) pair.getKey();
            Boolean hasRiver = (Boolean) pair.getValue();
            if (hasRiver) {
                riverConnections.add(getSideNumberForTileEdgeDirection(tileEdgeDirection));
            }
        }

        Collections.sort(riverConnections);
        for (Integer side : riverConnections) {
            sb.append(side).append(" ");
        }
        sb.append(")");
        return sb.toString();
    }
}
