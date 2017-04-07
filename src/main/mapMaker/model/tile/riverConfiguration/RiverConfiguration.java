package mapMaker.model.tile.riverConfiguration;

import mapMaker.direction.TileEdgeDirection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RiverConfiguration implements  Cloneable{

    private Map<TileEdgeDirection, Boolean> riverMap;
    private int rotationAmount;

    private RiverConfiguration(Map<TileEdgeDirection, Boolean> riverMap) {
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

    public Object clone(){
        try{
            RiverConfiguration riverClone = (RiverConfiguration) super.clone();
            Map<TileEdgeDirection, Boolean> riverMapClone = new HashMap<TileEdgeDirection,Boolean>();
            for (Map.Entry<TileEdgeDirection, Boolean> entry : riverMap.entrySet())
            {
                TileEdgeDirection edgeClone = (TileEdgeDirection)(entry.getKey()).clone();
                riverMapClone.put(edgeClone,entry.getValue());
            }
            return riverClone;
        } catch(CloneNotSupportedException e){
            throw new InternalError(e.toString());
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
        this.riverMap = new HashMap<TileEdgeDirection, Boolean>();
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
}
