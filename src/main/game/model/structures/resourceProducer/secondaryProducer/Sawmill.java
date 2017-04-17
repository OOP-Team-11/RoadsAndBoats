package game.model.structures.resourceProducer.secondaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.SecondaryProducer;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class Sawmill extends SecondaryProducer {

    private static final int LIMIT = 6;         // limit of uses per turn

    private static final int TRUNKS_REQ = 1;    // 1 Trunks input requirement

    private static final int BOARDS_AMT = 2;    // 2 Boards output amount

    private int productionLimit;

    public Sawmill() {
        this.productionLimit = LIMIT;
    }

    /**
     * Used for map importer to import used sawmills
     * @param productionLimit
     */
    public Sawmill(int productionLimit) {
        this.productionLimit = productionLimit;
    }

    // 2 Boards <= 1 Trunk
    @Override
    public boolean produce(TileCompartment tileCompartment) {
        if (canProduceBoards(tileCompartment)) {
            produceBoards(tileCompartment);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(Transport transport) {
        if (canProduceBoards(transport)) {
            produceBoards(transport);
            return true;
        }
        return false;
    }

    private void produceBoards(TileCompartment tileCompartment) {
        tileCompartment.storeResource(ResourceType.BOARDS, BOARDS_AMT);
        decrementProductionLimit();
    }

    private void produceBoards(Transport transport) {
        transport.storeResource(ResourceType.BOARDS, BOARDS_AMT);
        decrementProductionLimit();
    }

    private boolean canProduceBoards(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.TRUNKS, TRUNKS_REQ);
    }

    private boolean canProduceBoards(Transport transport) {
        return transport.takeResource(ResourceType.TRUNKS, TRUNKS_REQ);
    }

    private void decrementProductionLimit() {
        --productionLimit;
    }

    public void resetProductionLimit() {
        productionLimit = LIMIT;
    }

    public int getProductionLimit() {
        return productionLimit;
    }

    @Override
    public StructureType getType() {
        return StructureType.SAWMILL;
    }

    @Override
    public String getExportString() {
        //Has limit, so getExportString should return its limit
        return "LIMIT=" + this.getProductionLimit();
    }
}
