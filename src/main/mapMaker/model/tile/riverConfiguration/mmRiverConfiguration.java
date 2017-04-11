package mapMaker.model.tile.riverConfiguration;

import mapMaker.direction.mmTileEdgeDirection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class mmRiverConfiguration implements  Cloneable{

    private Map<mmTileEdgeDirection, Boolean> riverMap;
    private int rotationAmount;

    private mmRiverConfiguration(Map<mmTileEdgeDirection, Boolean> riverMap) {
        this.riverMap = riverMap;
        this.rotationAmount = 0;
    }

    public mmRiverConfiguration(int side) {
        if (side < 1 || side > 6) throw new IllegalArgumentException("Side must be [1-6]");
        this.riverMap = getSpringHeadMap();
        this.rotationAmount = side - 1;
    }

    public mmRiverConfiguration(int side1, int side2) {
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
            mmRiverConfiguration riverClone = (mmRiverConfiguration) super.clone();
            Map<mmTileEdgeDirection, Boolean> riverMapClone = new HashMap<mmTileEdgeDirection,Boolean>();
            for (Map.Entry<mmTileEdgeDirection, Boolean> entry : riverMap.entrySet())
            {
                mmTileEdgeDirection edgeClone = (mmTileEdgeDirection)(entry.getKey()).clone();
                riverMapClone.put(edgeClone,entry.getValue());
            }
            return riverClone;
        } catch(CloneNotSupportedException e){
            throw new InternalError(e.toString());
        }
    }

    public mmRiverConfiguration(int side1, int side2, int side3) {
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
        Boolean north = riverMap.get(mmTileEdgeDirection.getNorth());
        Boolean northEast =  riverMap.get(mmTileEdgeDirection.getNorthEast());
        Boolean southEast = riverMap.get(mmTileEdgeDirection.getSouthEast());
        Boolean south = riverMap.get(mmTileEdgeDirection.getSouth());
        Boolean southWest = riverMap.get(mmTileEdgeDirection.getSouthWest());
        Boolean northWest = riverMap.get(mmTileEdgeDirection.getNorthWest());

        this.riverMap = null;
        this.riverMap = new HashMap<mmTileEdgeDirection, Boolean>();
        riverMap.put(mmTileEdgeDirection.getNorth(), northWest);
        riverMap.put(mmTileEdgeDirection.getNorthEast(), north);
        riverMap.put(mmTileEdgeDirection.getSouthEast(), northEast);
        riverMap.put(mmTileEdgeDirection.getSouth(), southEast);
        riverMap.put(mmTileEdgeDirection.getSouthWest(), south);
        riverMap.put(mmTileEdgeDirection.getNorthWest(), southWest);
    }



    public int getRotationAmount() {
        return this.rotationAmount;
    }

    private static Map<mmTileEdgeDirection, Boolean> getDefaultMap() {
        Map<mmTileEdgeDirection, Boolean> riverMap = new HashMap<>();
        riverMap.put(mmTileEdgeDirection.getNorth(), false);
        riverMap.put(mmTileEdgeDirection.getNorthEast(), false);
        riverMap.put(mmTileEdgeDirection.getNorthWest(), false);
        riverMap.put(mmTileEdgeDirection.getSouth(), false);
        riverMap.put(mmTileEdgeDirection.getSouthEast(), false);
        riverMap.put(mmTileEdgeDirection.getSouthWest(), false);
        return riverMap;
    }

    public boolean canConnectNorth() {
        return this.riverMap.get(mmTileEdgeDirection.getNorth());
    }

    public boolean canConnectNortheast() {
        return this.riverMap.get(mmTileEdgeDirection.getNorthEast());
    }

    public boolean canConnectSoutheast() {
        return this.riverMap.get(mmTileEdgeDirection.getSouthEast());
    }

    public boolean canConnectSouth() {
        return this.riverMap.get(mmTileEdgeDirection.getSouth());
    }

    public boolean canConnectSouthwest() {
        return this.riverMap.get(mmTileEdgeDirection.getSouthWest());
    }

    public boolean canConnectNorthwest() {
        return this.riverMap.get(mmTileEdgeDirection.getNorthWest());
    }

    public static mmRiverConfiguration getNoRivers() {
        return new mmRiverConfiguration(getDefaultMap());
    }

    private static Map<mmTileEdgeDirection, Boolean> getSpringHeadMap() {
        Map<mmTileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(mmTileEdgeDirection.getNorth(), true);
        return riverMap;
    }

    public static mmRiverConfiguration getSpringHead() {
        return new mmRiverConfiguration(getSpringHeadMap());
    }

    private static Map<mmTileEdgeDirection, Boolean> getAdjacentFacesMap() {
        Map<mmTileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(mmTileEdgeDirection.getNorth(), true);
        riverMap.replace(mmTileEdgeDirection.getNorthEast(), true);
        return riverMap;
    }

    public static mmRiverConfiguration getAdjacentFaces() {
        return new mmRiverConfiguration(getAdjacentFacesMap());
    }

    private static Map<mmTileEdgeDirection, Boolean> getSkipAFaceMap() {
        Map<mmTileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(mmTileEdgeDirection.getNorth(), true);
        riverMap.replace(mmTileEdgeDirection.getSouthEast(), true);
        return riverMap;
    }

    public static mmRiverConfiguration getSkipAFace() {
        return new mmRiverConfiguration(getSkipAFaceMap());
    }

    private static Map<mmTileEdgeDirection, Boolean> getOppositeFacesMap() {
        Map<mmTileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(mmTileEdgeDirection.getNorth(), true);
        riverMap.replace(mmTileEdgeDirection.getSouth(), true);
        return riverMap;
    }

    public static mmRiverConfiguration getOppositeFaces() {
        return new mmRiverConfiguration(getOppositeFacesMap());
    }

    private static Map<mmTileEdgeDirection, Boolean> getEveryOtherFaceMap() {
        Map<mmTileEdgeDirection, Boolean> riverMap = getDefaultMap();
        riverMap.replace(mmTileEdgeDirection.getNorth(), true);
        riverMap.replace(mmTileEdgeDirection.getSouthEast(), true);
        riverMap.replace(mmTileEdgeDirection.getSouthWest(), true);
        return riverMap;
    }

    public static mmRiverConfiguration getEveryOtherFace() {
        return new mmRiverConfiguration(getEveryOtherFaceMap());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(riverMap, rotationAmount);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof mmRiverConfiguration))
        {
            return false;
        }

        mmRiverConfiguration rc = (mmRiverConfiguration)o;

        return rc.riverMap.equals(riverMap) && rotationAmount == rc.rotationAmount;
    }

    public boolean canConnect(mmTileEdgeDirection dir)
    {
        return riverMap.get(dir);
    }
}
