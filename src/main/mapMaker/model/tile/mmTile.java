package mapMaker.model.tile;


import game.model.TileCompartment;
import game.model.TileEdge;
import mapMaker.direction.mmAngle;
import mapMaker.direction.mmTileCompartmentDirection;
import mapMaker.direction.mmTileEdgeDirection;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;

import java.util.HashMap;
import java.util.Map;

public class mmTile implements Cloneable{

    private Map<mmTileEdgeDirection, TileEdge> edges;
    private Map<mmTileCompartmentDirection, TileCompartment> compartments;
    private mmTerrain mmTerrain;
    private mmRiverConfiguration mmRiverConfiguration;

    public mmTile(mmTerrain mmTerrain, mmRiverConfiguration mmRiverConfiguration) {
        edges = new HashMap<mmTileEdgeDirection, TileEdge>();
        compartments = new HashMap<mmTileCompartmentDirection, TileCompartment>();

        this.mmRiverConfiguration = mmRiverConfiguration;
        this.mmTerrain = mmTerrain;

        edges.put(mmTileEdgeDirection.getNorth(),
                new TileEdge(canConnectRiver(mmTileEdgeDirection.getNorth(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectNorth()));

        edges.put(mmTileEdgeDirection.getNorthEast(),
                new TileEdge(canConnectRiver(mmTileEdgeDirection.getNorthEast(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectNortheast()));

        edges.put(mmTileEdgeDirection.getNorthWest(),
                new TileEdge(canConnectRiver(mmTileEdgeDirection.getNorthWest(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectNorthwest()));

        edges.put(mmTileEdgeDirection.getSouth(),
                new TileEdge(canConnectRiver(mmTileEdgeDirection.getSouth(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectSouth()));

        edges.put(mmTileEdgeDirection.getSouthEast(),
                new TileEdge(canConnectRiver(mmTileEdgeDirection.getSouthEast(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectSoutheast()));

        edges.put(mmTileEdgeDirection.getSouthWest(),
                new TileEdge(canConnectRiver(mmTileEdgeDirection.getSouthWest(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectSouthwest()));

        this.rotateAccordingToRiverConfiguration();

        compartments.put(mmTileCompartmentDirection.getEast(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorthNorthEast(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorthEast(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorth(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorthNorthWest(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorthWest(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getWest(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getSouthWest(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getSouthSouthWest(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getSouth(), new TileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getSouthEast(), new TileCompartment(false));

        this.mmTerrain = mmTerrain;

    }

    private void rotateAccordingToRiverConfiguration() {
        int rotationAmount = this.mmRiverConfiguration.getRotationAmount();
        while (rotationAmount > 0) {
            this.rotate(new mmAngle(60));
            rotationAmount--;
        }
    }

    public mmRiverConfiguration getMmRiverConfiguration() {
        return this.mmRiverConfiguration;
    }

    private boolean canConnectRiver(mmTileEdgeDirection mmTileEdgeDirection, mmRiverConfiguration mmRiverConfiguration, mmTerrain mmTerrain) {

        return (mmTileEdgeDirection.equals(mmTileEdgeDirection.getNorth()) && mmRiverConfiguration.canConnectNorth() ||
                mmTileEdgeDirection.equals(mmTileEdgeDirection.getNorthEast()) && mmRiverConfiguration.canConnectNortheast() ||
                mmTileEdgeDirection.equals(mmTileEdgeDirection.getSouthEast()) && mmRiverConfiguration.canConnectSoutheast() ||
                mmTileEdgeDirection.equals(mmTileEdgeDirection.getSouth()) && mmRiverConfiguration.canConnectSouth() ||
                mmTileEdgeDirection.equals(mmTileEdgeDirection.getSouthWest()) && mmRiverConfiguration.canConnectSouthwest() ||
                mmTileEdgeDirection.equals(mmTileEdgeDirection.getNorthWest()) && mmRiverConfiguration.canConnectNorthwest()) ||
                mmTerrain.canConnectRiver();
    }

    public Object makeClone(){
        try{
            mmTile mmTileClone = (mmTile) super.clone();
            mmTileClone.setMmRiverConfiguration((mmRiverConfiguration)(mmTileClone.getMmRiverConfiguration()));
            Map<mmTileEdgeDirection, TileEdge> directionsClone = new HashMap<mmTileEdgeDirection, TileEdge>();
            for (Map.Entry<mmTileEdgeDirection, TileEdge> entry : edges.entrySet()) {
                mmTileEdgeDirection directionClone = (mmTileEdgeDirection)entry.getKey().clone();
                TileEdge edgeClone = (TileEdge)entry.getValue().clone();
                directionsClone.put(directionClone,edgeClone);
            }
            mmTileClone.edges = directionsClone;
            return mmTileClone;
        } catch(CloneNotSupportedException e){
            throw new InternalError(e.toString());
        }
    }

    // for deep cloning
    private void setMmRiverConfiguration(mmRiverConfiguration mmRiverConfiguration){
        this.mmRiverConfiguration = mmRiverConfiguration;
    }

    // mmTileEdge
    public TileEdge getTileEdge(mmTileEdgeDirection edgeDirection) {
        return edges.get(edgeDirection);
    }

    public TileEdge setTileEdge(mmTileEdgeDirection edgeDirection, TileEdge edge) {
        return edges.put(edgeDirection, edge);
    }

    // mmTileCompartment
    public TileCompartment getTileCompartment(mmTileCompartmentDirection compartmentDirection) {
        return compartments.get(compartmentDirection);
    }

    public void setHasWater(mmTileCompartmentDirection direction, boolean bool) {
        getTileCompartment(direction).setHasWater(bool);
    }

    public mmTerrain getMmTerrain() {
        return mmTerrain;
    }

    public void rotate(mmAngle mmAngle) {
        int rotationDegrees = mmAngle.getDegrees();

        if(rotationDegrees < 60)
            return;
        for(int i = 0; i < (rotationDegrees/60); i++) {
            rotateEdges();
            rotateCompartments();
            rotateRiverConfigurations();
        }
    }
    private void rotateRiverConfigurations(){
        mmRiverConfiguration.rotateRiverConfigurationOnce();
    }

    private void rotateEdges() {
        TileEdge northEdge = edges.get(mmTileEdgeDirection.getNorth());
        TileEdge northEastEdge = edges.get(mmTileEdgeDirection.getNorthEast());
        TileEdge northWestEdge = edges.get(mmTileEdgeDirection.getNorthWest());
        TileEdge southEdge = edges.get(mmTileEdgeDirection.getSouth());
        TileEdge southEastEdge = edges.get(mmTileEdgeDirection.getSouthEast());
        TileEdge southWestEdge = edges.get(mmTileEdgeDirection.getSouthWest());

        edges.put(mmTileEdgeDirection.getNorth(), northWestEdge);
        edges.put(mmTileEdgeDirection.getNorthEast(), northEdge);
        edges.put(mmTileEdgeDirection.getSouthEast(), northEastEdge);
        edges.put(mmTileEdgeDirection.getSouth(), southEastEdge);
        edges.put(mmTileEdgeDirection.getSouthWest(), southEdge);
        edges.put(mmTileEdgeDirection.getNorthWest(), southWestEdge);

    }

    private void rotateCompartments() {
        TileCompartment northComp = compartments.get(mmTileCompartmentDirection.getNorth());
        TileCompartment northNorthEastComp = compartments.get(mmTileCompartmentDirection.getNorthNorthEast());
        TileCompartment northEastComp = compartments.get(mmTileCompartmentDirection.getNorthEast());
        TileCompartment eastComp = compartments.get(mmTileCompartmentDirection.getEast());
        TileCompartment southEastComp = compartments.get(mmTileCompartmentDirection.getSouthEast());
        TileCompartment southSouthEastComp = compartments.get(mmTileCompartmentDirection.getSouthSouthEast());
        TileCompartment southComp = compartments.get(mmTileCompartmentDirection.getSouth());
        TileCompartment southSouthWestComp = compartments.get(mmTileCompartmentDirection.getSouthSouthWest());
        TileCompartment southWestComp = compartments.get(mmTileCompartmentDirection.getSouthWest());
        TileCompartment westComp = compartments.get(mmTileCompartmentDirection.getWest());
        TileCompartment northWestComp = compartments.get(mmTileCompartmentDirection.getNorthWest());
        TileCompartment northNorthWestComp = compartments.get(mmTileCompartmentDirection.getNorthNorthWest());

        compartments.put(mmTileCompartmentDirection.getEast(), northNorthEastComp);
        compartments.put(mmTileCompartmentDirection.getNorthNorthEast(), northNorthWestComp);
        compartments.put(mmTileCompartmentDirection.getNorthEast(), northComp);
        compartments.put(mmTileCompartmentDirection.getNorth(), northWestComp);
        compartments.put(mmTileCompartmentDirection.getNorthNorthWest(), westComp);
        compartments.put(mmTileCompartmentDirection.getNorthWest(), southWestComp);
        compartments.put(mmTileCompartmentDirection.getWest(), southSouthWestComp);
        compartments.put(mmTileCompartmentDirection.getSouthWest(), southComp);
        compartments.put(mmTileCompartmentDirection.getSouthSouthWest(), southSouthEastComp);
        compartments.put(mmTileCompartmentDirection.getSouth(), southEastComp);
        compartments.put(mmTileCompartmentDirection.getSouthEast(), northEastComp);
        compartments.put(mmTileCompartmentDirection.getSouthSouthEast(), eastComp);

    }

    public void resetTileEdge(mmTileEdgeDirection dir)
    {
        edges.put(mmTileEdgeDirection.getSouthWest(),
                new TileEdge(canConnectRiver(mmTileEdgeDirection.getSouthWest(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnect(dir)));
    }
}
