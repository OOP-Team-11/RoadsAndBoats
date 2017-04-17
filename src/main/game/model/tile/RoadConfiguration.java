package game.model.tile;

import game.model.direction.TileEdgeDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RoadConfiguration
{
    private Map<TileEdgeDirection, Boolean> roadMap;
    private int rotationAmount;

    public RoadConfiguration(Map<TileEdgeDirection, Boolean> roadMap) {
        this.roadMap = roadMap;
        this.rotationAmount = 0;
    }

    public boolean canConnectNorth() {
        return this.roadMap.get(TileEdgeDirection.getNorth());
    }

    public boolean canConnectNortheast() {
        return this.roadMap.get(TileEdgeDirection.getNorthEast());
    }

    public boolean canConnectSoutheast() {
        return this.roadMap.get(TileEdgeDirection.getSouthEast());
    }

    public boolean canConnectSouth() {
        return this.roadMap.get(TileEdgeDirection.getSouth());
    }

    public boolean canConnectSouthwest() {
        return this.roadMap.get(TileEdgeDirection.getSouthWest());
    }

    public boolean canConnectNorthwest() {
        return this.roadMap.get(TileEdgeDirection.getNorthWest());
    }

    public boolean canConnect(TileEdgeDirection dir)
    {
        return roadMap.get(dir);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(roadMap, rotationAmount);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof RoadConfiguration))
        {
            return false;
        }

        RoadConfiguration rc = (RoadConfiguration)o;

        return rc.roadMap.equals(roadMap) && rotationAmount == rc.rotationAmount;
    }

    private int getNumRoads() {
        int numRoads = 0;
        for (Boolean hasRoad : roadMap.values()) {
            if (hasRoad) numRoads++;
        }
        return numRoads;
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
        if (getNumRoads() == 0) return "";

        sb.append("( ");

        Iterator it = roadMap.entrySet().iterator();
        List<Integer> roadConfiguration = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileEdgeDirection tileEdgeDirection = (TileEdgeDirection) pair.getKey();
            Boolean hasRoad = (Boolean) pair.getValue();
            if (hasRoad) {
                roadConfiguration.add(getSideNumberForTileEdgeDirection(tileEdgeDirection));
            }
        }

        Collections.sort(roadConfiguration);
        for (Integer side : roadConfiguration) {
            sb.append(side).append(" ");
        }
        sb.append(")");
        return sb.toString();
    }
}
