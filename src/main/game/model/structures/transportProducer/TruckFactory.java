//package game.model.structures.transportProducer;
//
//import game.model.resources.ResourceType;
//import game.model.structures.StructureType;
//import game.model.tile.TileCompartment;
//import game.model.transport.Transport;
//import game.model.transport.TransportId;
//import game.model.transport.TruckTransport;
//
//public class TruckFactory extends TransportProducer {
//
//    private static final int IRON_REQ = 1;
//    private static final int FUEL_REQ = 1;
//
//    // 1 Truck = 1 Iron + 1 Fuel
//    public TruckFactory() {
//
//    }
//
//    @Override
//    public Transport produce(Transport transport) {
//        if (canProduceTruck(transport)) {
//            return new TruckTransport(transport.getPlayerId(), new TransportId());
//        }
//        return null;
//    }
//
//    @Override
//    public boolean produce(TileCompartment tileCompartment) {
//        return canProduceTruck(tileCompartment);
//    }
//
//    private boolean canProduceTruck(Transport transport) {
//        return transport.takeResource(ResourceType.IRON, IRON_REQ)
//                && transport.takeResource(ResourceType.FUEL, FUEL_REQ);
//    }
//
//    private boolean canProduceTruck(TileCompartment tileCompartment) {
//        return tileCompartment.takeResource(ResourceType.IRON, IRON_REQ)
//                && tileCompartment.takeResource(ResourceType.FUEL, FUEL_REQ);
//    }
//
//    @Override
//    public StructureType getType() {
//        return StructureType.TRUCK_FACTORY;
//    }
//
//    @Override
//    public String getExportString() {
//        // TODO add other things?
//        return this.getType().toString();
//    }
//
//}
