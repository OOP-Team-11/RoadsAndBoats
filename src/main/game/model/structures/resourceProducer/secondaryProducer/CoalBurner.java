package game.model.structures.resourceProducer.secondaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.SecondaryProducer;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class CoalBurner extends SecondaryProducer {

    private static final int LIMIT = 6;         // limit of uses per turn

    private static final int TRUNKS_REQ2 = 2;   // 2 Trunks input requirement
    private static final int BOARDS_REQ2 = 2;   // 2 Boards input requirement
    private static final int TRUNKS_REQ1 = 1;   // 1 Trunks + 1 Boards input requirement
    private static final int BOARDS_REQ1 = 1;

    private static final int FUEL_AMT = 1;      // 1 Fuel output amount

    private int productionLimit;

    public CoalBurner() {
        this.productionLimit = LIMIT;
    }

    // 1 Fuel <= 2 Trunks
    // 1 Fuel <= 2 Boards
    // 1 Fuel <= 1 Trunks + 1 Boards
    @Override
    public boolean produce(TileCompartment tileCompartment) {
        if (canProduceCoalWithTrunks(tileCompartment)) {
            produceCoalWithTrunks(tileCompartment);
            return true;
        }
        else if (canProduceCoalWithBoards(tileCompartment)) {
            produceCoalWithBoards(tileCompartment);
            return true;
        }
        else if (canProduceCoalWithBoth(tileCompartment)) {
            produceCoalWithBoth(tileCompartment);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(Transport transport) {
        if (canProduceCoalWithTrunks(transport)) {
            produceCoalWithTrunks(transport);
            return true;
        }
        else if (canProduceCoalWithBoards(transport)) {
            produceCoalWithBoards(transport);
            return true;
        }
        else if (canProduceCoalWithBoth(transport)) {
            produceCoalWithBoth(transport);
            return true;
        }
        return false;
    }

    private void produceCoalWithTrunks(TileCompartment tileCompartment) {
        tileCompartment.storeResource(ResourceType.FUEL, FUEL_AMT);
        decrementProductionLimit();
    }

    private void produceCoalWithBoards(TileCompartment tileCompartment) {
        tileCompartment.storeResource(ResourceType.FUEL, FUEL_AMT);
        decrementProductionLimit();
    }

    private void produceCoalWithBoth(TileCompartment tileCompartment) {
        tileCompartment.storeResource(ResourceType.FUEL, FUEL_AMT);
        decrementProductionLimit();
    }

    private void produceCoalWithTrunks(Transport transport) {
        transport.storeResource(ResourceType.FUEL, FUEL_AMT);
        decrementProductionLimit();
    }

    private void produceCoalWithBoards(Transport transport) {
        transport.storeResource(ResourceType.FUEL, FUEL_AMT);
        decrementProductionLimit();
    }

    private void produceCoalWithBoth(Transport transport) {
        transport.storeResource(ResourceType.FUEL, FUEL_AMT);
        decrementProductionLimit();
    }

    private boolean canProduceCoalWithTrunks(TileCompartment tileCompartment) {
        return (productionLimit != 0)
                && tileCompartment.takeResource(ResourceType.TRUNKS, TRUNKS_REQ2);
    }

    private boolean canProduceCoalWithBoards(TileCompartment tileCompartment) {
        return (productionLimit != 0)
                && tileCompartment.takeResource(ResourceType.BOARDS, BOARDS_REQ2);
    }

    private boolean canProduceCoalWithBoth(TileCompartment tileCompartment) {
        return (productionLimit != 0)
                && tileCompartment.takeResource(ResourceType.TRUNKS, TRUNKS_REQ1)
                && tileCompartment.takeResource(ResourceType.BOARDS, BOARDS_REQ1);
    }

    private boolean canProduceCoalWithTrunks(Transport transport) {
        return (productionLimit != 0)
                && transport.takeResource(ResourceType.TRUNKS, TRUNKS_REQ2);
    }

    private boolean canProduceCoalWithBoards(Transport transport) {
        return (productionLimit != 0)
                && transport.takeResource(ResourceType.BOARDS, BOARDS_REQ2);
    }

    private boolean canProduceCoalWithBoth(Transport transport) {
        return (productionLimit != 0)
                && transport.takeResource(ResourceType.TRUNKS, TRUNKS_REQ1)
                && transport.takeResource(ResourceType.BOARDS, BOARDS_REQ1);
    }

    private void decrementProductionLimit() {
        --productionLimit;
    }

    public void resetProductionLimit() {
        productionLimit = LIMIT;
    }

    public int getProductionLimit() {
        return  productionLimit;
    }

    @Override
    public StructureType getType() {
        return StructureType.COAL_BURNER;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }
}
