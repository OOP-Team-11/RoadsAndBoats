package mapMaker.model.tile;


import mapMaker.direction.mmAngle;
import mapMaker.direction.mmTileCompartmentDirection;
import mapMaker.direction.mmTileEdgeDirection;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;

import java.util.HashMap;
import java.util.Map;

public class mmTile implements Cloneable{

    private Map<mmTileEdgeDirection, mmTileEdge> edges;
    private Map<mmTileCompartmentDirection, mmTileCompartment> compartments;
    private mmTerrain mmTerrain;
    private mmRiverConfiguration mmRiverConfiguration;

    public mmTile(mmTerrain mmTerrain, mmRiverConfiguration mmRiverConfiguration) {
        edges = new HashMap<mmTileEdgeDirection, mmTileEdge>();
        compartments = new HashMap<mmTileCompartmentDirection, mmTileCompartment>();

        this.mmRiverConfiguration = mmRiverConfiguration;
        this.mmTerrain = mmTerrain;

        edges.put(mmTileEdgeDirection.getNorth(),
                new mmTileEdge(canConnectRiver(mmTileEdgeDirection.getNorth(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectNorth()));

        edges.put(mmTileEdgeDirection.getNorthEast(),
                new mmTileEdge(canConnectRiver(mmTileEdgeDirection.getNorthEast(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectNortheast()));

        edges.put(mmTileEdgeDirection.getNorthWest(),
                new mmTileEdge(canConnectRiver(mmTileEdgeDirection.getNorthWest(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectNorthwest()));

        edges.put(mmTileEdgeDirection.getSouth(),
                new mmTileEdge(canConnectRiver(mmTileEdgeDirection.getSouth(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectSouth()));

        edges.put(mmTileEdgeDirection.getSouthEast(),
                new mmTileEdge(canConnectRiver(mmTileEdgeDirection.getSouthEast(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectSoutheast()));

        edges.put(mmTileEdgeDirection.getSouthWest(),
                new mmTileEdge(canConnectRiver(mmTileEdgeDirection.getSouthWest(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnectSouthwest()));

        this.rotateAccordingToRiverConfiguration();

        compartments.put(mmTileCompartmentDirection.getEast(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorthNorthEast(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorthEast(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorth(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorthNorthWest(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getNorthWest(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getWest(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getSouthWest(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getSouthSouthWest(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getSouth(), new mmTileCompartment(false));
        compartments.put(mmTileCompartmentDirection.getSouthEast(), new mmTileCompartment(false));

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
            Map<mmTileEdgeDirection, mmTileEdge> directionsClone = new HashMap<mmTileEdgeDirection, mmTileEdge>();
            for (Map.Entry<mmTileEdgeDirection, mmTileEdge> entry : edges.entrySet()) {
                mmTileEdgeDirection directionClone = (mmTileEdgeDirection)entry.getKey().clone();
                mmTileEdge edgeClone = (mmTileEdge)entry.getValue().clone();
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
    public mmTileEdge getTileEdge(mmTileEdgeDirection edgeDirection) {
        return edges.get(edgeDirection);
    }

    public mmTileEdge setTileEdge(mmTileEdgeDirection edgeDirection, mmTileEdge edge) {
        return edges.put(edgeDirection, edge);
    }

    // mmTileCompartment
    public mmTileCompartment getTileCompartment(mmTileCompartmentDirection compartmentDirection) {
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
        mmTileEdge northEdge = edges.get(mmTileEdgeDirection.getNorth());
        mmTileEdge northEastEdge = edges.get(mmTileEdgeDirection.getNorthEast());
        mmTileEdge northWestEdge = edges.get(mmTileEdgeDirection.getNorthWest());
        mmTileEdge southEdge = edges.get(mmTileEdgeDirection.getSouth());
        mmTileEdge southEastEdge = edges.get(mmTileEdgeDirection.getSouthEast());
        mmTileEdge southWestEdge = edges.get(mmTileEdgeDirection.getSouthWest());

        edges.put(mmTileEdgeDirection.getNorth(), northWestEdge);
        edges.put(mmTileEdgeDirection.getNorthEast(), northEdge);
        edges.put(mmTileEdgeDirection.getSouthEast(), northEastEdge);
        edges.put(mmTileEdgeDirection.getSouth(), southEastEdge);
        edges.put(mmTileEdgeDirection.getSouthWest(), southEdge);
        edges.put(mmTileEdgeDirection.getNorthWest(), southWestEdge);

    }

    private void rotateCompartments() {
        mmTileCompartment northComp = compartments.get(mmTileCompartmentDirection.getNorth());
        mmTileCompartment northNorthEastComp = compartments.get(mmTileCompartmentDirection.getNorthNorthEast());
        mmTileCompartment northEastComp = compartments.get(mmTileCompartmentDirection.getNorthEast());
        mmTileCompartment eastComp = compartments.get(mmTileCompartmentDirection.getEast());
        mmTileCompartment southEastComp = compartments.get(mmTileCompartmentDirection.getSouthEast());
        mmTileCompartment southSouthEastComp = compartments.get(mmTileCompartmentDirection.getSouthSouthEast());
        mmTileCompartment southComp = compartments.get(mmTileCompartmentDirection.getSouth());
        mmTileCompartment southSouthWestComp = compartments.get(mmTileCompartmentDirection.getSouthSouthWest());
        mmTileCompartment southWestComp = compartments.get(mmTileCompartmentDirection.getSouthWest());
        mmTileCompartment westComp = compartments.get(mmTileCompartmentDirection.getWest());
        mmTileCompartment northWestComp = compartments.get(mmTileCompartmentDirection.getNorthWest());
        mmTileCompartment northNorthWestComp = compartments.get(mmTileCompartmentDirection.getNorthNorthWest());

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
                new mmTileEdge(canConnectRiver(mmTileEdgeDirection.getSouthWest(), mmRiverConfiguration, mmTerrain),
                        mmRiverConfiguration.canConnect(dir)));
    }
}
